package springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * <p>
 * The BasicCharacter class represents the character of the game, and it
 * contains the id and the name of the player, and the basic attributes
 * </p>
 * 
 * @author Yu Chen
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BasicCharacter {
	/**
	 * id
	 */
	@Id
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	public String id;

	/**
	 * character name
	 */
	@Column(name = "character_name", columnDefinition = "VARCHAR(15)")
	public String characterName;

	/**
	 * level
	 */
	@Column(name = "level", columnDefinition = "INT")
	public int level = 1;

	/**
	 * hp
	 */
	@Column(name = "hp", columnDefinition = "INT")
	public int hp;

	/**
	 * attack
	 */
	@Column(name = "attack", columnDefinition = "INT")
	public int attack;

	/**
	 * defense
	 */
	@Column(name = "defense", columnDefinition = "INT")
	public int defense;

	/**
	 * agility
	 */
	@Column(name = "agility", columnDefinition = "INT")
	public int agility;

	/**
	 * critical
	 */
	@Column(name = "critical_strike", columnDefinition = "INT")
	public int criticalStrike;

	/**
	 * the similarity between the ideal personality and the real personality
	 */
	@Column(name = "factor", columnDefinition = "DOUBLE")
	public double factor = 0;

	/**
	 * sword level
	 */
	@Column(name = "sword", columnDefinition = "INT")
	public int sword = 1;

	/**
	 * shield level
	 */
	@Column(name = "shield", columnDefinition = "INT")
	public int shield = 1;

	/**
	 * armour level
	 */
	@Column(name = "armour", columnDefinition = "INT")
	public int armour = 1;

	/**
	 * Constructor
	 */
	public BasicCharacter() {
	}

	/**
	 * Constructor
	 * 
	 * @param level          the level of the character
	 * @param hp             the hp of the character
	 * @param attack         the attack of the character
	 * @param defense        the defense of the character
	 * @param agility        the agility of the character
	 * @param criticalStrike the critical strike of the player
	 */
	public BasicCharacter(int level, int hp, int attack, int defense, int agility, int criticalStrike) {
		int[] array = { hp, attack, defense, agility, criticalStrike };
		this.setAttributes(array);
		this.level = level;
	}

	/**
	 * Constructor
	 * 
	 * @param characterName  the name of the character
	 * @param level          the level of the character
	 * @param hp             the hp of the character
	 * @param attack         the attack of the character
	 * @param defense        the defense of the character
	 * @param agility        the agility of the character
	 * @param criticalStrike the critical strike of the player
	 * @param factor         the similarity between the ideal personality and real
	 *                       personality
	 * @param sword          the sword level
	 * @param shield         the shield level
	 * @param armour         the armour level
	 */
	public BasicCharacter(String characterName, int level, int hp, int attack, int defense, int agility,
			int criticalStrike, double factor, int sword, int shield, int armour) {
		this(level, hp, attack, defense, agility, criticalStrike);
		this.characterName = characterName;
		this.factor = factor;
		int[] items = { sword, shield, armour };
		this.setItems(items);
	}

	/**
	 * @return the character name
	 */
	public String getCharacterName() {
		return characterName;
	}

	/**
	 * @param characterName the character name to set
	 */
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * @param attack the attack to set
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * @return the defense
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * @param defense the defense to set
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}

	/**
	 * @return the agility
	 */
	public int getAgility() {
		return agility;
	}

	/**
	 * @param agility the agility to set
	 */
	public void setAgility(int agility) {
		this.agility = agility;
	}

	/**
	 * @return the critical strike
	 */
	public int getCriticalStrike() {
		return criticalStrike;
	}

	/**
	 * @param criticalStrike the critical strike to set
	 */
	public void setCriticalStrike(int criticalStrike) {
		this.criticalStrike = criticalStrike;
	}

	/**
	 * @return the factor
	 */
	public double getFactor() {
		return factor;
	}

	/**
	 * @param factor the factor to set
	 */
	public void setFactor(double factor) {
		this.factor = factor;
	}

	/**
	 * @return the sword level
	 */
	public int getSword() {
		return sword;
	}

	/**
	 * @param sword the sword level to set
	 */
	public void setSword(int sword) {
		this.sword = sword;
	}

	/**
	 * @return the shield level
	 */
	public int getShield() {
		return shield;
	}

	/**
	 * @param shield the shield level to set
	 */
	public void setShield(int shield) {
		this.shield = shield;
	}

	/**
	 * @return the armour level
	 */
	public int getArmour() {
		return armour;
	}

	/**
	 * @param armour the armour level to set
	 */
	public void setArmour(int armour) {
		this.armour = armour;
	}

	/**
	 * set the attributes of the character
	 * 
	 * @param array the array which contains hp, attack, defense, agility and
	 *              critical strike
	 */
	public void setAttributes(int[] array) {
		this.hp = array[0];
		this.attack = array[1];
		this.defense = array[2];
		this.agility = array[3];
		this.criticalStrike = array[4];
	}

	/**
	 * increase attributes according to the similarity
	 */
	public void applyPersonality() {
		this.hp *= factor;
		this.attack *= factor;
		this.defense *= factor;
		this.agility *= factor;
		this.criticalStrike *= factor;
	}

	/**
	 * set the level of the items
	 * 
	 * @param array the array which contains the level of sword, shield and armour
	 */
	public void setItems(int[] array) {
		this.sword = array[0];
		this.shield = array[1];
		this.armour = array[2];
	}
}
