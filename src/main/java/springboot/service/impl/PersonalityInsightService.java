package springboot.service.impl;

import java.util.HashMap;
import java.util.Map;

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

/**
 * <p>
 * The PersonalityInsightService provide the analysis of tweets based on IBM
 * Personality Insights API
 * </p>
 * 
 * @author chenyu
 *
 */
@Service("piService")
public class PersonalityInsightService {
	private PersonalityInsights personalityInsights;
	private Content content;
	private static Logger logger = LoggerFactory.getLogger(PersonalityInsightService.class);

	/**
	 * The personality insight analysis service
	 */
	public PersonalityInsightService() {
		/*
		 * authentication token-based Identity and Access Management(IAM)
		 */
		IamOptions options = new IamOptions.Builder().apiKey(AnalysisConfig.getApiKey()).build();
		personalityInsights = new PersonalityInsights(AnalysisConfig.getVersion(), options);
		// Prevent the IBM to store the data for the request
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Watson-Learning-Opt-Out", "true");
		personalityInsights.setDefaultHeaders(headers);
		personalityInsights.setEndPoint(AnalysisConfig.getUrl());
	}

	/**
	 * Analysis the input text
	 * 
	 * @param tweetsContent the tweets
	 * @return the profile
	 */
	public Profile analysis(StringBuilder tweetsContent) {
		// Load content from ContentLoader
		ContentItem contentItem = new ContentItem.Builder().content(tweetsContent.toString()).build();
		this.content = new Content.Builder().addContentItem(contentItem).build();

		ProfileOptions profileOptions = new ProfileOptions.Builder().content(content).consumptionPreferences(true)
				.rawScores(true).build();
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
