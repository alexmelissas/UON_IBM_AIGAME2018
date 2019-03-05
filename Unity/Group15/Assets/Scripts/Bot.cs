using UnityEngine;
using System;

[Serializable]
public class Bot : Player {

    public Bot(Player player, int difficulty) : base("", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    {
        //Set the multipliers
        float hpx, atkx, defx, agx, critx = 0;
        int itemsx, levelx = 0;
        switch (difficulty)
        {
            case 0: //easy
                id = "Easy";
                hpx = 0.7f;
                atkx = 0.7f;
                defx = 0.7f;
                agx = 0.7f;
                critx = 0.7f;
                itemsx = -1;
                levelx = -1;
                break;
            case 1: //medium
                id = "Medium";
                hpx = 0.9f;
                atkx = 0.8f;
                defx = 0.8f;
                agx = 1f;
                critx = 1f;
                itemsx = 0;
                levelx = 0;
                break;
            case 2: // hard
                id = "Hard";
                hpx = 1.1f;
                atkx = 0.6f;
                defx = 0.5f;
                agx = 1.3f;
                critx = 1.3f;
                itemsx = 1;
                levelx = 1;
                break;
            default: // error
                id = "Missingno.";
                hpx = 1f;
                atkx = 1f;
                defx = 1f;
                agx = 1f;
                critx = 1f;
                itemsx = 0;
                levelx = 0;
                break;
        }

        //Set the stats
        id += " Bot";
        hp = Mathf.RoundToInt(player.hp * hpx);
        attack = Mathf.RoundToInt(player.attack * atkx);
        defense = Mathf.RoundToInt(player.defense * defx);
        agility = Mathf.RoundToInt(player.agility * agx);
        critical_strike = Mathf.RoundToInt(player.critical_strike * critx);
        level = player.level + levelx > 0 ? player.level + levelx : 1;
            sword = player.sword > 0 ? player.sword + itemsx : 1;
            shield = player.shield + itemsx > 0 ? player.shield+ itemsx : 1;
            armour = player.armour + itemsx > 0 ? player.armour + itemsx : 1;
        Items.AttachItemsToPlayer(new Items(this),this);
    }
}
