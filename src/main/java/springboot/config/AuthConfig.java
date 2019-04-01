package springboot.config;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class AuthConfig {
	private String consumerKey = "kLdFjFhJkiiWMb2SU4ZNtpGlf";
	private String consumerSecret = "VUXAlVuDbdOYDGhbImgYOfbX91xqtvSdFnXn3kzM6ZNoOWv6fa";
	private static AuthConfig instance = new AuthConfig();

	private AuthConfig() {
	}

	public static String getConsumerKey() {
		return instance.consumerKey;
	}

	public static String getConsumerKeySecret() {
		return instance.consumerSecret;
	}
	
	public static Twitter getTwitter() {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(instance.consumerKey, instance.consumerSecret);
		return twitter;
	}
}
