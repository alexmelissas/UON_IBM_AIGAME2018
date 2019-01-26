package springboot.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.setDebugEnabled(true).setOAuthConsumerKey("kLdFjFhJkiiWMb2SU4ZNtpGlf")
				.setOAuthConsumerSecret("VUXAlVuDbdOYDGhbImgYOfbX91xqtvSdFnXn3kzM6ZNoOWv6fa").setOAuthAccessToken(null)
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
			System.out.println(request.getSession());
			System.out.println(request.getSession().getAttribute("twitter"));
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		// Redirect to authorization url
		response.sendRedirect(requestToken.getAuthorizationURL());

		return;
	}

	@RequestMapping("/success")
	public @ResponseBody String success(HttpServletRequest request) {
//		oauth_token=2zTZ3gAAAAAA9WQ8AAABaIeSDEU&oauth_verifier=f96Ems3z7czYk5YscvKQ6WKlxlgXaFNy
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.setDebugEnabled(true).setOAuthConsumerKey("kLdFjFhJkiiWMb2SU4ZNtpGlf")
				.setOAuthConsumerSecret("VUXAlVuDbdOYDGhbImgYOfbX91xqtvSdFnXn3kzM6ZNoOWv6fa").setOAuthAccessToken(null)
				.setOAuthAccessTokenSecret(null);
		TwitterFactory twitterFactory = new TwitterFactory(config.build());
		Twitter twitter = twitterFactory.getInstance();
		
		try {
			String verifier = request.getParameter("oauth_verifier");
			AccessToken accessToken = twitter.getOAuthAccessToken(twitter.getOAuthRequestToken(), verifier);
			request.getSession().removeAttribute("requestToken");
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.setOAuthConsumerKey("ipp4zV5GKKUncWGmbRAFeV3Yb");
			builder.setOAuthConsumerSecret("OZmBI2ZdJaqGMXwE46QI0RnrSUJj7h2WRTMWuENCn2Mm9Hh0OZ");
			builder.setOAuthAccessToken(accessToken.getToken());
			builder.setOAuthAccessTokenSecret(accessToken.getTokenSecret());
			builder.setIncludeEmailEnabled(true);
			User user = twitter.verifyCredentials();
			System.out.print(user);
			// User user = twitter.showUser(accessToken.getUserId());
			System.out.println(user);
		} catch (TwitterException e) {
		}
		return "Hello";
	}
}
