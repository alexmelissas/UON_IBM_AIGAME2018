package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	public String id;
	
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
	
	@Column(name = "jsonresult", columnDefinition = "TEXT")
	public String jsonResult;
	
	public Role() {
	}

	
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

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
