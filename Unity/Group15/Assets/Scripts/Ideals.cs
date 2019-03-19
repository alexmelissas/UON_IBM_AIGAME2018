using System;
using UnityEngine;

[Serializable]
//! JSON-able object to pass to server to submit Ideal personality traits
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
}
