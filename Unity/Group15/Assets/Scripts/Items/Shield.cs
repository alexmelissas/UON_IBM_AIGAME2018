using UnityEngine;

public class Shield : Item {

    public static Sprite base_icon; //set path to base icon here

    public Shield(int level) : base(base_icon, "", 0, 0, 0, 0, 0, 0)
    {
        // also switch for levels to change icon of item (better looking with each upgrade)
        int attribute = 0;
        switch (level)
        {
            case 1:
                name = "Basic Shield";
                attribute = 2;
                price = 9;
                break;
            case 2:
                name = "Rampant Protector";
                attribute = 10;
                price = 29;
                break;
            case 3:
                name = "Runic Defender";
                attribute = 19;
                price = 87;
                break;
            case 4:
                name = "Angel's Guard";
                attribute = 37;
                price = 199;
                break;
            default: break;
        }
        defense = attribute;
    }


    
}