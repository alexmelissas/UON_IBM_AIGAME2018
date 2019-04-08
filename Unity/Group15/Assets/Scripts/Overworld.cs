using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

//! View username on the overworld
public class Overworld : MonoBehaviour {

    public Text usernameLabel, playsLabel;
    private void GetPlays() { StartCoroutine(Server.GetPlaysLeft()); }

    //! Update the Plays left
    private void Start()
    {
        gameObject.AddComponent<UpdateSessions>().U_All();
        Invoke("GetPlays", 0.5f);
    }
    
    //! Keep the logged-in user's username and plays left on the top of the screen
    private void Update ()
    {
        if (UserSession.us != null && UserSession.us.user.GetUsername()!="")
        {
            usernameLabel.GetComponentInChildren<Text>().text = UserSession.us.user.GetUsername();
            playsLabel.GetComponentInChildren<Text>().text = "" + PlayerSession.ps.plays_left;
        }
        else
        {
            usernameLabel.GetComponentInChildren<Text>().text = "<Invalid Session>";
            playsLabel.GetComponentInChildren<Text>().text = "";
        }
    }
}
