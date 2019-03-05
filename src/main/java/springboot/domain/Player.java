package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import springboot.util.PlayerConfig;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  
public class Player extends BasicCharacter {
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	public String id;

	@Column(name = "money", columnDefinition = "INT")
	public int money = 0;

	@Column(name = "experience", columnDefinition = "INT")
	public int experience = 0;

	@Column(name = "exptolevel", columnDefinition = "INT")
	public int exptolevel = PlayerConfig.getLevelUpExperience(this.level);

	@Column(name = "win", columnDefinition = "INT")
	public int win = 0;

	@Column(name = "lose", columnDefinition = "INT")
	public int lose = 0;

	public Player() {
	}

	// TODO Update function which update the status of player
	// Constructor to generate the level 1 player
	// Level up function
	// QUESTION - How to calculate the value after own items??? --- further
	// discussion
	// WHAT IS THE STRUCTURE OF INVENTORY?

	public Player(String id) {
		this.id = id;
	}

	public Player(String id, String characterName, int level, int hp, int attack, int defense, int agility, int criticalStrike, int money,
			int experience, int exptolevel, double factor, int sword, int shield, int armour, int win, int lose) {
		super(characterName, level, hp, attack, defense, agility, criticalStrike, factor, sword, shield, armour);
		this.id = id;
		this.money = money;
		this.experience = experience;
		this.exptolevel = exptolevel;
		this.win = win;
		this.lose = lose;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getExptolevel() {
		return exptolevel;
	}

	public void setExptolevel(int exptolevel) {
		this.exptolevel = exptolevel;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", money=" + money + ", experience=" + experience + ", exptolevel=" + exptolevel
				+ ", win=" + win + ", lose=" + lose + ", characterName=" + characterName + ", level=" + level + ", hp="
				+ hp + ", attack=" + attack + ", defense=" + defense + ", agility=" + agility + ", criticalStrike="
				+ criticalStrike + ", factor=" + factor + ", sword=" + sword + ", shield=" + shield + ", armour="
				+ armour + "]";
	}
}
