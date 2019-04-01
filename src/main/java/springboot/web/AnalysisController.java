package springboot.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;
import springboot.service.impl.TwitterService;
import twitter4j.Twitter;
import twitter4j.auth.RequestToken;

@RestController
public class AnalysisController {
	@Autowired
	private TwitterService twitterService;
	private static Logger logger = LoggerFactory.getLogger(AnalysisController.class);

	// Authorization callback
	@GetMapping("/tweets")
	public String analysis(HttpServletRequest request, HttpServletResponse response) {
		logger.info("======Analysis Tweets======");
		// Get the tweets of user
		String result = null;
		String id = (String) request.getSession().getAttribute("id");
		if (id == null) {
			try {
				request.getRequestDispatcher("/404").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			logger.info("======Analysis Tweets End======");
		} else {
			Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
			RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
			String verifier = request.getParameter("oauth_verifier");
			result = twitterService.analysisTweets(id, twitter, verifier, requestToken);
			logger.info("======Analysis Tweets End======");
			logger.info("======Authorization End======");
		}

		return result;
	}

	@PutMapping("/reanalysis/{id}")
	public String reanalysis(@PathVariable String id) {
		logger.info("======Reanalysis Tweets======");
		String result = twitterService.reAnalysisTweets(id);
		logger.info("======Reanalysis Tweets End======");
		return result;
	}

}
