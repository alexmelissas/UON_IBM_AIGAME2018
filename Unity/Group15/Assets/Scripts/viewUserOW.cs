using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class viewUserOW : MonoBehaviour {

    public Text usernameLabel;

	void Update () {
        if (UserSession.us != null)
            usernameLabel.GetComponentInChildren<Text>().text = UserSession.us.user.getUsername();
        else
            usernameLabel.GetComponentInChildren<Text>().text = "<Invalid Session>";
    }
}
