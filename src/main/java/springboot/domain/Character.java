package springboot.domain;

public class Character {
	public String uuid;
	public int hp;
	public int attack;
	public int defence;
	public int agiliyt;
	public int intelligence;
	public String jsonResult;
	
	public Character() {
	}

	public Character(String uuid, int hp, int attack, int defence, int aqiliyt, int intelligence) {
		this.uuid = uuid;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
		this.agiliyt = aqiliyt;
		this.intelligence = intelligence;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public int getAqiliyt() {
		return agiliyt;
	}

	public void setAqiliyt(int aqiliyt) {
		this.agiliyt = aqiliyt;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
}
