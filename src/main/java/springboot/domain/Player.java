package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import springboot.util.PlayerConfig;

@Entity
public class Player{
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	public String id;
	
	@Column(name = "level", columnDefinition = "INT")
	public int level = 1;
	
	@Column(name = "hp", columnDefinition = "INT")
	public int hp;
	
	@Column(name = "attack", columnDefinition = "INT")
	public int attack;
	
	@Column(name = "defense", columnDefinition = "INT")
	public int defense;
	
	@Column(name = "agility", columnDefinition = "INT")
	public int agility;
	
	@Column(name = "critical_strike", columnDefinition = "INT")
	public int criticalStrike;
	
	@Column(name = "money", columnDefinition = "INT")
	public int money = 0;
	
	@Column(name = "experience", columnDefinition = "INT")
	public int experience = 0;
	
	@Column(name = "exptolevel", columnDefinition = "INT")
	public int exptolevel = PlayerConfig.getLevelUpExperience(this.level);
	
	@Column(name = " factor", columnDefinition = "DOUBLE")
	public double factor = 0;
	
	@Column(name = "sword", columnDefinition = "INT")
	public int sword = 1;
	
	@Column(name = "shield", columnDefinition = "INT")
	public int shield = 1;
	
	@Column(name = "armour", columnDefinition = "INT")
	public int armour = 1;
	
	@Column(name = "win", columnDefinition = "INT")
	public int win = 0;
	
	@Column(name = "lose", columnDefinition = "INT")
	public int lose = 0;
	
	
	public Player() {
	}

	// TODO Update function which update the status of player
	// Constructor to generate the level 1 player
	// Level up function
	// QUESTION - How to calculate the value after own items??? --- further discussion
	// WHAT IS THE STRUCTURE OF INVENTORY?
	
	public Player(String id) {
		this.id = id;
	}
	
	public Player(int hp, int attack, int defense, int agility, int criticalStrike) {
		int[] array = {hp, attack, defense, agility, criticalStrike};
		this.setAttributes(array);
	}

	public Player(String id, int hp, int attack, int defense, int agility, int criticalStrike) {
		this(hp, attack, defense, agility, criticalStrike);
		this.id = id;
	}
	
	
	public Player(String id, int level, int hp, int attack, int defense, int agility, int criticalStrike, int money,
			int experience, int exptolevel, double factor, int sword, int shield, int armour, int win, int lose) {
		this(id, hp, attack, defense, agility, criticalStrike);
		this.money = money;
		this.experience = experience;
		this.exptolevel = exptolevel;
		this.factor = factor;
		this.sword = sword;
		this.shield = shield;
		this.armour = armour;
		this.win = win;
		this.lose = lose;
	}

	public void applyPersonality() {
		this.hp *= factor;
		this.attack *= factor;
		this.defense *= factor;
		this.agility *= factor;
		this.criticalStrike *= factor;
	}
	
	public void levelUp() {
		if(this.level >= 30) {
			// MAX LEVEL
			return;
		}
		
		if(this.exptolevel <= this.experience) {
			int exceed = this.experience - this.exptolevel;
			this.setLevel(this.getLevel() + 1);
			this.setAttributes(PlayerConfig.getBasicStatus(this.level));
			this.setExptolevel(PlayerConfig.getLevelUpExperience(this.level));
			System.out.println("---" + exceed);
			this.setExperience(exceed > 0 ? exceed : 0);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getCriticalStrike() {
		return criticalStrike;
	}

	public void setCriticalStrike(int criticalStrike) {
		this.criticalStrike = criticalStrike;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public void setAttributes(int[] array) {
		this.hp = array[0];
		this.attack = array[1];
		this.defense = array[2];
		this.agility = array[3];
		this.criticalStrike = array[4];
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

	public int getSword() {
		return sword;
	}

	public void setSword(int sword) {
		this.sword = sword;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public int getArmour() {
		return armour;
	}

	public void setArmour(int armour) {
		this.armour = armour;
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
}
