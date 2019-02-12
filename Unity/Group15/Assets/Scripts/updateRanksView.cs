using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class updateRanksView : MonoBehaviour {

    public GameObject display;
    List<User> Players;
    string oldRank = "";
    string newRank = "";

    void Update()
    {
        if (newRank != oldRank)
        {
            while (Players == null) ;
            int i = 0;
            foreach (User current in Players)
            {
                string currentslot = "Slot" + (++i);
                display.transform.Find(currentslot).gameObject.GetComponentInChildren<Text>().text = current.username;
            }
            StopCoroutine(GetPlayers(newRank));
            oldRank = newRank;
        }
    }

    IEnumerator GetPlayers(string rank)
    {
        string url = "http://3.8.137.254:8080/users";
        WWW www = new WWW(url);
        yield return www;
        string all = www.text.Trim(new char[]{'[', ']'});
        string[] separators = {"},","}"};
        string[] entries = all.Split(separators, System.StringSplitOptions.RemoveEmptyEntries);
        for (int i=0; i<entries.Length; i++) {
            if (i == 5) break;
            string newPlayerJson = entries[i] + "}";
            Debug.Log("" + newPlayerJson);
            User newplayer = JsonUtility.FromJson<User>(newPlayerJson);         
            Players.Add(newplayer);
            Debug.Log("" + newplayer.username);
        }
    }

    public void displayTopPlayers(string rank) {
        Players = new List<User>();
        StartCoroutine(GetPlayers(rank));
        newRank = rank;
    }
}
