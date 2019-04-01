package springboot.domain;

import springboot.config.PlayerConfig;

/**
 * <p>
 * The Bot class represents the bot character of the game It extends the @link
 * BasicCharacter} class
 * </p>
 * 
 * @author Yu Chen
 *
 */
public class Bot extends BasicCharacter {

	/**
	 * Constructor
	 * 
	 * @param level  the level of the bot
	 * @param factor the buff of the bot
	 */
	public Bot(int level, double factor) {
		this.level = level;
		this.setAttributes(PlayerConfig.getBasicStatus(level));
		this.factor = factor;
		this.characterName = "Bot";
	}
}
