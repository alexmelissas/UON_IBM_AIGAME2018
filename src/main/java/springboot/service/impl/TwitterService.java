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
import springboot.util.ContentLoader;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

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

	// Analysis tweets
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

	// Reanalysis tweets
	public String reAnalysisTweets(String id) {
		// TODO call this function to check twitter again
		
		logger.info(">>>Re-Analysis tweets of [id:{}]", id);
		User user = userService.getUserById(id);
		AccessToken accessToken = new AccessToken(user.getAccessToken(), user.getAccessTokenSecret());
		Twitter twitter = AuthConfig.getTwitter();
		twitter.setOAuthAccessToken(accessToken);

		try {
			String result = loadTweets(id, twitter);
			logger.info(">>>Re-Analysis tweets successed");
			return result;
		} catch (TwitterException e) {
			logger.info(">>>Re-Analysis tweets failed");
			return "Authorization fail.";
		}
	}

	// Load tweets and analysis them
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

		// Add text into ContentLoader
		ContentLoader contentLoader = new ContentLoader();
		for (Status status : statuses) {
			contentLoader.addInput(status.getText());
		}

		// Analysis
		return analysis(contentLoader, id);
	}

	// analysis
	public String analysis(ContentLoader contentLoader, String id) {
		logger.info(">>>Analysis tweets");
		Profile profile = piService.analysis(contentLoader);

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
			// TODO 
			// Update the similarity of twitter
			updateSimilarity(id, profile.toString(), isSufficient);
		}
		return "Authorization success.";
	}

	// without twitter
	public String withoutTwitter(String id) {
		logger.info(">>>Initializing the character", id);
		initialize(id, false);
		return "Account creation succss.";
	}

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
	
	public void updateSimilarity(String id, String jsonResult, boolean isSufficient) {
		logger.info(">>>Update the similarity");
		
		if (!isSufficient) {
			return;
		}
		
		Ideal ideal = idealService.getIdealById(id);
		Player player = playerService.getPlayerById(id);
		
		AnalysisResult analysisResult = new AnalysisResult();
		analysisResult.setJsonObject(jsonResult);
		analysisResult.generateFactor(ideal, player);
	}
}
