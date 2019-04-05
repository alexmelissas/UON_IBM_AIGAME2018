package springboot.util;

import java.util.Arrays;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import springboot.domain.Ideal;
import springboot.domain.Player;

/**
 * <p>
 * The AnalysisResult is used to store the related data for analysis. And it
 * will generate the factor according to the ideal personality and real
 * personality.
 * </p>
 * 
 * @author chenyu
 *
 */
public class AnalysisResult {
	private JsonObject jsonObject;

	/**
	 * Constructor
	 */
	public AnalysisResult() {
	}

	/**
	 * Generate the similarity according to the ideal and player
	 * 
	 * @param ideal  the ideal
	 * @param player the player
	 */
	public void generateFactor(Ideal ideal, Player player) {
		JsonArray personality = jsonObject.getAsJsonArray("personality");

		double openess = personality.get(0).getAsJsonObject().get("percentile").getAsDouble();
		double conscientiousness = personality.get(1).getAsJsonObject().get("percentile").getAsDouble();
		double extraversion = personality.get(2).getAsJsonObject().get("percentile").getAsDouble();
		double agreeableness = personality.get(3).getAsJsonObject().get("percentile").getAsDouble();
		double neuroticism = personality.get(4).getAsJsonObject().get("percentile").getAsDouble();

		double openessIdeal = ideal.getOpeness();
		double conscientiousnessIdeal = ideal.getConscientiousness();
		double extraversionIdeal = ideal.getExtraversion();
		double agreeablenessIdeal = ideal.getAgreeableness();
		double neuroticismIdeal = ideal.getEmotionalrange();

		// Compare the similarity
		double openessSimilarity = (1 - Math.abs(openessIdeal - openess));
		double conscientiousnessSimilarity = (1 - Math.abs(conscientiousnessIdeal - conscientiousness));
		double extraversionSimilarity = (1 - Math.abs(extraversionIdeal - extraversion));
		double agreeablenessSimilarity = (1 - Math.abs(agreeablenessIdeal - agreeableness));
		double neuroticismSmililarity = (1 - Math.abs(neuroticismIdeal - neuroticism));

		// Weighted average (higher similarity has lower weight)
		// 0.1 0.1 0.2 0.3 0.3
		double[] weight = { 0.05, 0.05, 0.25, 0.3, 0.35 };
		double[] similarity = { openessSimilarity, conscientiousnessSimilarity, extraversionSimilarity,
				agreeablenessSimilarity, neuroticismSmililarity };
		Arrays.sort(similarity);
		double averageSimilarity = 0;
		for (int i = 0; i < similarity.length; i++) {
			averageSimilarity += weight[4 - i] * similarity[i];
		}

		// Balance the benefit of similarity of personality
		if (averageSimilarity < 0.1) {
			averageSimilarity = 0.1;
		} else if (averageSimilarity > 0.6) {
			averageSimilarity = 0.6;
		}

		player.setFactor(averageSimilarity + 1);
	}

	/**
	 * Generator the normal factor
	 * 
	 * @param player the player
	 */
	public void generateNormalFactor(Player player) {
		player.setFactor(1);
	}

	/**
	 * Get the json result as JsonObject
	 * 
	 * @return a JsonObject
	 */
	public JsonObject getJsonObject() {
		return jsonObject;
	}

	/**
	 * Set the json result as a JsonObjeect
	 * 
	 * @param jsonResult the json result
	 */
	public void setJsonObject(String jsonResult) {
		jsonObject = new JsonParser().parse(jsonResult).getAsJsonObject();
	}
}
