using System;
using UnityEngine;

[Serializable]
public class Ideals {

    public string id;
    public float openess;
    public float conscientiousness;
    public float extraversion;
    public float agreeableness;
    public float emotionalrange;

    public Ideals(string i, float o, float c, float ex, float a, float em)
    {
        id = i;
        openess = o;
        conscientiousness = c;
        extraversion = ex;
        agreeableness = a;
        emotionalrange = em;
    }

    public static Ideals CreateIdealsFromJSON(string json)
    {
        Ideals temp = new Ideals("",0,0,0,0,0);
        JsonUtility.FromJsonOverwrite(json, temp);
        return temp;
    }

    public string getID()
    {
        return id;
    }
    
    public float getO()
    {
        return openess;
    }

    public float getC()
    {
        return conscientiousness;
    }

    public float getEX()
    {
        return extraversion;
    }

    public float getA()
    {
        return agreeableness;
    }

    public float getEM()
    {
        return emotionalrange;
    }
}
