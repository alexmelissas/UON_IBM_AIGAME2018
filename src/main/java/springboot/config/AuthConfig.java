package springboot.config;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * <p>
 * The AuthConfig class is used to configure the consumer key for Twitter API.
 * </p>
 * 
 * @author chenyu
 *
 */
public class AuthConfig {
	private static String consumerKey = "kLdFjFhJkiiWMb2SU4ZNtpGlf";
	private static String consumerSecret = "VUXAlVuDbdOYDGhbImgYOfbX91xqtvSdFnXn3kzM6ZNoOWv6fa";

	/**
	 * @return the consumerKey
	 */
	public static String getConsumerKey() {
		return consumerKey;
	}

	/**
	 * @return the consumerSecret
	 */
	public static String getConsumerSecret() {
		return consumerSecret;
	}

	/**
	 * Get the Twitter instance from TwitterFactory
	 * 
	 * @return the Twitter instance (with consumer key and secret)
	 */
	public static Twitter getTwitter() {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		return twitter;
	}
}
