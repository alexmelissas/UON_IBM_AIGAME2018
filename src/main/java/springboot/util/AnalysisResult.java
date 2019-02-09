package springboot.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

import springboot.domain.Character;

public class AnalysisResult {
    private final Profile profile;
    private JsonObject jsonObject;
    private Character character = null;
    
    public AnalysisResult(Profile profile) {
    	this.profile = profile;
    	jsonObject = new JsonParser().parse(profile.toString()).getAsJsonObject();
    	System.out.println(jsonObject);
    }

    public void generateCharacter() {
    	// TODO transfer json data into attributes
    	character = new Character();
    	JsonArray personality = jsonObject.getAsJsonArray("personality");
    	JsonArray needs = jsonObject.getAsJsonArray("needs");
    	JsonArray values = jsonObject.getAsJsonArray("values");

    	JsonArray openess = personality.get(0).getAsJsonObject().getAsJsonArray("children");
    	JsonArray conscientiousness = personality.get(1).getAsJsonObject().getAsJsonArray("children");
    	JsonArray extraversion = personality.get(2).getAsJsonObject().getAsJsonArray("children");
    	JsonArray agreeableness = personality.get(3).getAsJsonObject().getAsJsonArray("children");
    	JsonArray neuroticism = personality.get(4).getAsJsonObject().getAsJsonArray("children");

    	double hp = (conscientiousness.get(4).getAsJsonObject().get("percentile").getAsDouble()
    				+ conscientiousness.get(5).getAsJsonObject().get("percentile").getAsDouble()
    				+ extraversion.get(2).getAsJsonObject().get("percentile").getAsDouble()
    				+ needs.get(2).getAsJsonObject().get("percentile").getAsDouble()
    				+ values.get(2).getAsJsonObject().get("percentile").getAsDouble())
    				* (1 - neuroticism.get(2).getAsJsonObject().get("percentile").getAsDouble());
    	System.out.println("hp: " + hp);
    	
    	double attack = (openess.get(5).getAsJsonObject().get("percentile").getAsDouble()
					+ conscientiousness.get(0).getAsJsonObject().get("percentile").getAsDouble()
					+ needs.get(0).getAsJsonObject().get("percentile").getAsDouble()
					+ values.get(3).getAsJsonObject().get("percentile").getAsDouble())
					* (1 - agreeableness.get(4).getAsJsonObject().get("percentile").getAsDouble());
    	System.out.println("attack: " + attack);
    	
    	double defence = (conscientiousness.get(1).getAsJsonObject().get("percentile").getAsDouble()
					+ agreeableness.get(3).getAsJsonObject().get("percentile").getAsDouble()
					+ needs.get(1).getAsJsonObject().get("percentile").getAsDouble()
					+ values.get(0).getAsJsonObject().get("percentile").getAsDouble())
					* (1 - neuroticism.get(1).getAsJsonObject().get("percentile").getAsDouble());
    	System.out.println("defence: " + defence);
    	
    	double agility = (openess.get(0).getAsJsonObject().get("percentile").getAsDouble()
					+ extraversion.get(0).getAsJsonObject().get("percentile").getAsDouble()
					+ needs.get(6).getAsJsonObject().get("percentile").getAsDouble()
					+ values.get(1).getAsJsonObject().get("percentile").getAsDouble())
					* (1 - neuroticism.get(5).getAsJsonObject().get("percentile").getAsDouble());
    	System.out.println("aqility: " + agility);
    	
    	double intelligence = (openess.get(4).getAsJsonObject().get("percentile").getAsDouble()
					+ conscientiousness.get(3).getAsJsonObject().get("percentile").getAsDouble()
					+ extraversion.get(1).getAsJsonObject().get("percentile").getAsDouble()
					+ needs.get(11).getAsJsonObject().get("percentile").getAsDouble()
					+ values.get(4).getAsJsonObject().get("percentile").getAsDouble())
					* (1 - neuroticism.get(0).getAsJsonObject().get("percentile").getAsDouble());
    	System.out.println("intelligence: " + intelligence);
    }
    
    public Character balanceCharacter(double hp, double attack, double defence, double agility, double intelligence) {
    	
    	return null;
    }
    
	public Profile getProfile() {
		return profile;
	}

	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonData) {
		jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}
}
