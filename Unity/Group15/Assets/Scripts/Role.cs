using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Role : MonoBehaviour {

    public string id;
    public int hp;
    public int attack;
    public int defence; //ask yu to rename it :p
    public int agility;
    public int intelligence;
    public int score;
    public int experience;

    public Role(string i, int healthpoints, int atk, int def, int agl, int intl, int sc, int exp)
    {
        id = i;
        hp = healthpoints;
        attack = atk;
        defence = def;
        agility = agl;
        intelligence = intl;
        score = sc;
        experience = exp;
    }

    public static Ideals CreateIdealsFromJSON(string json)
    {
        Ideals temp = new Ideals("", 0, 0, 0, 0, 0);
        JsonUtility.FromJsonOverwrite(json, temp);
        return temp;
    }

    public string getID()
    {
        return id;
    }

}
