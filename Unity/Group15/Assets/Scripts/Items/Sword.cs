using UnityEngine;

public class Sword : Item {

    public static Sprite base_icon; // set path to base icon here

	public Sword(int level) : base(base_icon, "", 0, 0, 0, 0, 0, 0)
    {
        // also switch for levels to change icon of item (better looking with each upgrade)
        int attribute = 0;
        switch (level)
        {
            case 1:
                name = "Basic Blade";
                attribute = 5;
                price = 9;
                break;
            case 2:
                name = "Banana Wedge";
                attribute = 15;
                price = 69;
                break;
            case 3:
                name = "Midnight Sharp";
                attribute = 30;
                price = 109;
                break;
            case 4:
                name = "Leviathan Blade";
                attribute = 50;
                price = 199;
                break;
            default: break;
        }
        attack = attribute;
    }
}
