using UnityEngine;

public class Turn {

    public Player player;
    public Player enemy;
    public bool player_turn;
    public float damage;
    public int crit_landed;

    public Turn(bool turn, Player p, Player e)
    {
        player_turn = turn;
        player = p;
        enemy = e;
    }

    private Player PlaysNow(bool playerPlays) { return playerPlays ? player : enemy; }

    public int PlayTurn()
    {
        int r_atk = PlaysNow(player_turn).attack;
        int atk = Random.Range((int)(r_atk - (r_atk*0.05)),(int)(r_atk + (r_atk*0.05)));
        damage = atk - PlaysNow(!player_turn).defense /* * hp multiplier */;
        if (damage < 1) damage = 1;
        int low_crit = 10; //* PlaysNow(player_turn).critical_strike; // not sure about multiplying it?
        int med_crit = 5;
        int high_crit = 1;
        int miss;
        if (PlaysNow(!player_turn).agility < 10) miss = 10;
        else miss = PlaysNow(!player_turn).agility; //need to be careful with agility scaling

        int crit = Random.Range(0, 100);
        float crit_dmg = damage;
        if (crit </*=*/ low_crit) { crit_dmg = damage *3f; crit_landed = 3; }
        else if (crit < med_crit) { crit_dmg = damage * 2f; ; crit_landed = 2; }
        else if (crit < high_crit) { crit_dmg = damage * 1.5f; crit_landed = 1; }
        else crit_landed = 0;
        if (crit_landed > 0) damage = crit_dmg;
        int misschance = Random.Range(0, 100);
        if (misschance < miss) damage = 0;
        
        PlaysNow(!player_turn).hp -= Mathf.RoundToInt(damage);
        
        if (player.hp <= 0) return 1; //player lost
        else if (enemy.hp <= 0) return 2; //player won
        else return 0; //no death yet
    }
}
