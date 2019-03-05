package springboot.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import springboot.domain.Ideal;
import springboot.domain.Player;

public class AnalysisResult {
	private JsonObject jsonObject;

	public AnalysisResult() {
	}

	public void generateFactor(Ideal ideal, Player player) {
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

		double averageSimilarity = (openessSimilarity + conscientiousnessSimilarity + extraversionSimilarity
				+ agreeablenessSimilarity + neuroticismSmililarity) / 5;

		// balance the benefit of similarity of personality
		if (averageSimilarity < 0.3) {
			averageSimilarity = 0.3;
		} else if (averageSimilarity > 0.7) {
			averageSimilarity = 0.7;
		}

		System.out.println("------AVERAGE VALUE------");
		System.out.println(averageSimilarity);

		player.setFactor(averageSimilarity + 1);
	}

	public void generateNormalFactor(Player player) {
		player.setFactor(1);
	}

	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonResult) {
		jsonObject = new JsonParser().parse(jsonResult).getAsJsonObject();
	}
}
