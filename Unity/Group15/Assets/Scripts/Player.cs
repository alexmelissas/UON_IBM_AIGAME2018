using System;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using UnityEngine;

[Serializable]
public class Player
{
    public string id;
    public int hp;
    public int attack;
    public int defense; //ask yu to rename it :p
    public int agility;
    public int critical_strike;
    public int score;
    public int experience;

    public Player(string i, int healthpoints, int atk, int def, int agl, int intl, int sc, int exp)
    {
        id = i;
        hp = healthpoints;
        attack = atk;
        defense = def;
        agility = agl;
        critical_strike = intl;
        score = sc;
        experience = exp;
    }

    public static Player CreatePlayerFromJSON(string json)
    {
        Player temp = new Player("", 0, 0, 0, 0, 0, 0, 0);
        JsonUtility.FromJsonOverwrite(json, temp);
        return temp;
    }

    public string getID()
    {
        return id;
    }

    public bool ComparePlayer(Player other)
    {
        if (id != other.id || attack != other.attack || defense != other.defense || agility != other.agility
            || critical_strike != other.critical_strike || score != other.score || experience != other.experience)
            return false;
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
    
              