package springboot.util;

public class PlayerConfig {
	public static int getLevelUpExperience(int level) {
		// win = 20; lose = 5
		// 1 - 5: 10 * level)
		// 6 - 10: 15 * level)
		// 11 - 20: 35 * level / 2)
		// 21 - 30: 40 * level / 2)
		
		int experience = 0;
		if(level <= 5) {
			experience = 10 * level;
		} else if(level <= 10) {
			experience = 15 * level;
		} else if(level <= 20) {
			experience = 35 * level / 2;
		} else if(level <= 30) {
			experience = 40 * level / 2;
		}
		return experience;
	}
	
	// basic status
	public static void getBasicStatus(int level) {
		int hp = 0;
		int attack = 0;
		int defence = 0;
		int agility = 0; // rate
		int criticalStrike = 0; // rate 

		double[] rates = getRates(level);
		hp = (int) (150 + 20 * rates[0] * (level - 1));
		attack = (int) (30 + 10 * rates[1] * (level - 1));
		defence = (int) (15 + 8 * rates[2] * (level - 1));
		agility = (int) (10 + 5 * rates[3] * (level - 1));
		criticalStrike = (int) (15 + 5 * rates[4] * (level - 1)); 
		
		System.out.println(level + "   " + hp + "  " + attack + "  " + defence + "  " + agility + "  " + criticalStrike);
	}
	
	private static double[] getRates(int level) {
		double[] rates = {1 + level / 2.5, 1 + level / 7.5, 1 + level / 7.5, 1 + level / 12.5, 1 + level / 12.5};
		return rates;
	}
}
