using UnityEngine;

public class Armour : Item {

    public static Sprite base_icon; // set path to base icon here

    public Armour(int level) : base(base_icon, "", 0, 0, 0, 0, 0, 0)
    {
        // also switch for levels to change icon of item (better looking with each upgrade)
        // attribute1/attribute2 = what the item gives
        int attribute1 = 0;
        int attribute2 = 0;
        switch (level)
        {
            case 1:
                name = "Basic Plating";
                attribute1 = 20;
                attribute2 = 1;
                price = 10;
                break;
            case 2:
                name = "Conqueror";
                attribute1 = 48;
                attribute2 = 4;
                price = 49;
                break;
            case 3:
                name = "The Unbreakable";
                attribute1 = 79;
                attribute2 = 7;
                price = 139;
                break;
            case 4:
                name = "Turret Plating";
                attribute1 = 149;
                attribute2 = 15;
                price = 249;
                break;
            default:
                break;
        }
        hp = attribute1;
        defense = attribute2;
    }
}
