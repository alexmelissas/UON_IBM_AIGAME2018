using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Linq;
using System;

public class Gameplay : MonoBehaviour {

    /* 1. Calculate and store Turns until one dies (List<Turn> turns)
     * 2. Calculate result
     * 3. POST result to server (id, result)
     * 4. while(!skip) ShowGameplay() - displays all the animations;
    */
    public Slider playerHP;
    public Image playerHPcolour;
    public Slider enemyHP;
    public Image enemyHPcolour;
    public Text playerName;
    public Text enemyName;
    public Text playerLevel;
    public Text enemyLevel;
    public Text actualPlayerHP;
    public Text actualEnemyHP;
    public Text playerDmgLabel;
    public Text enemyDmgLabel;

    private List<Turn> turns;
    private Player player;
    private Player enemy;
    private PlayerModel player_model;
    private PlayerModel enemy_model;

    private int result;
    private int player_max_hp; //for HP bar scaling
    private int enemy_max_hp;
    float new_hp_player=-1;
    float new_hp_enemy=-1;
    private bool skip = false;

    private void Start()
    {
        //player = PlayerSession.ps.player;
        //ItemsSession.its.items.AddItemsToStats(player);
        // GET enemy Player by id
        // GET enemy ServerItems by id
        
        /* TESTING: */
        player = new Player("Player", 230, 37, 4, 1, 1, 2, 0);
        new Items(new ServerItems(1, 1, 1)).AddItemsToStats(player);
        enemy = new Player("Enemy", 250, 12, 3, 1, 1, 3, 0); 
        new Items(new ServerItems(1, 1, 1)).AddItemsToStats(enemy);
        /************/

        turns = new List<Turn>();
        player_max_hp = player.hp;
        enemy_max_hp = enemy.hp;

        if (PlayerPrefs.HasKey("skip_battles")) if (PlayerPrefs.GetInt("skip_battles") == 1) skip = true;
        playerName.text = "" + player.id; // would need to get username.
        playerLevel.text = ""+player.score;
        enemyName.text = "" + enemy.id; // need to make server give username with player pls
        enemyLevel.text = "" + enemy.score;
        playerHP.normalizedValue = 1f;
        enemyHP.normalizedValue = 1f;
        CalculateTurns();
    }

    private void Update()
    {
        if (skip) { StopAllCoroutines(); Debug.Log((result == 1) ? "Enemy wins. Too bad." : "You won! Congrats!"); gameObject.AddComponent<ChangeScene>().Forward("Overworld"); Destroy(gameObject); }
        //actualPlayerHP.text = new_hp_player + "/" + player_max_hp; - oops
        //actualEnemyHP.text = new_hp_enemy + "/" + enemy_max_hp;
        if (new_hp_player > -1) if(playerHP.value>new_hp_player)playerHP.normalizedValue -= 0.005f; //need to think about scaling and speed
        if (new_hp_enemy>-1) if (enemyHP.value > new_hp_enemy) enemyHP.normalizedValue -= 0.005f;
        if (playerHP.value < 0.25) playerHPcolour.color = Color.red; else if (playerHP.value < 0.5) playerHPcolour.color = Color.yellow;
        if (enemyHP.value < 0.25) enemyHPcolour.color = Color.red; else if (enemyHP.value < 0.5) enemyHPcolour.color = Color.yellow;
    }

    public void press_skip()
    {
        skip = true;
    }

    public void CalculateTurns()
    {
        result = 0;
        bool player_turn = true; // think about who goes first
        while (result == 0)
        {
            Turn turn = new Turn(player_turn, player, enemy);
            turns.Add(turn);
            result = turn.PlayTurn();
            player = Player.DeepClone<Player>(turn.player);
            enemy = Player.DeepClone<Player>(turn.enemy);
            if (result==0) player_turn = player_turn ? false : true;
        }
        // POST(PlayerSession.ps.player.getID(),(result==1 ? false : true);
        if (!skip) StartCoroutine(PlayTurns(turns));
        else Debug.Log("This guy won! whatever"); // jump to result notification
    }
    

    IEnumerator PlayTurns(List<Turn> turns)
    {
        foreach (Turn turn in turns)
        {
            string attacker = turn.player_turn ? "player" : "enemy";

            // 1) Run the animations (implemented in PlayerModel class)
            //if (attacker == "player")
            //{
            //    player_model.Strike();
            //    if (turns.Count == 1) enemy_model.HitWillDie(); // if last turn, then enemy died
            //    else enemy_model.HitBlock(); // need to think about block/blockless
            //    yield return new WaitForSeconds(1); // to wait for animation to be in motion?
                
            //}
            //else if(attacker == "enemy")
            //{
            //    enemy_model.Strike();
            //    if (turns.Count == 1) player_model.HitWillDie();
            //    else player_model.HitBlock();
            //    yield return new WaitForSeconds(1);
            //}

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
            yield return new WaitForSeconds(0.3f);

            // 3) Update HP of victim
            if (turn.damage!=0)
            {
                float currenthp = (attacker == "player") ? turn.enemy.hp : turn.player.hp;
                float maxhp = (attacker == "player") ? enemy_max_hp : player_max_hp;
                float hp_bar_value = currenthp / maxhp;
                if (attacker == "player") new_hp_enemy = hp_bar_value;
                else new_hp_player = hp_bar_value;
            }
            yield return new WaitForSeconds(1.2f);
            dmgLabel.enabled = false;
            
            // 4) Wait a bit and go to next turn
            yield return new WaitForSeconds(0.5f);
        }
        skip = true;
    }
}
