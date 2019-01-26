package springboot.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

@Controller
public class AuthController {
	
	@RequestMapping("/auth")
	protected void auth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get twitter intance
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.setDebugEnabled(true)
				.setOAuthConsumerKey("kLdFjFhJkiiWMb2SU4ZNtpGlf")
				.setOAuthConsumerSecret("VUXAlVuDbdOYDGhbImgYOfbX91xqtvSdFnXn3kzM6ZNoOWv6fa")
				.setOAuthAccessToken(null)
				.setOAuthAccessTokenSecret(null);
		
		TwitterFactory twitterFactory = new TwitterFactory(config.build());
		Twitter twitter = twitterFactory.getInstance();

		request.getSession().setAttribute("twitter", twitter);

		// Get the request token
		RequestToken requestToken = null;
		
		try {
			requestToken = twitter.getOAuthRequestToken();
			request.getSession().setAttribute("requestToken", requestToken);
			String token = requestToken.getToken();
			String tokenSecret = requestToken.getTokenSecret();
			System.out.println("Request Token:" + token);
			System.out.println("Request Token Secret:" + tokenSecret);
//			System.out.println(request.getSession());
//			System.out.println(request.getSession().getAttribute("twitter"));
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		// Redirect to authorization url
		response.sendRedirect(requestToken.getAuthorizationURL());

		return;
	}

	@RequestMapping("/success")
	public @ResponseBody String success(HttpServletRequest request) {
		// Get twitter and request token from session
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
		String verifier = request.getParameter("oauth_verifier");
		
		System.out.println("Twitter:" + twitter);
		System.out.println("Request Token:" + requestToken.getToken());
		System.out.println("Request Token Secret:" + requestToken.getTokenSecret());
		
		// Get access token
		try {
			request.getSession().removeAttribute("requestToken");
			AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
			System.out.println("Access Token:" + accessToken.getToken());
			System.out.println("Access Token Secret:" + accessToken.getTokenSecret());
			User user = twitter.verifyCredentials();
			System.out.print(user);
			user = twitter.showUser(accessToken.getUserId());
			System.out.println(user);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return "Hello";
	}
}
