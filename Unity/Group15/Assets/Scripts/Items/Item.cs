using UnityEngine;
using UnityEngine.UI;

//! Abstract class describing core attributes of items.
public abstract class Item {

    public Image icon; //maybe not Image - something to hold the icon
    public int hp;
    public int attack;
    public int defense;
    public int agility;
    public int critical_strike;

    //! Create a generic item with icon and attributes
    protected Item(Image icn,int h,int atk,int def,int ag,int cr)
    {
        icon = icn; hp = h; attack = atk; defense = def; agility = ag; critical_strike = cr;
    }

    //! Factory - to create different kinds of items
    public static Item NewItem(string type, int level)
    {
        Item item;
        switch (type)
        {
            case "sword":
                item = new Sword(level);
                break;
            case "shield":
                item = new Shield(level);
                break;
            case "armour":
                item = new Armour(level);
                break;
            default:
                item = null;
                break;
        }
        return item;
    }
}
