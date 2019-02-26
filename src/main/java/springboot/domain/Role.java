package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role{
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	public String id;
	
	@Column(name = "level", columnDefinition = "INT")
	public int level = 1;
	
	@Column(name = "hp", columnDefinition = "INT")
	public int hp;
	
	@Column(name = "attack", columnDefinition = "INT")
	public int attack;
	
	@Column(name = "defence", columnDefinition = "INT")
	public int defence;
	
	@Column(name = "agility", columnDefinition = "INT")
	public int agility;
	
	@Column(name = "intelligence", columnDefinition = "INT")
	public int intelligence;
	
	@Column(name = "score", columnDefinition = "INT")
	public int score = 0;
	
	@Column(name = "experience", columnDefinition = "INT")
	public int experience = 0;
	
	@Column(name = " factor", columnDefinition = "DOUBLE")
	public double factor = 0;
	
	public Role() {
	}

	// TODO Update function which update the status of role
	// Constructor to generate the level 1 role
	// Level up function
	// QUESTION - How to calculate the value after own items??? --- further discussion
	// WHAT IS THE STRUCTURE OF INVENTORY?
	
	public Role(int hp, int attack, int defence, int agility, int intelligence) {
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
		this.agility = agility;
		this.intelligence = intelligence;
	}

	public Role(String id, int hp, int attack, int defence, int agility, int intelligence) {
		this(hp, attack, defence, agility, intelligence);
		this.id = id;
	}
	
	public Role(String id, int hp, int attack, int defence, int agility, int intelligence, int score) {
		this(id, hp, attack, defence, agility, intelligence);
		this.score = score;
	}
	
	public void applyPersonality() {
		this.hp *= factor;
		this.attack *= factor;
		this.defence *= factor;
		this.agility *= factor;
		this.intelligence *= factor;
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

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
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
