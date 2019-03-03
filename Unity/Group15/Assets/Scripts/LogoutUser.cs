using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LogoutUser : MonoBehaviour {
    public void Logout() {
        UserSession.us.user = new User("","");
        PlayerSession.ps.player = new Player("",0,0,0,0,0,0,0);
        ItemsSession.its.items = new Items(new ServerItems("",1, 1, 1));
        PlayerPrefs.DeleteKey("id");
        gameObject.AddComponent<ChangeScene>().Forward("StartScreen");
    }
}