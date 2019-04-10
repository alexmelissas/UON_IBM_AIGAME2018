using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using VoxelBusters.NativePlugins;
using UnityEngine.Networking;

//! Entire Gameplay and Battle Screen management
public class Gameplay : MonoBehaviour {

    /* 1. Calculate and store Turns until one dies (List<Turn> turns)
     * 2. Calculate result
     * 3. POST result to server (id, result)
     * 4. while(!skip) ShowGameplay() - displays all the animations;
    */
    public Slider playerHP, enemyHP;
    public Image playerHPcolour, enemyHPcolour;
    public Text playerName, enemyName, playerLevel, enemyLevel;
    public Text actualPlayerHP, maxPlayerHP, actualEnemyHP, maxEnemyHP, playerDmgLabel, enemyDmgLabel;
    public AudioSource soundsrc, musicsrc;
    public AudioClip playerhit, enemyhit, crit, miss, drop_sword;
    private List<Turn> turns;
    private Player player, enemy;
    private PlayerModel player_model, enemy_model;
    private int result;
    private int player_max_hp, enemy_max_hp; //HP bar scaling
    float new_hp_player = -1;
    float new_hp_enemy = -1;
    private bool skip = false;
    private bool death = false;
    private bool ended = false;

    //! Skip button handler
    public void Press_Skip() { skip = true; }

    //! Setup the battle screen, including HP bars, Models, Damage Labels etc.
    private void Start()
    {
        player = PlayerSession.ps.player;
        Items.AttachItemsToPlayer(new Items(player), player);

        if (!PlayerPrefs.HasKey("battle_type")) { gameObject.AddComponent<ChangeScene>().Forward("Overworld"); }; //Error
        int battle_type = PlayerPrefs.GetInt("battle_type");
        if(battle_type!=0 && battle_type!=1) gameObject.AddComponent<ChangeScene>().Forward("Overworld"); // Handle error better
        enemy = PlayerSession.ps.enemy;
        if (battle_type == 0)
        {
            enemy.id = "bot";
            enemy.characterName = BotScreen.difficulty + "Bot";
        }

        turns = new List<Turn>();
        player_max_hp = player.hp;
        enemy_max_hp = enemy.hp;

        playerName.text = "" + player.characterName;
        playerLevel.text = "" + player.level;
        enemyName.text = "" + enemy.characterName;
        enemyLevel.text = "" + enemy.level;

        playerDmgLabel.enabled = enemyDmgLabel.enabled = false;
        playerDmgLabel.GetComponentInParent<Image>().enabled = enemyDmgLabel.GetComponentInParent<Image>().enabled = false;

        playerHP.normalizedValue = enemyHP.normalizedValue = 1f;

        musicsrc.volume = PlayerPrefs.GetFloat("music")/6;
        musicsrc.loop = true;
        musicsrc.playOnAwake = true;

        RunBattle();
    }

    //! Check if animation should be displayed: If not skipped, and if nobody won yet
    private void Update()
    {
        if (PlayerPrefs.HasKey("skip") && PlayerPrefs.GetInt("skip") == 1) skip = true;
        if (skip) Invoke("EndMatch", 0.5f);

        //Update HP bars
        float nhpp = new_hp_player * player_max_hp;
        float nhpe = new_hp_enemy * enemy_max_hp;
        Mathf.RoundToInt(nhpp);
        Mathf.RoundToInt(nhpe);
        actualPlayerHP.text = "" + ((nhpp >= 0) ? nhpp : player_max_hp);
        actualEnemyHP.text = "" + ((nhpe >= 0) ? nhpe : enemy_max_hp);
        maxPlayerHP.text = "/" + player_max_hp;
        maxEnemyHP.text = "/" + enemy_max_hp;

        if (new_hp_player > -1) if (playerHP.value > new_hp_player) playerHP.normalizedValue -= 0.005f;
        if (playerHP.value == 0 && !death) { soundsrc.PlayOneShot(drop_sword, PlayerPrefs.GetFloat("fx")); playerHPcolour.enabled = false; death = true; }
        else if (playerHP.value < 0.25) playerHPcolour.color = Color.red; else if (playerHP.value < 0.5) playerHPcolour.color = Color.yellow;

        if (new_hp_enemy > -1) if (enemyHP.value > new_hp_enemy) enemyHP.normalizedValue -= 0.005f;
        if (enemyHP.value == 0 && !death) { soundsrc.PlayOneShot(drop_sword, PlayerPrefs.GetFloat("fx")); enemyHPcolour.enabled = false; death = true; }
        else if (enemyHP.value < 0.25) enemyHPcolour.color = Color.red; else if (enemyHP.value < 0.5) enemyHPcolour.color = Color.yellow;
    }

    //! Run the battle, calculate all Turns and result
    public void RunBattle()
    {
        result = 0;
        bool player_turn = true; // think about who goes first
        while (result == 0)
        {
            Turn turn = new Turn(player_turn, player, enemy);
            turns.Add(turn);
            result = turn.PlayTurn();
            player = Player.DeepClone<Player>(turn.player); // get a new Player object with updated HP to pass to the next turn
            enemy = Player.DeepClone<Player>(turn.enemy);
            if (result==0) player_turn = player_turn ? false : true;
        }
        StartCoroutine(Server.PassResult(BattleResult.GetJSON(player, enemy, (result == 1) ? false : true)));
        StartCoroutine(PlayTurns(turns));
    }

    //! End the battle animations etc
    private void EndMatch()
    {
        if (ended) return;

        ended = true;
        StopAllCoroutines();
        gameObject.AddComponent<UpdateSessions>().U_Player();
        //PlayerSession.ps.player = Player.DeepClone<Player>(PlayerSession.ps.updatedPlayer); //keep copy of pre-battle player
        // do animations for gain exp / level up
        string announce = (result == 1) ? "Enemy wins. Too bad." : "You won! Congrats!";
        Debug.Log(announce);
        NPBinding.UI.ShowToast(announce, eToastMessageLength.SHORT);
        result = 0;
        gameObject.AddComponent<ChangeScene>().Forward("Overworld");
        Destroy(gameObject);
    }

    //! Animate the battle, including Player animations, HP bars, Damage Labels etc.
    IEnumerator PlayTurns(List<Turn> turns)
    {
        yield return new WaitForSeconds(1f);
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
            Image bam = attacker == "player" ? enemyDmgLabel.GetComponentInParent<Image>() : playerDmgLabel.GetComponentInParent<Image>();
            AudioClip sound;
            if (turn.damage != 0)
            {
                dmgLabel.text = "" + turn.damage;
                sound = crit;
                switch (turn.crit_landed)
                {
                    case 1: dmgLabel.color = Color.green; break;
                    case 2: dmgLabel.color = Color.yellow; break;
                    case 3: dmgLabel.color = Color.red; break;
                    default: dmgLabel.color = Color.black;
                        sound = (attacker=="player") ? playerhit : enemyhit;
                        break;
                }                
            }
            else
            {
                dmgLabel.text = "MISS";
                dmgLabel.color = Color.grey;
                sound = miss;
            }
            soundsrc.PlayOneShot(sound, PlayerPrefs.GetFloat("fx"));
            dmgLabel.enabled = true;
            bam.enabled = true;
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
            yield return new WaitForSeconds(0.75f);
            dmgLabel.enabled = false;
            bam.enabled = false;
            
            // 4) Wait a bit and go to next turn
            yield return new WaitForSeconds(0.5f);
        }
        skip = true;
    }
}
