using UnityEngine;
using UnityEngine.UI;

public class ShowPlayerStats : MonoBehaviour {

    public Text hp_lbl;
    public Text atk_lbl;
    public Text def_lbl;
    public Text agility_lbl;
    public Text crit_lbl;
    public Text score_lbl;
    public Text exp_lbl;
    private Player p;

    void Start()
    {
        p = new Player("",0,0,0,0,0,0,0);
    }

    void Update () {
        if (!(p.ComparePlayer(PlayerSession.ps.player)))
        {
            hp_lbl.text = "" + PlayerSession.ps.player.hp;
            atk_lbl.text = "" + PlayerSession.ps.player.attack;
            def_lbl.text = "" + PlayerSession.ps.player.defense;
            agility_lbl.text = "" + PlayerSession.ps.player.agility;
            crit_lbl.text = "" + PlayerSession.ps.player.critical_strike;
            score_lbl.text = "" + PlayerSession.ps.player.score;
            exp_lbl.text = "" + PlayerSession.ps.player.experience;
            p = PlayerSession.ps.player;
        }
    }
}
