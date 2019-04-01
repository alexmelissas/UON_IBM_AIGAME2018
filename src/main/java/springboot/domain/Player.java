package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import springboot.config.PlayerConfig;

/**
 * <p>
 * The Player class represents the real character of the player. It extends the
 * {@link BasicCharacter} class.
 * </p>
 * <p>
 * This class class contains more information about the player.
 * </p>
 * 
 * @author Yu Chen
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Player extends BasicCharacter {
	/**
	 * id
	 */
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	public String id;

	/**
	 * money
	 */
	@Column(name = "money", columnDefinition = "INT")
	public int money = 0;

	/**
	 * experience
	 */
	@Column(name = "experience", columnDefinition = "INT")
	public int experience = 0;

	/**
	 * experience needed to level up
	 */
	@Column(name = "exptolevel", columnDefinition = "INT")
	public int exptolevel = PlayerConfig.getLevelUpExperience(this.level);

	/**
	 * the number of wins
	 */
	@Column(name = "win", columnDefinition = "INT")
	public int win = 0;

	/**
	 * the number of loses
	 */
	@Column(name = "lose", columnDefinition = "INT")
	public int lose = 0;

	/**
	 * Constructor
	 */
	public Player() {
	}

	// TODO Update function which update the status of player
	// Constructor to generate the level 1 player
	// Level up function
	// QUESTION - How to calculate the value after own items??? --- further
	// discussion
	// WHAT IS THE STRUCTURE OF INVENTORY?

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public Player(String id) {
		this.id = id;
	}

	/**
	 * Constructor
	 * 
	 * @param id             id
	 * @param characterName  character name
	 * @param level          level
	 * @param hp             hp
	 * @param attack         attack
	 * @param defense        defense
	 * @param agility        agility
	 * @param criticalStrike critical strike
	 * @param money          money
	 * @param experience     experience
	 * @param exptolevel     experience needed to level up
	 * @param factor         similarity
	 * @param sword          sword level
	 * @param shield         shield level
	 * @param armour         armour level
	 * @param win            number of wins
	 * @param lose           number of loses
	 */
	public Player(String id, String characterName, int level, int hp, int attack, int defense, int agility,
			int criticalStrike, int money, int experience, int exptolevel, double factor, int sword, int shield,
			int armour, int win, int lose) {
		super(characterName, level, hp, attack, defense, agility, criticalStrike, factor, sword, shield, armour);
		this.id = id;
		this.money = money;
		this.experience = experience;
		this.exptolevel = exptolevel;
		this.win = win;
		this.lose = lose;
	}

	/**
	 * Check if the player can level up If so, update the information of the player
	 */
	public void checklevelUp() {
		if (this.level >= 30) {
			// MAX LEVEL
			return;
		}

		if (this.exptolevel <= this.experience) {
			int exceed = this.experience - this.exptolevel;
			this.setLevel(this.getLevel() + 1);
			this.setAttributes(PlayerConfig.getBasicStatus(this.level));
			this.setExptolevel(PlayerConfig.getLevelUpExperience(this.level));
			this.applyPersonality();
			this.setExperience(exceed > 0 ? exceed : 0);
		}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * @param experience the experience to set
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}

	/**
	 * @return the experience needed
	 */
	public int getExptolevel() {
		return exptolevel;
	}

	/**
	 * @param exptolevel the needed experience to set
	 */
	public void setExptolevel(int exptolevel) {
		this.exptolevel = exptolevel;
	}

	/**
	 * @return the win
	 */
	public int getWin() {
		return win;
	}

	/**
	 * @param win the number of wins to set
	 */
	public void setWin(int win) {
		this.win = win;
	}

	/**
	 * @return the lose
	 */
	public int getLose() {
		return lose;
	}

	/**
	 * @param lose the number of loses to set
	 */
	public void setLose(int lose) {
		this.lose = lose;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", money=" + money + ", experience=" + experience + ", exptolevel=" + exptolevel
				+ ", win=" + win + ", lose=" + lose + ", characterName=" + characterName + ", level=" + level + ", hp="
				+ hp + ", attack=" + attack + ", defense=" + defense + ", agility=" + agility + ", criticalStrike="
				+ criticalStrike + ", factor=" + factor + ", sword=" + sword + ", shield=" + shield + ", armour="
				+ armour + "]";
	}
}
