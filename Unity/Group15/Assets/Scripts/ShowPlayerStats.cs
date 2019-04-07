using System;
using UnityEngine;
using UnityEngine.UI;

//! Display player stats in Profile screen
public class ShowPlayerStats : MonoBehaviour {

    public Text hp_lbl, atk_lbl, def_lbl, agility_lbl, crit_lbl;
    public Text factor_lbl, lvl_lbl, next_lvl_lbl, exp_lbl;
    public Slider exp_slider, factor_slider;
    private Player p;


    private void Awake()
    {
       gameObject.AddComponent<UpdateSessions>().U_All(); // NOT SURE
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

            double max_factor = 0.8f; //keep updated!

            double factor = (p.factor - 1) / max_factor;
            factor_slider.normalizedValue = (float)factor;
            double factor_percent = Math.Round(factor, 2) * 100;
            if (factor_percent == 0) factor_lbl.text = "N/A";
            else factor_lbl.text = "" + factor_percent + "%";


            exp_lbl.text = "" + p.experience + "/" + p.exptolevel;
            lvl_lbl.text = "" + p.level;
            next_lvl_lbl.text = "" + (p.level + 1);

            float expBarValue = (float)p.experience / (float)p.exptolevel;
            exp_slider.normalizedValue = expBarValue;

        }
    }
}
