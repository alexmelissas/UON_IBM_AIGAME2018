package springboot.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

import springboot.service.PersonalityInsightService;
import springboot.service.UserService;
import springboot.util.AnalysisResult;
import springboot.util.ContentLoader;
import springboot.domain.Character;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@RestController
public class AnalysisController {
	@Autowired
	private PersonalityInsightService piService;
	@Autowired
	private UserService userService;
	
    @RequestMapping("/result")
    public Character result(HttpServletRequest request) {
    	// TODO return character in JSON type
    	ContentLoader contentLoader = (ContentLoader) request.getSession().getAttribute("content");
    	Profile profile = piService.analysis(contentLoader);
    	AnalysisResult analysisResult;
//    	System.out.println(profile.toString());
    	if(profile.getWordCount() == null) {
    		// tweets less than 100 words
    		analysisResult = new AnalysisResult(new Profile());
    	} else {
    		analysisResult = new AnalysisResult(profile);
    	}
    	return analysisResult.getCharacter();
    }
    
    // Authorization callback
    @GetMapping("/analysis")
	public void analysis(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uid = (String) request.getSession().getAttribute("uid");
    	Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
		String verifier = request.getParameter("oauth_verifier");
		
		try {
			AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
			// Twitter User
			User user = twitter.verifyCredentials();
			
			// Update user information
			springboot.domain.User newUser = userService.getUserByUid(uid).get();
			newUser.setAccessToken(accessToken.getToken());
			newUser.setAccessTokenSecret(accessToken.getTokenSecret());
			userService.updateUser(uid, newUser);
			
//			user = twitter.showUser(accessToken.getUserId());
//			System.out.println(user);
			
			// Get tweets and add text into ContentLoader
			ContentLoader contentLoader = new ContentLoader();
			List<Status> statuses = twitter.getUserTimeline();			
			System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                contentLoader.addInput(status.getText());
            }
            
            request.getSession().setAttribute("content", contentLoader);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		// TODO related to the failure?
		
		response.sendRedirect(request.getContextPath() + "/result");
		return;
	}
}
