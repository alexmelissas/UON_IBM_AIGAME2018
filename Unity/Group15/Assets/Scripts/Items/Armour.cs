using UnityEngine;

//! Armour item class
public class Armour : Item {

    public static Sprite base_icon; // set path to base icon here

    public Armour(int level) : base(base_icon, "", 0, 0, 0, 0, 0, 0)
    {
        // also switch for levels to change icon of item (better looking with each upgrade)

        int attribute = 0;
        switch (level)
        {
            case 1:
                name = "Basic Plating";
                attribute = 20;
                price = 99;
                break;
            case 2:
                name = "Conqueror";
                attribute = 48;
                price = 259;
                break;
            case 3:
                name = "The Unbreakable";
                attribute = 79;
                price = 489;
                break;
            case 4:
                name = "Turret Plating";
                attribute = 149;
                price = 999;
                break;
            default:
                break;
        }
        hp = attribute;
    }
}
