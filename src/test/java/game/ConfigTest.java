package game;

import springboot.util.PlayerConfig;

public class ConfigTest {
	public static void main(String[] args) {
		System.out.println("level   hp   attack   defence   agility   criticalStrike");
		for(int i = 1; i <= 30; i++) {
			System.out.println(PlayerConfig.getLevelUpExperience(i) / 20);
//			PlayerConfig.getBasicStatus(i);
		}
	}
}
