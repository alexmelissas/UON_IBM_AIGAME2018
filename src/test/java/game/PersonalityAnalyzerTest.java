package game;

import springboot.service.PersonalityInsightService;

public class PersonalityAnalyzerTest {
	public static void main(String[] args) {
		PersonalityInsightService personalityAnalyzer = new PersonalityInsightService();
		
		personalityAnalyzer.analysis();
	}
}
