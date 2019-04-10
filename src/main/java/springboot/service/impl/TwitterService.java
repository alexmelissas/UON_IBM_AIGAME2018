package springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

import springboot.config.AuthConfig;
import springboot.config.PlayerConfig;
import springboot.domain.Ideal;
import springboot.domain.Player;
import springboot.domain.User;
import springboot.service.IdealService;
import springboot.service.PlayerService;
import springboot.service.UserService;
import springboot.util.AnalysisResult;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * <p>
 * The TwitterService provide authorization of Twitter and access of tweets
 * </p>
 * 
 * @author chenyu
 *
 */
@Service("twitterService")
public class TwitterService {
	@Autowired
	private UserService userService;
	@Autowired
	private PersonalityInsightService piService;
	@Autowired
	private IdealService idealService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private RedisService redisService;
	private static Logger logger = LoggerFactory.getLogger(TwitterService.class);

	/**
	 * Analysis tweets
	 * 
	 * @param id           the id
	 * @param twitter      the twitter
	 * @param verifier     the verifier
	 * @param requestToken the request token
	 * @return
	 */
	public String analysisTweets(String id, Twitter twitter, String verifier, RequestToken requestToken) {
		try {
			logger.info(">>>User information [id:{}]", id);
			logger.info(">>>Store access token");
			AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);

			// Store access token
			User newUser = userService.getUserById(id);
			newUser.setAccessToken(accessToken.getToken());
			newUser.setAccessTokenSecret(accessToken.getTokenSecret());
			userService.updateUser(id, newUser);

			String result = loadTweets(id, twitter);
			logger.info(">>>Analysis successed");
			return result;
		} catch (TwitterException e) {
			e.printStackTrace();
			logger.info(">>>Analysis failed");
			return "Authorization fail.";
		}
	}

	/**
	 * Reanalysis tweets
	 * 
	 * @param id the id
	 * @return the result of analysis
	 */
	public Player reAnalysisTweets(String id) {
		logger.info(">>>Re-Analysis tweets of [id:{}]", id);
		User user = userService.getUserById(id);
		AccessToken accessToken = new AccessToken(user.getAccessToken(), user.getAccessTokenSecret());
		Twitter twitter = AuthConfig.getTwitter();
		twitter.setOAuthAccessToken(accessToken);
		Player player = null; 
		
		try {
			String result = loadTweets(id, twitter);
			logger.info(">>>Re-Analysis tweets successed");
			if ("Authorization success.".equals(result)) {
				player = playerService.getPlayerById(id);
			}
		} catch (TwitterException e) {
			logger.info(">>>Re-Analysis tweets failed");
		}
		return player;
	}

	/**
	 * Load tweets and analysis them
	 * 
	 * @param id      the id
	 * @param twitter the twitter
	 * @return the result of analysis
	 * @throws TwitterException exception
	 */
	public String loadTweets(String id, Twitter twitter) throws TwitterException {
		logger.info(">>>Loading tweets from [name:{}]", twitter.getScreenName());
		// Get all tweets
		List<Status> statuses = new ArrayList<Status>();
		int pageno = 1;

		while (true) {
			int size = statuses.size();
			Paging page = new Paging(pageno, 200);
			statuses.addAll(twitter.getUserTimeline(page));
			if (statuses.size() == size) {
				break;
			}
			pageno++;
		}

		StringBuilder content = new StringBuilder();
		for (Status status : statuses) {
			content.append(status.getText());
		}

		// Analysis
		return analysis(content, id);
	}

	/**
	 * Analysis
	 * 
	 * @param content the analysis content
	 * @param id      the id
	 * @return the result of analysis
	 */
	public String analysis(StringBuilder content, String id) {
		logger.info(">>>Analysis tweets");
		Profile profile = piService.analysis(content);

		boolean isSufficient = true;
		if (profile.getWordCount() == null || profile.getWordCount() <= 100) {
			// insufficient words
			isSufficient = false;
			logger.info(">>>Invalid analysis result caused by insufficient word count");
		} else {
			// Store result in cache for one hour
			redisService.setResult(id, profile.toString());
		}

		// create the ideal and player for new user
		if (idealService.getIdealById(id) == null) {
			initialize(id, true);
		} else {
			// Update the similarity of twitter
			updateSimilarity(id, profile.toString(), isSufficient);
		}
		return "Authorization success.";
	}

	/**
	 * Without twitter
	 * 
	 * @param id the id
	 * @return the result of
	 */
	public void withoutTwitter(String id) {
		logger.info(">>>Initializing the character", id);
		initialize(id, false);

	}

	/**
	 * Initialize the ideal and player for a new user
	 * 
	 * @param id   the id
	 * @param auth whether the user has authorized with Twitter
	 */
	public void initialize(String id, boolean auth) {
		logger.info(">>>Initializing the character");
		String characterName = userService.getUserById(id).getUsername();
		Ideal ideal = new Ideal(id);
		ideal.setAuth(auth);
		Player player = new Player(id);
		player.setAttributes(PlayerConfig.getBasicStatus(1));
		player.setCharacterName(characterName);

		idealService.addIdeal(ideal);
		playerService.addPlayer(player);
	}

	/**
	 * Update the similarity with ideal personality
	 * 
	 * @param id           the id
	 * @param jsonResult   the json result of analysis
	 * @param isSufficient whether the word is enough
	 */
	public void updateSimilarity(String id, String jsonResult, boolean isSufficient) {
		logger.info(">>>Update the similarity");
		AnalysisResult analysisResult = new AnalysisResult();
		analysisResult.setJsonObject(jsonResult);
		Ideal ideal = idealService.getIdealById(id);
		Player player = playerService.getPlayerById(id);

		if (!ideal.isAuth()) {
			idealService.reAuth(ideal.getId());
		}

		player.setAttributes(PlayerConfig.getBasicStatus(player.getLevel()));
		if (!isSufficient) {
			analysisResult.generateNormalFactor(player);
		} else {
			analysisResult.generateFactor(ideal, player);
		}

		player.applyPersonality();
		playerService.updatePlayer(id, player);
	}

	/**
	 * Cancel the authorization of Twitter
	 * 
	 * @param id the id
	 */
	public void cancelAuth(String id) {
		logger.info(">>>Unlink the Twitter");
		// factor
		// ideal
		Player player = playerService.getPlayerById(id);
		userService.deleteToken(id);
		idealService.unAuth(id);
		player.setAttributes(PlayerConfig.getBasicStatus(player.getLevel()));
		player.setFactor(1);
		player.applyPersonality();
		playerService.updatePlayer(id, player);
	}
}
