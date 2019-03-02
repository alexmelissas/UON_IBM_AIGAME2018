using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Items {

    public Item sword;
    public Item shield;
    public Item armour;

	public Items(ServerItems si)
    {
        sword = Item.NewItem("sword", si.sword);
        shield = Item.NewItem("shield", si.shield);
        armour = Item.NewItem("armour", si.armour);
    }

    public void AddItemsToStats(Player player)
    {
        if (PlayerSession.ps.player == null) return;
        player.hp += this.armour.hp;
        player.attack += this.sword.attack;
        player.defense += (this.shield.defense + this.armour.defense);
        return;
    }
}
