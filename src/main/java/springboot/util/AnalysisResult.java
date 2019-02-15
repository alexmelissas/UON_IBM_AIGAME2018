package springboot.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import springboot.domain.Ideal;
import springboot.domain.Role;

public class AnalysisResult {
    private JsonObject jsonObject;
    private Role role = null;
    
    public AnalysisResult() {
    }
    
    public AnalysisResult(String jsonResult) {
    	jsonObject = new JsonParser().parse(jsonResult).getAsJsonObject();
    }

    public Role generateRole(Ideal ideal) {
    	// TODO transfer json data into attributes
    	// Compare the similarity
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
    				- neuroticism.get(2).getAsJsonObject().get("percentile").getAsDouble();
    	System.out.println("hp: " + hp);
    	
    	double attack = (openess.get(5).getAsJsonObject().get("percentile").getAsDouble()
					+ conscientiousness.get(0).getAsJsonObject().get("percentile").getAsDouble()
					+ needs.get(0).getAsJsonObject().get("percentile").getAsDouble()
					+ values.get(3).getAsJsonObject().get("percentile").getAsDouble())
					- agreeableness.get(4).getAsJsonObject().get("percentile").getAsDouble();
    	System.out.println("attack: " + attack);
    	
    	double defence = (conscientiousness.get(1).getAsJsonObject().get("percentile").getAsDouble()
					+ agreeableness.get(3).getAsJsonObject().get("percentile").getAsDouble()
					+ needs.get(1).getAsJsonObject().get("percentile").getAsDouble()
					+ values.get(0).getAsJsonObject().get("percentile").getAsDouble())
					- neuroticism.get(1).getAsJsonObject().get("percentile").getAsDouble();
    	System.out.println("defence: " + defence);
    	
    	double agility = (openess.get(0).getAsJsonObject().get("percentile").getAsDouble()
					+ extraversion.get(0).getAsJsonObject().get("percentile").getAsDouble()
					+ needs.get(6).getAsJsonObject().get("percentile").getAsDouble()
					+ values.get(1).getAsJsonObject().get("percentile").getAsDouble())
					- neuroticism.get(5).getAsJsonObject().get("percentile").getAsDouble();
    	System.out.println("aqility: " + agility);
    	
    	double intelligence = (openess.get(4).getAsJsonObject().get("percentile").getAsDouble()
					+ conscientiousness.get(3).getAsJsonObject().get("percentile").getAsDouble()
					+ extraversion.get(1).getAsJsonObject().get("percentile").getAsDouble()
					+ needs.get(11).getAsJsonObject().get("percentile").getAsDouble()
					+ values.get(4).getAsJsonObject().get("percentile").getAsDouble())
					- neuroticism.get(0).getAsJsonObject().get("percentile").getAsDouble();
    	System.out.println("intelligence: " + intelligence);
    	
    	// TODO compare similarity
    	role = new Role(1, 1, 1, 1, 1);
    	role.setJsonResult(jsonObject.toString());
    	
    	return role;
    }
    
    public Role generateNormalRole() {
    	role = new Role(1, 1, 1, 1, 1);
    	role.setJsonResult(jsonObject.toString());
    	
    	return role;
    }
    
    public Character balanceRole(double hp, double attack, double defence, double agility, double intelligence) {
    	
    	return null;
    }
    
	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonData) {
		jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
