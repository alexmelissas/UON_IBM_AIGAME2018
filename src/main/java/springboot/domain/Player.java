package springboot.domain;

import java.io.Serializable;

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
 * @author chenyu
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Player extends BasicCharacter implements Serializable {

	private static final long serialVersionUID = 3382275482902910220L;

	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	private String id;

	@Column(name = "money", columnDefinition = "INT")
	private int money = 0;

	@Column(name = "experience", columnDefinition = "INT")
	private int experience = 0;

	@Column(name = "exptolevel", columnDefinition = "INT")
	private int exptolevel = PlayerConfig.getLevelUpExperience(this.getLevel());

	@Column(name = "win", columnDefinition = "INT")
	private int win = 0;

	@Column(name = "lose", columnDefinition = "INT")
	private int lose = 0;

	@Column(name = "groupnum", columnDefinition = "INT")
	private int group = -1;

	/**
	 * Constructor
	 */
	public Player() {
	}

	/**
	 * Constructor
	 * 
	 * @param id the id
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
	 * @param group          group number
	 */
	public Player(String id, String characterName, int level, int hp, int attack, int defense, int agility,
			int criticalStrike, int money, int experience, int exptolevel, double factor, int sword, int shield,
			int armour, int win, int lose, int group) {
		super(characterName, level, hp, attack, defense, agility, criticalStrike, factor, sword, shield, armour);
		this.id = id;
		this.money = money;
		this.experience = experience;
		this.exptolevel = exptolevel;
		this.win = win;
		this.lose = lose;
		this.group = group;
	}

	/**
	 * Check if the player can level up If so, update the information of the player
	 */
	public void checklevelUp() {
		if (this.getLevel() >= 30) {
			// MAX LEVEL
			return;
		}

		if (this.exptolevel <= this.experience) {
			int exceed = this.experience - this.exptolevel;
			this.setLevel(this.getLevel() + 1);
			this.setAttributes(PlayerConfig.getBasicStatus(this.getLevel()));
			this.setExptolevel(PlayerConfig.getLevelUpExperience(this.getLevel()));
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

	/**
	 * @return the group
	 */
	public int getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(int group) {
		this.group = group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [getId()=" + getId() + ", getMoney()=" + getMoney() + ", getExperience()=" + getExperience()
				+ ", getExptolevel()=" + getExptolevel() + ", getWin()=" + getWin() + ", getLose()=" + getLose()
				+ ", getGroup()=" + getGroup() + ", getCharacterName()=" + getCharacterName() + ", getLevel()="
				+ getLevel() + ", getHp()=" + getHp() + ", getAttack()=" + getAttack() + ", getDefense()="
				+ getDefense() + ", getAgility()=" + getAgility() + ", getCriticalStrike()=" + getCriticalStrike()
				+ ", getFactor()=" + getFactor() + ", getSword()=" + getSword() + ", getShield()=" + getShield()
				+ ", getArmour()=" + getArmour() + "]";
	}
}
