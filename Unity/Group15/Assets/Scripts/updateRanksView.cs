using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class updateRanksView : MonoBehaviour {

    public GameObject display;
    

    private List<string> getPlayers(string rank) {
        List<string> players = new List<string>();
        players.Clear();

        for(int i=0;i<5;i++) {    //here it would GET the top5 from server and add to list.
            players.Add(rank); 
        }
        //players.Sort -- by points.

        return players;
    }

    public void displayTopPlayers(string rank) {
        int i = 0;
        foreach (string current in getPlayers(rank)){
            string currentslot = "Slot" + (++i);
            display.transform.Find(currentslot).gameObject.GetComponentInChildren<Text>().text = current;
        }
    }
}
