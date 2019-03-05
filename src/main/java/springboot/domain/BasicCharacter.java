package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity  
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  
public class BasicCharacter {
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	public String id;
	
	@Column(name = "character_name", columnDefinition = "VARCHAR(15)")
	public String characterName;

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

	@Column(name = "factor", columnDefinition = "DOUBLE")
	public double factor = 0;

	@Column(name = "sword", columnDefinition = "INT")
	public int sword = 1;

	@Column(name = "shield", columnDefinition = "INT")
	public int shield = 1;

	@Column(name = "armour", columnDefinition = "INT")
	public int armour = 1;

	public BasicCharacter() {
	}

	public BasicCharacter(int level, int hp, int attack, int defense, int agility, int criticalStrike) {
		int[] array = { hp, attack, defense, agility, criticalStrike };
		this.setAttributes(array);
		this.level = level;
	}

	public BasicCharacter(String characterName, int level, int hp, int attack, int defense, int agility,
			int criticalStrike, double factor, int sword, int shield, int armour) {
		this(level, hp, attack, defense, agility, criticalStrike);
		this.characterName = characterName;
		this.factor = factor;
		int[] items = { sword, shield, armour };
		this.setItems(items);
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getCriticalStrike() {
		return criticalStrike;
	}

	public void setCriticalStrike(int criticalStrike) {
		this.criticalStrike = criticalStrike;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
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

	public void setAttributes(int[] array) {
		this.hp = array[0];
		this.attack = array[1];
		this.defense = array[2];
		this.agility = array[3];
		this.criticalStrike = array[4];
	}

	public void applyPersonality() {
		this.hp *= factor;
		this.attack *= factor;
		this.defense *= factor;
		this.agility *= factor;
		this.criticalStrike *= factor;
	}

	public void setItems(int[] array) {
		this.sword = array[0];
		this.shield = array[1];
		this.armour = array[2];
	}
}
