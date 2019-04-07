using UnityEngine.UI;

public class Shield : Item {

    public static Image base_icon; //set path to base icon here

    public Shield(int level) : base(base_icon, 0, 0, 0, 0, 0, 0)
    {
        // also switch for levels to change icon of item (better looking with each upgrade)
        int attribute = 0;
        switch (level)
        {
            case 1:
                attribute = 2;
                price = 9;
                break;
            case 2:
                attribute = 10;
                price = 29;
                break;
            case 3:
                attribute = 19;
                price = 87;
                break;
            case 4:
                attribute = 37;
                price = 199;
                break;
            case 5:
                attribute = 51;
                price = 399;
                break;
            case 6:
                attribute = 79;
                price = 999;
                break;
            default: break;
        }
        defense = attribute;
    }


    
}