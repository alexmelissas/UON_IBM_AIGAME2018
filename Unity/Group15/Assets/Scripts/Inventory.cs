using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Inventory : MonoBehaviour {

    public Text hp_lbl, atk_lbl, def_lbl, money_lbl;
    public GameObject upgradePanel;
    public Text itemName, itemStat, itemPrice, balance;
    public GameObject itemIcon, statsIcon;
    public Sprite atk, def, hp;

    private Player p;

    private void Awake()
    {
        gameObject.AddComponent<UpdateSessions>().U_All(); // NOT SURE
    }

    void Start()
    {
        //set the inv slots to current items?
        p = new Player();
        Displayed(false);
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

    public void Displayed(bool shown)
    {
        Vector3 hide = new Vector3(-791.5f, -1231.1f, 0);
        Vector3 show = new Vector3(Screen.width / 2, Screen.height / 2, 0);
        upgradePanel.transform.position = shown ? show : hide;
    }

    public void SetupUpgradePanel(int item_type)
    {
        if (item_type < 0 || item_type > 2) return;

        Item displayItem = null;
        int currentItemLevel = 0;
        
        switch(item_type)
        {
            case 0: // Sword
                currentItemLevel = PlayerSession.ps.player.sword;
                if (currentItemLevel == 4) return; // message no further upgrades available - or dim the icon at start
                displayItem = Item.NewItem("sword", currentItemLevel + 1);
                break;
            case 1: // Shield
                currentItemLevel = PlayerSession.ps.player.shield;
                if (currentItemLevel == 4) return; // message no further upgrades available - or dim the icon at start
                displayItem = Item.NewItem("shield", currentItemLevel + 1);
                break;
            case 2: // Armour
                currentItemLevel = PlayerSession.ps.player.armour;
                if (currentItemLevel == 4) return; // message no further upgrades available - or dim the icon at start
                displayItem = Item.NewItem("armour", currentItemLevel + 1);
                break;
        }

        UpdateLabels(displayItem, item_type);
        Displayed(true);
    }

    private void UpdateLabels(Item displayItem, int item_type)
    {
        int stat = 0;
        Sprite statIcon = atk;

        switch(item_type)
        {
            case 0:
                statIcon = atk;
                stat = displayItem.attack;
                break;
            case 1:
                statIcon = def;
                stat = displayItem.defense;
                break;
            case 2:
                statIcon = hp;
                stat = displayItem.hp;
                break;
        }
        //itemIcon.GetComponent<Image>().sprite = statIcon;
        statsIcon.GetComponent<Image>().sprite = statIcon;
        itemName.text = displayItem.name;
        itemStat.text = "" + stat;
        balance.text = "" + PlayerSession.ps.player.money;
        itemPrice.text = "" + displayItem.price;
    }

    public void ConfirmPurchase(int item, int level, int cost)
    {
        gameObject.AddComponent<UpdateSessions>().U_All();
        Displayed(false);
    }

    
}
