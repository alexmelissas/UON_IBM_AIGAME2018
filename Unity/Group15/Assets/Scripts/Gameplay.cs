using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Gameplay : MonoBehaviour {

    /* 1. Calculate and store Turns until one dies (List<Turn> turns)
     * 2. Calculate result
     * 3. POST result to server (id, result)
     * 4. while(!skip) foreach(Turn bt : turns) ShowGameplay(bt);
    */
    public Slider playerHP;
    public Slider enemyHP;
    public Text playerDmgLabel;
    public Text enemyDmgLabel;

    private List<Turn> turns;
    private Player player;
    private Player enemy;
    private PlayerModel player_model;
    private PlayerModel enemy_model;
    
    private float player_max_hp; //for HP bar scaling
    private float enemy_max_hp;
    private bool skip = false;

    private void Start()
    {
        playerHP.normalizedValue = 1f;
        player_max_hp = player.hp;
        enemyHP.normalizedValue = 1f;
        enemy_max_hp = enemy.hp;

        player = PlayerSession.ps.player;
        ItemsSession.its.items.AddItemsToStats(player);

        /*replace*/enemy = new Player("", 0, 0, 0, 0, 0, 0, 0); // GET enemy Player by id
        Items enemy_items = new Items(new ServerItems(1, 1, 1)); // GET enemy ServerItems by id
        enemy_items.AddItemsToStats(enemy);
    }

    public void CalculateTurns()
    {
        int result = 0;
        bool player_turn = true; // think about who goes first
        while (result == 0)
        {
            Turn turn = new Turn(player_turn, player, enemy);
            turns.Add(turn);
            result = turn.PlayTurn();
            player = turn.player;
            enemy = turn.enemy;
            if (result==0) player_turn = player_turn ? false : true;
        }
        // POST(PlayerSession.ps.player.getID(),(result==1 ? false : true);
        if (!skip) ShowGameplay();
    }

    public void ShowGameplay()
    {
        StartCoroutine(PlayTurns(turns));
    }

    IEnumerator PlayTurns(List<Turn> turns)
    {
        foreach (Turn turn in turns)
        {
            string attacker = turn.player_turn ? "player" : "enemy";

            // 1) Run the animations (implemented in PlayerModel class)
            if (attacker == "player")
            {
                player_model.Strike();
                if (turns.Count == 1) enemy_model.HitWillDie(); // if last turn, then enemy died
                else enemy_model.HitBlock(); // need to think about block/blockless
                yield return new WaitForSeconds(1); // to wait for animation to be in motion?
                
            }
            else if(attacker == "enemy")
            {
                enemy_model.Strike();
                if (turns.Count == 1) player_model.HitWillDie();
                else player_model.HitBlock();
                yield return new WaitForSeconds(1);
            }

            // 2) Display damage
            Text dmgLabel = attacker == "player" ? enemyDmgLabel : playerDmgLabel;
            if (turn.damage != 0)
            {
                dmgLabel.text = "" + turn.damage;
                switch (turn.crit_landed)
                {
                    case 1: dmgLabel.color = Color.green; break;
                    case 2: dmgLabel.color = Color.yellow; break;
                    case 3: dmgLabel.color = Color.red; break;
                    default: dmgLabel.color = Color.black; break;
                }
            }
            else
            {
                dmgLabel.text = "MISS";
                dmgLabel.color = Color.grey;
            }
            dmgLabel.enabled = true;
            yield return new WaitForSeconds(1.5f);
            dmgLabel.enabled = false;

            // 3) Update HP of victim - Without animation for now
            Slider hpbar = attacker == "player" ? enemyHP : playerHP;
            hpbar.normalizedValue = (float) (attacker == "player" ? turn.enemy.hp : turn.player.hp)
                / (attacker == "player" ? enemy_max_hp : player_max_hp);

            // 4) Remove this turn from the list, wait a bit and go to next turn
            turns.Remove(turn);
            yield return new WaitForSeconds(4);
        }
        StopCoroutine(PlayTurns(turns));
    }
}
