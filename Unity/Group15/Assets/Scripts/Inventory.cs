using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Inventory : MonoBehaviour {
    public Text hp_lbl;
    public Text atk_lbl;
    public Text def_lbl;
    public Text agility_lbl;
    public Text crit_lbl;
    private Player p;


    private void Awake()
    {
        gameObject.AddComponent<UpdateSessions>().U_All(); // NOT SURE
    }

    void Start()
    {
        p = new Player();
    }

    void Update()
    {
        if (!(p.ComparePlayer(PlayerSession.ps.player)))
        {
            p = PlayerSession.ps.player;
            hp_lbl.text = "" + p.hp;
            atk_lbl.text = "" + p.attack;
            def_lbl.text = "" + p.defense;
            agility_lbl.text = "" + p.agility;
            crit_lbl.text = "" + p.critical_strike;
        }
    }

    void BackButton()
    {
        gameObject.AddComponent<ChangeScene>().Back();
    }
}
