using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LogoutUser : MonoBehaviour {
    public void Logout() {
        UserSession.us.user = new User("","");
        PlayerSession.ps.player = new Player();
        PlayerPrefs.DeleteKey("id");
        gameObject.AddComponent<ChangeScene>().Forward("StartScreen");
    }
}