package springboot.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

@Controller
public class AuthController {
	
	@RequestMapping("/auth")
	protected void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String consumerKey = "kLdFjFhJkiiWMb2SU4ZNtpGlf";
		String consumerSecret = "VUXAlVuDbdOYDGhbImgYOfbX91xqtvSdFnXn3kzM6ZNoOWv6fa";
		
		// Get request token and token secret
		Twitter twitter = new TwitterFactory().getInstance();
    	twitter.setOAuthConsumer(consumerKey, consumerSecret);
    	RequestToken requestToken = null;
    	
		try {
			requestToken = twitter.getOAuthRequestToken();
			// Store twitter and request token in session
			request.getSession().setAttribute("twitter", twitter);
			request.getSession().setAttribute("requestToken", requestToken);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		String token = requestToken.getToken();
		String tokenSecret = requestToken.getTokenSecret();
		System.out.println("---Request Token:" + token);
		System.out.println("---Request Token Secret:" + tokenSecret);

		// Redirect to authorization page
    	response.sendRedirect(requestToken.getAuthorizationURL());

//    	twitter = (Twitter) request.getSession().getAttribute("twitter");
//		requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
//		
//		System.out.println("Twitter:" + twitter);
//		token = requestToken.getToken();
//		tokenSecret = requestToken.getTokenSecret();
//		System.out.println("Request Token:" + token);
//		System.out.println("Request Token Secret:" + tokenSecret);
		return;
	}
}
