using UnityEngine.UI;

public class Sword : Item {

    public static Image base_icon; // set path to base icon here

	public Sword(int level) : base(base_icon, 0, 0, 0, 0, 0)
    {
        // also switch for levels to change icon of item (better looking with each upgrade)
        int attribute = 0;
        switch (level)
        {
            case 1: attribute = 5; break;
            case 2: attribute = 15; break;
            case 3: attribute = 30; break;
            case 4: attribute = 50; break;
            case 5: attribute = 77; break;
            case 6: attribute = 105; break;
            default: break;
        }
        attack = attribute;
    }
}
