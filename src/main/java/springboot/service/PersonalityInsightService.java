package springboot.service;

import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Content;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ContentItem;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import springboot.util.AnalysisConfig;
import springboot.util.ContentLoader;

@Service("piService")
public class PersonalityInsightService {
	private AnalysisConfig config = new AnalysisConfig();
	private PersonalityInsights personalityInsights;
	private Content content;
	
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
		
		ProfileOptions profileOptions = new ProfileOptions.Builder().content(content).consumptionPreferences(true).rawScores(true).build();
		Profile profile = personalityInsights.profile(profileOptions).execute();
		
		// result profile in JSON
		return profile;
	}
}
