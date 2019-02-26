package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
	
	@Column(name = "score", columnDefinition = "INT")
	public int score = 0;
	
	@Column(name = "experience", columnDefinition = "INT")
	public int experience = 0;
	
	@Column(name = " factor", columnDefinition = "DOUBLE")
	public double factor = 0;
	
	public Player() {
	}

	// TODO Update function which update the status of player
	// Constructor to generate the level 1 player
	// Level up function
	// QUESTION - How to calculate the value after own items??? --- further discussion
	// WHAT IS THE STRUCTURE OF INVENTORY?
	
	public Player(int hp, int attack, int defense, int agility, int criticalStrike) {
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.agility = agility;
		this.criticalStrike = criticalStrike;
	}

	public Player(String id, int hp, int attack, int defense, int agility, int criticalStrike) {
		this(hp, attack, defense, agility, criticalStrike);
		this.id = id;
	}
	
	public Player(String id, int hp, int attack, int defense, int agility, int criticalStrike, int score) {
		this(id, hp, attack, defense, agility, criticalStrike);
		this.score = score;
	}
	
	public void applyPersonality() {
		this.hp *= factor;
		this.attack *= factor;
		this.defense *= factor;
		this.agility *= factor;
		this.criticalStrike *= factor;
	}
	
	public void levelUp() {
		// TODO
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}
}
