using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class setDifficulty : MonoBehaviour {

    public GameObject selected_easy, selected_medium, selected_hard;
    public static string difficulty;

    public void Start()
    {
        difficulty = "easy";
    }

    public void Update()
    {
        string selected = "selected_" + difficulty;
        switch (selected)
        {
            case "selected_easy":
                selected_easy.SetActive(true);
                selected_medium.SetActive(false);
                selected_hard.SetActive(false);
                break;
            case "selected_medium":
                selected_easy.SetActive(false);
                selected_medium.SetActive(true);
                selected_hard.SetActive(false);
                break;
            case "selected_hard":
                selected_easy.SetActive(false);
                selected_medium.SetActive(false);
                selected_hard.SetActive(true);
                break;
        }

    }

    public void changeDifficulty(string given_difficulty)
    {
        difficulty = given_difficulty;
        Debug.Log("Difficulty set to "+difficulty);
    }

}
