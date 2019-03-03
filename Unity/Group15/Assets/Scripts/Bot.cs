using UnityEngine;
using System;

[Serializable]
public class Bot : Player {

    public Bot(Player player, Items items, int difficulty) : base("Bot", 0, 0, 0, 0, 0, 0, 0)
    {
        float hpx, atkx, defx, agx, critx = 0; //multipliers
        int itemsx, levelx = 0;
        switch (difficulty)
        {
            case 0: //easy
                hpx = 0.7f;
                atkx = 0.7f;
                defx = 0.7f;
                agx = 0.7f;
                critx = 0.7f;
                itemsx = -1;
                levelx = -1;
                break;
            case 1: //medium
                hpx = 0.9f;
                atkx = 0.8f;
                defx = 0.8f;
                agx = 1f;
                critx = 1f;
                itemsx = 0;
                levelx = 0;
                break;
            case 2: // hard
                hpx = 1.1f;
                atkx = 0.6f;
                defx = 0.5f;
                agx = 1.3f;
                critx = 1.3f;
                itemsx = 1;
                levelx = 1;
                break;
            default: // error
                hpx = 1f;
                atkx = 1f;
                defx = 1f;
                agx = 1f;
                critx = 1f;
                itemsx = 0;
                levelx = 0;
                break;
        }
        hp = Mathf.RoundToInt(player.hp * hpx);
        attack = Mathf.RoundToInt(player.attack * atkx);
        defense = Mathf.RoundToInt(player.defense * defx);
        agility = Mathf.RoundToInt(player.agility * agx);
        critical_strike = Mathf.RoundToInt(player.critical_strike * critx);
        score = player.score + levelx > 0 ? player.score + levelx : 1;
        int sword_level = items.sword_level + itemsx > 0 ? items.sword_level + itemsx : 1;
        int shield_level = items.shield_level + itemsx > 0 ? items.shield_level + itemsx : 1;
        int armour_level = items.armour_level + itemsx > 0 ? items.armour_level + itemsx : 1;
        new Items(new ServerItems("", sword_level, shield_level, armour_level)).AddItemsToStats(this);
    }
	
}
