using UnityEngine;

//! Armour item class
public class Armour : Item {

    public static Texture2D base_icon; // set path to base icon here

    public Armour(int level) : base(base_icon, "", 0, 0, 0, 0, 0, 0)
    {
        string item_icon_location = "";
        int attribute = 0;
        switch (level)
        {
            case 1:
                name = "Boots";
                attribute = 20;
                price = 99;
                item_icon_location = "boots";
                break;
            case 2:
                name = "Hunter's Jacket";
                attribute = 48;
                price = 259;
                item_icon_location = "hunter's jacket";
                break;
            case 3:
                name = "Hephaestus' Chains";
                attribute = 79;
                price = 489;
                item_icon_location = "hephaestus' chains";
                break;
            case 4:
                name = "Athena's Headplate";
                attribute = 149;
                price = 999;
                item_icon_location = "athena's_headplate";
                break;
            default:
                break;
        }
        icon = Resources.Load(item_icon_location) as Texture2D;
        hp = attribute;
    }
}
