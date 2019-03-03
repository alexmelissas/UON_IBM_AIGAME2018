using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Items {

    public Item sword;
    public int sword_level;
    public Item shield;
    public int shield_level;
    public Item armour;
    public int armour_level;

	public Items(ServerItems si)
    {
        sword_level = si.sword;
        shield_level = si.shield;
        armour_level = si.armour;
        sword = Item.NewItem("sword", sword_level);
        shield = Item.NewItem("shield", shield_level);
        armour = Item.NewItem("armour", armour_level);
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
