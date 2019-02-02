package springboot.web;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import springboot.domain.User;
import springboot.service.UserService;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

@Controller
public class AuthController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/auth/{id}")
	protected void auth(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) throws IOException {
		// Get the user
		Optional<User> user = userService.getUserById(id);
		if(user == null || !user.isPresent()) {
			try {
				request.getRequestDispatcher("/404").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
			return;
		}
		
		String consumerKey = "kLdFjFhJkiiWMb2SU4ZNtpGlf";
		String consumerSecret = "VUXAlVuDbdOYDGhbImgYOfbX91xqtvSdFnXn3kzM6ZNoOWv6fa";
		
		// Get request token and token secret
		Twitter twitter = new TwitterFactory().getInstance();
    	twitter.setOAuthConsumer(consumerKey, consumerSecret);
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
		
//		String token = requestToken.getToken();
//		String tokenSecret = requestToken.getTokenSecret();
//		System.out.println("---Request Token:" + token);
//		System.out.println("---Request Token Secret:" + tokenSecret);

		// Redirect to authorization page
    	response.sendRedirect(requestToken.getAuthorizationURL());
    	return;
    	
//    	twitter = (Twitter) request.getSession().getAttribute("twitter");
//		requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
//		
//		System.out.println("Twitter:" + twitter);
//		token = requestToken.getToken();
//		tokenSecret = requestToken.getTokenSecret();
//		System.out.println("Request Token:" + token);
//		System.out.println("Request Token Secret:" + tokenSecret);
	}
}
