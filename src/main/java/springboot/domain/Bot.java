package springboot.domain;

import springboot.util.PlayerConfig;

public class Bot extends BasicCharacter {
	
	public Bot(int level, double factor) {
		this.level = level;
		this.setAttributes(PlayerConfig.getBasicStatus(level));
		this.factor = factor;
		this.characterName = "Bot";
	}
}
