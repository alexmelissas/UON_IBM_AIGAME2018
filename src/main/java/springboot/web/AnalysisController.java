package springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.service.PersonalityInsightService;
import springboot.util.AnalysisResult;

@RestController
public class AnalysisController {
	@Autowired
	private PersonalityInsightService piService; 
	
    @RequestMapping("/analysis")
    public AnalysisResult anlysis() {
    	return new AnalysisResult(piService.analysis());
    }
}
