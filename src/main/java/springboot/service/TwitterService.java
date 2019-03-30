package springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

import springboot.domain.Ideal;
import springboot.domain.Player;
import springboot.domain.User;
import springboot.util.AuthConfig;
import springboot.util.ContentLoader;
import springboot.util.PlayerConfig;
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

	// Analysis tweets
	public String analysisTweets(String id, Twitter twitter, String verifier, RequestToken requestToken) {
		try {
			AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);

			// Store access token
			User newUser = userService.getUserById(id);
			newUser.setAccessToken(accessToken.getToken());
			newUser.setAccessTokenSecret(accessToken.getTokenSecret());
			userService.updateUser(id, newUser);

			return loadTweets(id, twitter);
		} catch (TwitterException e) {
			e.printStackTrace();
			return "Authorization fail.";
		}
	}

	// Reanalysis tweets
	public String reAnalysisTweets(String id) {
		User user = userService.getUserById(id);
		AccessToken accessToken = new AccessToken(user.getAccessToken(), user.getAccessTokenSecret());
		Twitter twitter = AuthConfig.getTwitter();
		twitter.setOAuthAccessToken(accessToken);

		try {
			return loadTweets(id, twitter);
		} catch (TwitterException e) {
			return "Authorization fail.";
		}
	}

	// Load tweets and analysis them
	public String loadTweets(String id, Twitter twitter) throws TwitterException {
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
		System.out.println("Showing @" + twitter.getScreenName() + "'s home timeline.");
		for (Status status : statuses) {
			System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
			contentLoader.addInput(status.getText());
		}

		// Analysis
		return analysisResult(contentLoader, id);
	}

	// analysis
	public String analysisResult(ContentLoader contentLoader, String id) {
		// Store Json result to database
		Profile profile = piService.analysis(contentLoader);

		if (profile.getWordCount() == null || profile.getWordCount() <= 100) {
			// insufficient words
		} else {
			// Store result in cache for one hour
			redisService.setResult(id, profile.toString());
		}

		// create the ideal and player for new user
		initialize(id, true);

		return "Authorization success.";
	}

	// without twitter
	public String withoutTwitter(String id) {
		initialize(id, false);

		return "Account creation succss.";
	}

	public void initialize(String id, boolean auth) {
		if (idealService.getIdealById(id) != null) {
			// Ideal and Player already exist
			return;
		}
		String characterName = userService.getUserById(id).getUsername();
		Ideal ideal = new Ideal(id);
		ideal.setAuth(auth);
		Player player = new Player(id);
		player.setAttributes(PlayerConfig.getBasicStatus(1));
		player.setCharacterName(characterName);

		idealService.addIdeal(ideal);
		playerService.addPlayer(player);
	}
}
