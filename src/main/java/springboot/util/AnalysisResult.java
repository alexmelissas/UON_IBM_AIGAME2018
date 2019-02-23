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
    	JsonArray personality = jsonObject.getAsJsonArray("personality");

    	double openess = personality.get(0).getAsJsonObject().get("percentile").getAsDouble();
    	double conscientiousness = personality.get(1).getAsJsonObject().get("percentile").getAsDouble();
    	double extraversion = personality.get(2).getAsJsonObject().get("percentile").getAsDouble();
    	double agreeableness = personality.get(3).getAsJsonObject().get("percentile").getAsDouble();
    	double neuroticism = personality.get(4).getAsJsonObject().get("percentile").getAsDouble();

//    	System.out.println("openess: " + openess);
//    	System.out.println("conscientiousness: " + conscientiousness);
//    	System.out.println("extraversion: " + extraversion);
//    	System.out.println("agreeableness: " + agreeableness);
//    	System.out.println("neuroticism: " + neuroticism);
//    	
    	double openessIdeal = ideal.getOpeness();
    	double conscientiousnessIdeal = ideal.getConscientiousness();
    	double extraversionIdeal = ideal.getExtraversion();
    	double agreeablenessIdeal = ideal.getAgreeableness();
    	double neuroticismIdeal = ideal.getEmotionalrange();
    	
    	
    	// TODO FIX  THE SIMILARITY
    	// COMPARE THE SIMILARITY
    	double openessSimilarity = (1 - Math.abs(openessIdeal - openess));
    	double conscientiousnessSimilarity = (1 - Math.abs(conscientiousnessIdeal - conscientiousness));
    	double extraversionSimilarity = (1 - Math.abs(extraversionIdeal - extraversion));
    	double agreeablenessSimilarity = (1 - Math.abs(agreeablenessIdeal - agreeableness));
    	double neuroticismSmililarity = (1 - Math.abs(neuroticismIdeal - neuroticism));
    	
    	System.out.println("openess: " + openessSimilarity);
    	System.out.println("conscientiousness: " + conscientiousnessSimilarity);
    	System.out.println("extraversion: " + extraversionSimilarity);
    	System.out.println("agreeableness: " + agreeablenessSimilarity);
    	System.out.println("neuroticism: " + neuroticismSmililarity);
    	
    	double averageSimilarity = (openessSimilarity + conscientiousnessSimilarity + extraversionSimilarity + 
    								agreeablenessSimilarity + neuroticismSmililarity) / 5;
    	
    	// balance the benefit of similarity of personality
    	if(averageSimilarity < 0.3) {
    		averageSimilarity = 0.3;
    	} else if(averageSimilarity > 0.7) {
    		averageSimilarity = 0.7;
    	}
    	
    	System.out.println("------AVERAGE VALUE------");
    	System.out.println(averageSimilarity);
//    	double hp = (conscientiousness.get(4).getAsJsonObject().get("percentile").getAsDouble()
//    				+ conscientiousness.get(5).getAsJsonObject().get("percentile").getAsDouble()
//    				+ extraversion.get(2).getAsJsonObject().get("percentile").getAsDouble()
//    				+ needs.get(2).getAsJsonObject().get("percentile").getAsDouble()
//    				+ values.get(2).getAsJsonObject().get("percentile").getAsDouble())
//    				- neuroticism.get(2).getAsJsonObject().get("percentile").getAsDouble();
//    	System.out.println("hp: " + hp);
//    	
//    	double attack = (openess.get(5).getAsJsonObject().get("percentile").getAsDouble()
//					+ conscientiousness.get(0).getAsJsonObject().get("percentile").getAsDouble()
//					+ needs.get(0).getAsJsonObject().get("percentile").getAsDouble()
//					+ values.get(3).getAsJsonObject().get("percentile").getAsDouble())
//					- agreeableness.get(4).getAsJsonObject().get("percentile").getAsDouble();
//    	System.out.println("attack: " + attack);
//    	
//    	double defence = (conscientiousness.get(1).getAsJsonObject().get("percentile").getAsDouble()
//					+ agreeableness.get(3).getAsJsonObject().get("percentile").getAsDouble()
//					+ needs.get(1).getAsJsonObject().get("percentile").getAsDouble()
//					+ values.get(0).getAsJsonObject().get("percentile").getAsDouble())
//					- neuroticism.get(1).getAsJsonObject().get("percentile").getAsDouble();
//    	System.out.println("defence: " + defence);
//    	
//    	double agility = (openess.get(0).getAsJsonObject().get("percentile").getAsDouble()
//					+ extraversion.get(0).getAsJsonObject().get("percentile").getAsDouble()
//					+ needs.get(6).getAsJsonObject().get("percentile").getAsDouble()
//					+ values.get(1).getAsJsonObject().get("percentile").getAsDouble())
//					- neuroticism.get(5).getAsJsonObject().get("percentile").getAsDouble();
//    	System.out.println("aqility: " + agility);
//    	
//    	double intelligence = (openess.get(4).getAsJsonObject().get("percentile").getAsDouble()
//					+ conscientiousness.get(3).getAsJsonObject().get("percentile").getAsDouble()
//					+ extraversion.get(1).getAsJsonObject().get("percentile").getAsDouble()
//					+ needs.get(11).getAsJsonObject().get("percentile").getAsDouble()
//					+ values.get(4).getAsJsonObject().get("percentile").getAsDouble())
//					- neuroticism.get(0).getAsJsonObject().get("percentile").getAsDouble();
//    	System.out.println("intelligence: " + intelligence);
    	
    	// TODO Multiple the basic value
    	// basic status for each level and the experience of level needed.
    	
    	role = new Role(100, 10, 5, 10, 5);
    	role.setFactor(averageSimilarity + 1);
    	role.applyPersonality();
    	
    	return role;
    }
    
    public Role generateNormalRole() {
    	role = new Role(100, 10, 5, 10, 5);
    	role.setFactor(1);
    	return role;
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
