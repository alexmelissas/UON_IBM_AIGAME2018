using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UserSession : MonoBehaviour {

    public static UserSession us;

    public User user;

	void Awake () {
        if (us == null)
        {
            DontDestroyOnLoad(gameObject);
            us = this;
        }
        else if(us!=this)
        {
            Destroy(gameObject);
        }
	}

    public void getUserData()
    {
        string id = user.getID();
        string username = user.getUsername();
        string password = user.getPassword();
        string at = user.getAT();
        string ats = user.getATS();
        Debug.Log("ID: " + id + ", Username: " + username + ", Password: "
            + password + ", AccessToken: " + at + ", AccessTokenSecret: " + ats);
    }

}
