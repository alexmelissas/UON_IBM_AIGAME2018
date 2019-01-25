package springboot.util;

import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

public class AnalysisResult {

    private final Profile profile;

    public AnalysisResult(Profile profile) {
    	this.profile = profile;
    }

	public Profile getProfile() {
		return profile;
	}
}
