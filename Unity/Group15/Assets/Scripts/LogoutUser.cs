using System.Collections;
using System.Collections.Generic;
using UnityEngine;

//! Logout User from app
public class LogoutUser : MonoBehaviour {
    //! Remove User, Player and id from memory.
    public void Logout() {
        UserSession.us.user = new User("","");
        PlayerSession.ps.player = new Player();
        PlayerPrefs.DeleteKey("id");
        gameObject.AddComponent<ChangeScene>().Forward("StartScreen");
    }
}