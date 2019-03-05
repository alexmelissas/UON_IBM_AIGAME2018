using System;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using UnityEngine;

[Serializable]
public class Player
{
    public string id;
    public int level;
    public int hp;
    public int attack;
    public int defense;
    public int agility;
    public int critical_strike;
    public int money;
    public int experience;
    public int exptolevel;
    public double factor;
    public int sword;
    public int shield;
    public int armour;
    public int win;
    public int lose;

    public Player(string i, int lvl, int h_p, int atk, int def, int agl, int crit, int mn, int exp, 
        int exp2lvl, double ftr, int sw, int sh, int ar, int w, int l)
    {
        id = i; level = lvl; hp = h_p; attack = atk; defense = def; agility = agl; critical_strike = crit;
        money = mn; experience = exp; exptolevel = exp2lvl; factor = ftr;
        sword = sw; shield = sh; armour = ar; win = w; lose = l;
    }

    public Player()
    {
        id = ""; level = 0; hp = 0; attack = 0; defense = 0; agility = 0; critical_strike = 0; money = 0;
        experience = 0; exptolevel = 0; factor = 0; sword = 0; shield = 0; armour = 0; win = 0; lose = 0;
    }

    public static Player CreatePlayerFromJSON(string json)
    {
        Player temp = new Player();
        JsonUtility.FromJsonOverwrite(json, temp);
        return temp;
    }

    public string getID()
    {
        return id;
    }

    public bool ComparePlayer(Player other)
    {
        if (id != other.id || level != other.level || hp!=other.hp || attack != other.attack || defense != other.defense 
            || agility != other.agility || critical_strike != other.critical_strike || money != other.money 
            || experience != other.experience || exptolevel != other.exptolevel || factor != other.factor
            || sword != other.sword || shield != other.shield || armour != other.armour || win != other.win 
            || lose != other.lose) return false;
        return true;
    }

    // find different syntax that's more understandable
    public static T DeepClone<T>(T obj)
    {
        using (var ms = new MemoryStream())
        {
            var formatter = new BinaryFormatter();
            formatter.Serialize(ms, obj);
            ms.Position = 0;
            return (T)formatter.Deserialize(ms);
        }
    }

}
    
              