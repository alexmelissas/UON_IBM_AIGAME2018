using UnityEngine.UI;

public class Armour : Item {

    public static Image base_icon; // set path to base icon here

    public Armour(int level) : base(base_icon, 0, 0, 0, 0, 0, 0)
    {
        // also switch for levels to change icon of item (better looking with each upgrade)
        // attribute1/attribute2 = what the item gives
        int attribute1 = 0;
        int attribute2 = 0;
        switch (level)
        {
            case 1:
                attribute1 = 20;
                attribute2 = 1;
                price = 10;
                break;
            case 2:
                attribute1 = 48;
                attribute2 = 4;
                price = 49;
                break;
            case 3:
                attribute1 = 79;
                attribute2 = 7;
                price = 139;
                break;
            case 4:
                attribute1 = 149;
                attribute2 = 15;
                price = 249;
                break;
            case 5:
                attribute1 = 203;
                attribute2 = 28;
                price = 499;
                break;
            case 6:
                attribute1 = 289;
                attribute2 = 41;
                price = 999;
                break;
            default:
                break;
        }
        hp = attribute1;
        defense = attribute2;
    }
}
