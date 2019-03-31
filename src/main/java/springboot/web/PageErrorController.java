package springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageErrorController {
	private static Logger logger = LoggerFactory.getLogger(PageErrorController.class);
	
	@RequestMapping("/404")
	public @ResponseBody String pageNotFound() {
		logger.info(">>>Page Error: 404 - Page Not Found");
		return "404 Page Not Found.";
	}
	
	@RequestMapping("/500")
	public @ResponseBody String exception() {
		logger.info(">>>Page Error: 500 - Internal Server Error");
		return "Sorrry, authorization fails. Please try again later.";
	}
}
