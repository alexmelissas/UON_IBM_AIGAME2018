package springboot.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import springboot.config.AuthConfig;
import springboot.domain.User;
import springboot.service.UserService;
import springboot.service.impl.TwitterService;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

@RestController
public class AuthController {
	@Autowired
	private UserService userService;
	@Autowired
	private TwitterService twitterService;
	private static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/auth/{id}")
	protected void auth(HttpServletRequest request, HttpServletResponse response, @PathVariable String id)
			throws IOException {
		if (!userService.isExistById(id)) {
			return;
		}

		logger.info("======Authorization======");
		// Get request token and token secret
		Twitter twitter = AuthConfig.getTwitter();
		RequestToken requestToken = null;

		try {
			requestToken = twitter.getOAuthRequestToken();
			// Store twitter and request token in session
			request.getSession().setAttribute("id", id);
			request.getSession().setAttribute("twitter", twitter);
			request.getSession().setAttribute("requestToken", requestToken);
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		// Redirect to authorization page
		response.sendRedirect(requestToken.getAuthorizationURL());
		return;
	}

	@GetMapping("/noauth/{id}")
	public void noauth(@PathVariable String id) {
		if (!userService.isExistById(id)) {
			return;
		}

		logger.info("======Without Authorization======");
		User user = userService.getUserById(id);
		if (user.getAccessToken() != null || user.getAccessTokenSecret() != null) {
			user.setAccessToken(null);
			user.setAccessTokenSecret(null);
			userService.updateUser(id, user);
		}

		twitterService.withoutTwitter(id);
		logger.info("======Without Authorization End======");
	}

	@GetMapping("/auth/cancel/{id}")
	public void cancelAuth(@PathVariable String id) {
		if (!userService.isExistById(id)) {
			return;
		}
		logger.info("======Cancel Authorization======");
		twitterService.cancelAuth(id);
		logger.info("======Cancel Authorization End======");
	}
}
