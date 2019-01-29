using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class setDifficulty : MonoBehaviour {

    public static string difficulty;

    public void Start()
    {
        difficulty = "easy";
    }

    public void changeDifficulty(string given_difficulty)
    {
        difficulty = given_difficulty;
        Debug.Log("Difficulty set to "+difficulty);
    }

}
