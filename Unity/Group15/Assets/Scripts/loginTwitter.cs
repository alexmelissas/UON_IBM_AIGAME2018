using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class loginTwitter : MonoBehaviour {

    GameObject reference;

	public void openTwitter()
    {
        //string username = GameObject.Find("username").GetComponentInChildren<Text>().text;
        string url = Server.Address("login_twitter");
        //Need to keep username from previous scene and add it to a GET request
        Application.OpenURL(url);
        // then need to check if user did log in, if not disable next button
    }
}
