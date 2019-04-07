using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Inventory : MonoBehaviour {

    public Text hp_lbl, atk_lbl, def_lbl, money_lbl;
    public Button swordSlot, shieldSlot, armourSlot; // says slot but really they're invis buttons xD
    public GameObject upgradeSword, upgradeShield, upgradeArmour;

    private Player p;
    
    private void Awake()
    {
        gameObject.AddComponent<UpdateSessions>().U_All(); // NOT SURE
    }

    void Start()
    {
        upgradeSword.SetActive(false);
        upgradeShield.SetActive(false);
        upgradeArmour.SetActive(false);
        //set the inv slots to current items
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
            money_lbl.text = "" + p.money;
        }
    }

    public void ExpandUpgrades(int item)
    {
        switch(item)
        {
            case 0:
                upgradeSword.SetActive(true);
                upgradeShield.SetActive(false);
                upgradeArmour.SetActive(false);
                break;
            case 1:
                upgradeSword.SetActive(false);
                upgradeShield.SetActive(true);
                upgradeArmour.SetActive(false);
                break;
            case 2:
                upgradeSword.SetActive(false);
                upgradeShield.SetActive(false);
                upgradeArmour.SetActive(true);
                break;
            default:
                break;
        }
    }

    private void PopulateUpgrades(int item)
    {
        int min_level=0;
        switch(item)
        {
            case 0:
                min_level = PlayerSession.ps.player.sword;
                break;
            case 1:
                min_level = PlayerSession.ps.player.shield;
                break;
            case 2:
                min_level = PlayerSession.ps.player.armour;
                break;
        }
        
        // Assume have images of all items according to level
    }

    public void ConfirmPurchase(int item, int level, int cost)
    {

    }
}
