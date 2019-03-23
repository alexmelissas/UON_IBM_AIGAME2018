using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

//! PvE screen and difficulty management
public class BotScreen : MonoBehaviour {

    public GameObject selected_easy, selected_medium, selected_hard;
    public static string difficulty;

    private void Start()
    {
        int difficultynumber = PlayerPrefs.GetInt("bot_difficulty");
        switch (difficultynumber)
        {
            case 0: difficulty = "easy"; break;
            case 1: difficulty = "medium"; break;
            case 2: difficulty = "hard"; break;
            default: break;
        }
    }

    private void Update()
    {
        string selected = "selected_" + difficulty;
        switch (selected)
        {
            case "selected_easy":
                PlayerPrefs.SetInt("bot_difficulty", 0);
                selected_easy.SetActive(true);
                selected_medium.SetActive(false);
                selected_hard.SetActive(false);
                break;
            case "selected_medium":
                PlayerPrefs.SetInt("bot_difficulty", 1);
                selected_easy.SetActive(false);
                selected_medium.SetActive(true);
                selected_hard.SetActive(false);
                break;
            case "selected_hard":
                PlayerPrefs.SetInt("bot_difficulty", 2);
                selected_easy.SetActive(false);
                selected_medium.SetActive(false);
                selected_hard.SetActive(true);
                break;
        }
    }

    //! Update the difficulty to the selected one.
    public void ChangeDifficulty(string given_difficulty)
    {
        difficulty = given_difficulty;        
    }

    public void OpenInventory()
    {
        //gameObject.AddComponent<ChangeScene>().Forward("Inventory");
    }

    //! Initiate the PvE match
    public void Play()
    {
        PlayerPrefs.SetInt("battle_type", 0);
        StartCoroutine(Gameplay.GetEnemy(0));
        Invoke("ActuallyPlay", 0.5f);
    }

    public void ActuallyPlay()
    {
        gameObject.AddComponent<ChangeScene>().Forward("Battle");
    }
}
