using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Items {

    public Item sword;
    public Item shield;
    public Item armour;

    public Items(Player p)
    {
        sword = Item.NewItem("sword", p.sword);
        shield = Item.NewItem("shield", p.shield);
        armour = Item.NewItem("armour", p.armour);
    }

    public static void AttachItemsToPlayer(Items i, Player p) //also make this put the item icons onto player
    {
        if (PlayerSession.ps.player == null) return;
        p.hp += i.armour.hp;
        p.attack += i.sword.attack;
        p.defense += (i.shield.defense + i.armour.defense);
        return;
    }
}
