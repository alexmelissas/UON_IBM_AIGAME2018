using System;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using UnityEngine;

[Serializable]
//! JSON-able object with all Player-related attributes (eg. id, winrate, level, stats ...)
public class Player
{
    public string id, characterName;
    public int level, hp, attack, defense, agility, critical_strike ,money, experience, exptolevel;
    public int sword, shield, armour, win, lose;
    public double factor;

    //! Fully specific constructor
    public Player(string i, string cn, int lvl, int h_p, int atk, int def, int agl, int crit, int mn, int exp, 
        int exp2lvl, double ftr, int sw, int sh, int ar, int w, int l)
    {
        id = i; characterName = cn; level = lvl; hp = h_p; attack = atk; defense = def; agility = agl; critical_strike = crit;
        money = mn; experience = exp; exptolevel = exp2lvl; factor = ftr;
        sword = sw; shield = sh; armour = ar; win = w; lose = l;
    }

    //! Default constructor
    public Player()
    {
        id = ""; characterName = ""; level = 0; hp = 0; attack = 0; defense = 0; agility = 0; critical_strike = 0; money = 0;
        experience = 0; exptolevel = 0; factor = 0; sword = 0; shield = 0; armour = 0; win = 0; lose = 0;
    }

    //! Create a Player object from JSON
    public static Player CreatePlayerFromJSON(string json)
    {
        Player temp = new Player();
        JsonUtility.FromJsonOverwrite(json, temp);
        return temp;
    }

    //! Check if two Player objects have identical attributes
    public bool ComparePlayer(Player other)
    {
        if (id != other.id || characterName != other.characterName || level != other.level || hp!=other.hp 
            || attack != other.attack || defense != other.defense || agility != other.agility 
            || critical_strike != other.critical_strike || money != other.money || experience != other.experience 
            || exptolevel != other.exptolevel || factor != other.factor || sword != other.sword || shield != other.shield 
            || armour != other.armour || win != other.win || lose != other.lose) return false;
        return true;
    }

    //! Fully clone the Player object to a new one - needed in GamePlay for Turn calculation
        // Code  modified from https://stackoverflow.com/questions/129389/how-do-you-do-a-deep-copy-of-an-object-in-net-c-specifically
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

