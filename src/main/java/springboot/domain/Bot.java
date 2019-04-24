package springboot.domain;

import java.io.Serializable;

import springboot.config.PlayerConfig;

/**
 * <p>
 * The Bot class represents the bot character of the game It extends the
 * {@link BasicCharacter} class.
 * </p>
 * 
 * @author chenyu
 *
 */
public class Bot extends BasicCharacter implements Serializable {

	private static final long serialVersionUID = -1329586083529614902L;

	/**
	 * Constructor
	 * 
	 * @param level  the level of the bot
	 * @param factor the buff of the bot
	 */
	public Bot(int level, double factor) {
		this.setLevel(level);
		this.setAttributes(PlayerConfig.getBasicStatus(level));
		this.setFactor(factor);
		this.setCharacterName("Bot");
	}
}
