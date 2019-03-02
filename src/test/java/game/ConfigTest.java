package game;

import springboot.util.PlayerConfig;

public class ConfigTest {
	public static void main(String[] args) {
		System.out.println("level   hp   attack   defence   agility   criticalStrike");
		int count = 0;
		for(int i = 1; i <= 30; i++) {
//			System.out.println("level: " + i + " experience: " + PlayerConfig.getLevelUpExperience(i) + " number: " + PlayerConfig.getLevelUpExperience(i) / 20);
//			count += PlayerConfig.getLevelUpExperience(i);
			PlayerConfig.getBasicStatus(i);
		}
//		System.out.println(count / 20);
	}
}
