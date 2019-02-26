using System;
using UnityEngine;

[Serializable]
public class Player {

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

}
