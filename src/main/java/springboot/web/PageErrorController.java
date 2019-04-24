package springboot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * The PageErrorController is used to handle error page.
 * </p>
 * 
 * @author chenyu
 *
 */
@RestController
public class PageErrorController {
	private static Logger logger = LoggerFactory.getLogger(PageErrorController.class);

	/**
	 * Handle the 404 page
	 * @param request the request
	 * @param response the response
	 * @return the information
	 */
	@RequestMapping("/404")
	public @ResponseBody String pageNotFound(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(404);
		logger.info(">>>Page Error: 404 - Page Not Found");
		return "404 Page Not Found.";
	}

	/**
	 * Handle the 500 page
	 * @param request the request
	 * @param response the response
	 * @return the information
	 */
	@RequestMapping("/500")
	public @ResponseBody String exception(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(500);
		logger.info(">>>Page Error: 500 - Internal Server Error");
		return "Sorry, authorization fails. Please try again later.";
	}
}
