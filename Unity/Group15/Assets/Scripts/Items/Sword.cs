using UnityEngine;

//! Sword item class
public class Sword : Item {

    public static Texture2D base_icon; // set path to base icon here

	public Sword(int level) : base(base_icon, "", 0, 0, 0, 0, 0, 0)
    {
        string item_icon_location = "";
        int attribute = 0;
        switch (level)
        {
            case 1:
                name = "Basic Blade";
                attribute = 5;
                price = 109;
                item_icon_location = "basic_blade";
                break;
            case 2:
                name = "Runic Slasher";
                attribute = 15;
                price = 299;
                item_icon_location = "runic_slayer";
                break;
            case 3:
                name = "Zeus's ThunderSlasher";
                attribute = 30;
                price = 519;
                item_icon_location = "zeus_thunderslasher";
                break;
            case 4:
                name = "Athena's AngelBlade";
                attribute = 50;
                price = 999;
                item_icon_location = "athena's_angelblade";
                break;
            default: break;
        }
        icon = Resources.Load(item_icon_location) as Texture2D;
        attack = attribute;
    }
}
