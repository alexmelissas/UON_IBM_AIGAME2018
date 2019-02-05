package springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageErrorController {
	@RequestMapping("/404")
	public @ResponseBody String pageNotFound() {
		return "404 Page Not Found.";
	}
	
	@RequestMapping("/500")
	public @ResponseBody String exception() {
		return "Sorrry, authorization fails. Please try again later.";
	}
}
