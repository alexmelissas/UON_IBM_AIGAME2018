package springboot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import springboot.service.TwitterService;
import twitter4j.Twitter;
import twitter4j.auth.RequestToken;

@RestController
public class AnalysisController {
	@Autowired
	private TwitterService twitterService;

	// Authorization callback
	@GetMapping("/tweets")
	public String analysis(HttpServletRequest request, HttpServletResponse response) {
		// Get the tweets of user
		// TODO 404 page
		String id = (String) request.getSession().getAttribute("id");
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
		String verifier = request.getParameter("oauth_verifier");

		return twitterService.analysisTweets(id, twitter, verifier, requestToken);
	}
}
