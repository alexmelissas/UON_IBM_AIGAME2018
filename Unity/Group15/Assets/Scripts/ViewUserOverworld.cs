using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ViewUserOverworld : MonoBehaviour {

    public Text usernameLabel;

	void Update () {
        if (UserSession.us != null && UserSession.us.user.getUsername()!="")
            usernameLabel.GetComponentInChildren<Text>().text = UserSession.us.user.getUsername();
        else
            usernameLabel.GetComponentInChildren<Text>().text = "<Invalid Session>";
    }
}
