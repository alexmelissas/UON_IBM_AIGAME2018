package springboot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.domain.Player;
import springboot.service.impl.TwitterService;
import twitter4j.Twitter;
import twitter4j.auth.RequestToken;

/**
 * <p>
 * The AnalysisConctroller is used to handle analysis related requests.
 * </p>
 * 
 * @author chenyu
 *
 */
@RestController
public class AnalysisController {
	@Autowired
	private TwitterService twitterService;
	private static Logger logger = LoggerFactory.getLogger(AnalysisController.class);

	/**
	 * Authorization callback (set in Twitter Developer Account)
	 * 
	 * @param request  the request
	 * @param response the response
	 * @return the result
	 */
	@GetMapping("/tweets")
	public String analysis(HttpServletRequest request, HttpServletResponse response) {
		logger.info("======Analysis Tweets======");
		// Get the tweets of user
		String result = null;
		String id = (String) request.getSession().getAttribute("id");
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
		String verifier = request.getParameter("oauth_verifier");
		result = twitterService.analysisTweets(id, twitter, verifier, requestToken);
		logger.info("======Analysis Tweets End======");
		logger.info("======Authorization End======");

		return result;
	}

	/**
	 * Reanalysis the tweets
	 * 
	 * @param id the id
	 * @return the result
	 */
	@PutMapping("/reanalysis/{id}")
	public Player reanalysis(@PathVariable String id) {
		logger.info("======Reanalysis Tweets======");
		Player player = twitterService.reAnalysisTweets(id);
		logger.info("======Reanalysis Tweets End======");
		return player;
	}
}
