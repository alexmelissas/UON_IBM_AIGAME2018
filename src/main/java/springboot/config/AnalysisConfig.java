package springboot.config;

/**
 * <p>
 * The AnalysisConfig class is used to configure the api key of IBM Personality
 * Insights Service.
 * </p>
 * 
 * @author chenyu
 *
 */
public class AnalysisConfig {
	private static String apiKey = "ZJED5aYP3r0pVCnGY9BQNgBLlAT5jctj__e7xmsAXH54";
	private static String version = "2017-10-13";
	private static String url = "https://gateway-syd.watsonplatform.net/personality-insights/api";

	/**
	 * @return the apiKey
	 */
	public static String getApiKey() {
		return apiKey;
	}

	/**
	 * @return the version
	 */
	public static String getVersion() {
		return version;
	}

	/**
	 * @return the url
	 */
	public static String getUrl() {
		return url;
	}
}