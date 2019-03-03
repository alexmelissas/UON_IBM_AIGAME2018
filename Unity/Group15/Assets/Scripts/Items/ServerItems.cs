using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;

[Serializable]
public class ServerItems {

    public string id;
    public int sword; //stored on server: ints representing level of each item
    public int shield;
    public int armour;

    public ServerItems(string i, int sw,int sh,int arm)
    {
        id = i;  sword = sw; shield = sh; armour = arm;
    }

    public static bool CreateItemsFromJSON(string json)
    {
        ServerItems temp = new ServerItems("",0,0,0);
        JsonUtility.FromJsonOverwrite(json, temp);
        ItemsSession.its.items = new Items(temp);
        if (ItemsSession.its.items != null) return true;
        return false;
    }

}
