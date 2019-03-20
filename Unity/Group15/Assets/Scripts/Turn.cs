using UnityEngine;

//! Calculate damage, who plays and outcome of one Turn
public class Turn {

    public Player player;
    public Player enemy;
    public bool player_turn;
    public float damage;
    public int crit_landed;

    //! Constructor
    public Turn(bool turn, Player p, Player e)
    {
        player_turn = turn;
        player = p;
        enemy = e;
    }

    //! Choose whose Turn this is, alternating every Turn
    private Player PlaysNow(bool playerPlays) { return playerPlays ? player : enemy; }

    //! Calculate the result of this Turn, including damage dealth, each Player's remaining HP etc
    public int PlayTurn()
    {
        Player attacker = PlaysNow(player_turn);
        Player victim = PlaysNow(!player_turn);

        int r_atk = attacker.attack;
        int atk = Random.Range((int)(r_atk - (r_atk*0.05)),(int)(r_atk + (r_atk*0.05)));
        damage = (atk - victim.defense) * (victim.level > attacker.level ? victim.level - attacker.level : 1);
        if (damage < 1) damage = 1;
        int low_crit = 10;//attacker.critical_strike; // not sure about multiplying it?
        int med_crit = low_crit/2;
        int high_crit = low_crit/10;

        int crit = Random.Range(0, 100);
        float crit_dmg = damage;
        if (crit <= low_crit) { crit_dmg = damage *3f; crit_landed = 3; }
        else if (crit < med_crit) { crit_dmg = damage * 2f; ; crit_landed = 2; }
        else if (crit < high_crit) { crit_dmg = damage * 1.5f; crit_landed = 1; }
        else crit_landed = 0;
        if (crit_landed > 0) damage = crit_dmg;

        int miss;
        if (victim.agility < 10) miss = 10;
        else miss = victim.agility; //need to be careful with agility scaling
        int misschance = Random.Range(0, 100);
        if (misschance < miss) damage = 0;
        
        victim.hp -= Mathf.RoundToInt(damage);
        
        if (player.hp <= 0) return 1; //player lost
        else if (enemy.hp <= 0) return 2; //player won
        else return 0; //no death yet
    }
}
