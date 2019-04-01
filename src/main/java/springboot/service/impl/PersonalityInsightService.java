package springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Content;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ContentItem;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.service.exception.BadRequestException;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import springboot.config.AnalysisConfig;
import springboot.util.ContentLoader;

@Service("piService")
public class PersonalityInsightService {
	private AnalysisConfig config = new AnalysisConfig();
	private PersonalityInsights personalityInsights;
	private Content content;
	private static Logger logger = LoggerFactory.getLogger(PersonalityInsightService.class); 
	
	public PersonalityInsightService() {
		/*
		 * authentication
		 * token-based Identity and Access Management(IAM) 
		 */
		IamOptions options = new IamOptions.Builder().apiKey(config.getApiKey()).build();
		personalityInsights = new PersonalityInsights(config.getVersion(), options);
		personalityInsights.setEndPoint(config.getUrl());
	}
	
	public Profile analysis(ContentLoader contentLoader) {
		// Load content from ContentLoader
		ContentItem contentItem = new ContentItem.Builder().content(contentLoader.getInput()).build();
		content = new Content.Builder().addContentItem(contentItem).build();
		
		ProfileOptions profileOptions = new ProfileOptions.Builder()
															.content(content)
															.consumptionPreferences(true)
															.rawScores(true)
															.build();
		Profile profile;
		try {
			profile = personalityInsights.profile(profileOptions).execute();
			logger.info(">>>Valid analysis");
		} catch (BadRequestException badRequestException) {
			// tweets is less than 100 words
			profile = new Profile();
			logger.info(">>>Insufficient word count");
			return profile;
		}
		
		// result profile in JSON
		return profile;
	}
	
}
