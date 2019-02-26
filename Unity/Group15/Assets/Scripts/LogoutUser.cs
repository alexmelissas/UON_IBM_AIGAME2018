using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LogoutUser : MonoBehaviour {
    public void Logout() {
        UserSession.us.user = new User("","");
        gameObject.AddComponent<ChangeScene>().Forward("StartScreen");
    }
}