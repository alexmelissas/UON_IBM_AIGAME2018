using UnityEngine;
using UnityEngine.UI;

//! Display player stats in Profile screen
public class ShowPlayerStats : MonoBehaviour {

    public Text hp_lbl;
    public Text atk_lbl;
    public Text def_lbl;
    public Text agility_lbl;
    public Text crit_lbl;
    public Text factor_lbl;
    public Text lvl_lbl;
    public Text next_lvl_lbl;
    public Text exp_lbl;
    public Slider exp_slider;
    private Player p;


    private void Awake()
    {
       // gameObject.AddComponent<UpdateSessions>().U_All(); // NOT SURE
    }

    void Start()
    {
        p = new Player();
    }

    void Update () {
        if (!(p.ComparePlayer(PlayerSession.ps.player)))
        {
            p = PlayerSession.ps.player;
            hp_lbl.text = "" + p.hp;
            atk_lbl.text = "" + p.attack;
            def_lbl.text = "" + p.defense;
            agility_lbl.text = "" + p.agility;
            crit_lbl.text = "" + p.critical_strike;
            factor_lbl.text = "" + (p.factor * 100) + "%";
            exp_lbl.text = "" + p.experience + "/" + p.exptolevel;
            lvl_lbl.text = "" + p.level;
            next_lvl_lbl.text = "" + (p.level==30 ? p.level : p.level + 1); //max level 30?
            float expBarValue = (float)p.experience / (float)p.exptolevel;
            exp_slider.normalizedValue = expBarValue;

        }
    }
}
