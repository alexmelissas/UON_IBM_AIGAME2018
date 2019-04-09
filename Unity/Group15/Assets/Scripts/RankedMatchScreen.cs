using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using VoxelBusters.NativePlugins;

//! PvP screen handling
public class RankedMatchScreen : MonoBehaviour
{

    public GameObject display;
    private List<User> Users;
    public string oldRank = "";
    private int attempts = 0;

    //! GET the top 5 players of each rank
    IEnumerator GetPlayers(string newRank)
    {
        if (newRank != oldRank)
        { // Only update when different rank is clicked.

            // Here would need to get users of THAT RANK. Now just all people.
            // Also need to get top 5 - server-side implementation.

            string url = Server.Address("view_users");
            WWW www = new WWW(url);
            yield return www;
            string all = www.text.Trim(new char[] { '[', ']' }); // Split the entire huge all-player JSON into individual-user JSONs.
            string[] separators = { "},", "}" };
            string[] entries = all.Split(separators, System.StringSplitOptions.RemoveEmptyEntries);
            for (int i = 0; i < entries.Length; i++)
            {
                if (i == 5) break;
                string newUserJson = entries[i] + "}";
                User newuser = User.CreateUserFromJSON(newUserJson);
                Users.Add(newuser);
            }
            // Users.Sort(); //Need to sort based on Points (top 5).

            int s = 0;
            foreach (User current in Users)
            {
                string name = current.username;
                string currentslot = "Slot" + (++s);
                display.transform.Find(currentslot).gameObject.GetComponentInChildren<Text>().text = name;
            }
            oldRank = newRank;
            StopCoroutine(GetPlayers(newRank));
        }
    }

    //! Show the top players of selected rank
    public void DisplayTopPlayers(string rank)
    {
        Users = new List<User>();
        StartCoroutine(GetPlayers(rank));
    }

    public void OpenInventory()
    {
        gameObject.AddComponent<ChangeScene>().Forward("Inventory");
    }

    //! Initiate the PvP match
    public void Play()
    {
        attempts = 0;
        PlayerPrefs.SetInt("battle_type", 1);
        StartCoroutine(Server.GetEnemy(1));
        // add wait animation .. looking for opponent...
        Invoke("CheckEnemy", 0.5f);
    }
    
    //! Recursively try to find an enemy 4 times (in case of errors). If not found after 4 tries, stop.
    private void CheckEnemy()
    {
        if (PlayerSession.ps.enemy.id != "")
            gameObject.AddComponent<ChangeScene>().Forward("Battle");
        else if (attempts < 3) //recursively try to find enemy 4 times
        {
            StartCoroutine(Server.GetEnemy(1));
            attempts++;
            CheckEnemy();
        }
        else
            NPBinding.UI.ShowToast("No enemy found. Try again later.", eToastMessageLength.SHORT);
        return;
    }
}
