package springboot.config;

/**
 * <p>
 * The PlayerConfig class is used to configure the experience and attributes of
 * players for each level.
 * </p>
 * 
 * @author chenyu
 *
 */
public class PlayerConfig {
	/**
	 * Get the experience needed to next level
	 * 
	 * @param level the goal level
	 * @return the experience needed
	 */
	public static int getLevelUpExperience(int level) {
		// win = 20; lose = 5
		// 1 - 5: 10 * level)
		// 6 - 10: 15 * level)
		// 11 - 20: 35 * level / 2)
		// 21 - 30: 40 * level / 2)

		// 1 - 5: 0 - 0.5 days to level up
		// 5 - 10: 0.5 -> 1 days to level up
		// 11 - 20: 1 -> 1.5 days to level up
		// 21 - 30: 1.5 -> 2 days to level up
		int experience = 0;
		double a = 0;
		double b = 15;
		double c = 40;
		int x = level;
		if (x <= 5) {

		} else if (x <= 10) {
			a = 1;
			b = 10;
			c = 20;
		} else if (x <= 20) {
			a = 0.7;
			b = 10;
			c = 30;
		} else {
			a = 0.5;
			b = 8;
			c = 120;
		}
		experience = (int) (a * x * x + b * x + c);

		return experience;
	}

	/**
	 * Get the basic attributes of player according to the level
	 * 
	 * @param level the level of player
	 * @return the array contains the basic value of attributes
	 */
	public static int[] getBasicStatus(int level) {
		int hp = 0;
		int attack = 0;
		int defence = 0;
		int agility = 0; // rate
		int criticalStrike = 0; // rate

		double a = 1.2;
		double b = 1;
		double c = 200;
		int x = level + 14;
		hp = (int) (a * x * x + b * x + c);
		a = 0.2;
		b = 0.2;
		c = 30;
		attack = (int) (a * x * x + b * x + c);
		a = 0.15;
		b = 0.15;
		c = 15;
		defence = (int) (a * x * x + b * x + c);
		a = 0.05;
		b = 0.5;
		c = 5;
		agility = (int) ((a * x * x + b * x + c) * 0.6);
		a = 0.1;
		b = 0.1;
		c = 10;
		criticalStrike = (int) (a * x * x + b * x + c);

		int[] array = { hp, attack, defence, agility, criticalStrike };
		return array;
	}
}
