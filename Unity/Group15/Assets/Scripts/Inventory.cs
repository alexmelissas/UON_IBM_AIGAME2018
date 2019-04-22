using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using VoxelBusters.NativePlugins;

public class Inventory : MonoBehaviour {

    public Text hp_lbl, atk_lbl, def_lbl, money_lbl;
    public GameObject upgradePanel;
    public Text itemName, itemStat, itemPrice, balance;
    public GameObject itemIcon, statsIcon, loading;
    public GameObject swordFullLevel, shieldFullLevel, armourFullLevel;
    public Sprite atk, def, hp;

    private Player p;
    private Item displayItem;
    private string displayItemType;
    private int currentItemLevel;

    private void Awake()
    {
        gameObject.AddComponent<UpdateSessions>().U_All();
    }

    private void Start()
    {
        p = new Player();
        swordFullLevel.SetActive(false);
        shieldFullLevel.SetActive(false);
        armourFullLevel.SetActive(false);
        loading.SetActive(false);
        Displayed(false);
    }

    private void Update()
    {
        if (!(p.ComparePlayer(PlayerSession.ps.player)))
        {
            p = PlayerSession.ps.player;
            int atk_bonus = (new Sword(p.sword)).attack;
            int def_bonus = (new Shield(p.shield)).defense;
            int hp_bonus = (new Armour(p.armour)).hp;

            hp_lbl.text = "" + (p.hp + hp_bonus);
            atk_lbl.text = "" + (p.attack + atk_bonus);
            def_lbl.text = "" + (p.defense + def_bonus);
            money_lbl.text = "" + p.money;
            if (p.sword == 4) swordFullLevel.SetActive(true);
            if (p.shield == 4) shieldFullLevel.SetActive(true);
            if (p.armour == 4) armourFullLevel.SetActive(true);
        }
    }

    private void Displayed(bool shown)
    {
        Vector3 hide = new Vector3(-791.5f, -1231.1f, 0);
        Vector3 show = new Vector3(Screen.width / 2, Screen.height / 2, 0);
        upgradePanel.transform.position = shown ? show : hide;
    }

    public void SetupUpgradePanel(int item_type)
    {
        if (item_type < 0 || item_type > 2) return;
        currentItemLevel = 0;

        switch(item_type)
        {
            case 0: // Sword
                currentItemLevel = PlayerSession.ps.player.sword;
                if (currentItemLevel == 4) return;
                displayItemType = "sword";
                break;
            case 1: // Shield
                currentItemLevel = PlayerSession.ps.player.shield;
                if (currentItemLevel == 4) return;
                displayItemType = "shield";
                break;
            case 2: // Armour
                currentItemLevel = PlayerSession.ps.player.armour;
                if (currentItemLevel == 4) return;
                displayItemType = "armour";
                break;
        }

        displayItem = Item.NewItem(displayItemType, currentItemLevel + 1);
        UpdateLabels(item_type);
        Displayed(true);
    }

    private void UpdateLabels(int item_type)
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

    private IEnumerator Purchase(string poorerPlayerJSON)
    {
        StartCoroutine(Server.UpdatePlayer(poorerPlayerJSON));
        loading.SetActive(true);
        yield return new WaitUntil(() => Server.updateplayer_done == true);
        loading.SetActive(false);
        gameObject.AddComponent<UpdateSessions>().U_Player();
        Displayed(false);
        yield break;
    }

    public void ConfirmPurchase()
    {
        if (PlayerSession.ps.player.money >= displayItem.price)
        {
            Player poorerPlayer = Player.Clone(PlayerSession.ps.player);
            poorerPlayer.money -= displayItem.price;
            switch(displayItemType)
            {
                case "sword": poorerPlayer.sword++; break;
                case "shield": poorerPlayer.shield++; break;
                case "armour": poorerPlayer.armour++; break;
            }
            string poorerPlayerJSON = JsonUtility.ToJson(poorerPlayer);
            StartCoroutine(Purchase(poorerPlayerJSON));
        }
        else
        {
            NPBinding.UI.ShowToast("Insufficient Funds.", eToastMessageLength.SHORT);
            gameObject.AddComponent<UpdateSessions>().U_Player();
            Displayed(false);
        }
    }
    
}
