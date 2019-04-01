package springboot.config;

public class AnalysisConfig {
	/*
	 * default values
	 */
	private String apiKey = "ZJED5aYP3r0pVCnGY9BQNgBLlAT5jctj__e7xmsAXH54";
	private String version = "2017-10-13";
	private String url = "https://gateway-syd.watsonplatform.net/personality-insights/api";
	
	public String getApiKey() {
		return apiKey;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
