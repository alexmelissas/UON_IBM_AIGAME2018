using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using VoxelBusters.NativePlugins;

// Usage: add this line to the other class and choose the function
// gameObject.AddComponent<UpdateSessions>()

//! Keep User and Player objects up to date with the server, keep data consistent when exiting app
public class UpdateSessions : MonoBehaviour{

    //! Update all singleton objects in the app
    public void U_All() { StartCoroutine(GetUser(true)); }

    //! Update just the User object
    public void U_User() { StartCoroutine(GetUser(false)); }

    //! Update just the Player object
    public void U_Player() { StartCoroutine(GetPlayer()); }

    //! Update a User/Player object from JSON
    public static void JSON_Session(string session, string json)
    {
        if (session == "user") UserSession.user_session.user = User.CreateUserFromJSON(json);
        else if (session == "player") PlayerSession.player_session.player = Player.CreatePlayerFromJSON(json);
        else return;
    }

    //! GET JSON for Player
    IEnumerator GetPlayer()
    {
        UnityWebRequest uwr = UnityWebRequest.Get(Server.Address("players") + ZPlayerPrefs.GetString("id"));
        uwr.timeout = 10;
        yield return uwr.SendWebRequest();
        if (uwr.isNetworkError)
        {
            Debug.Log("Error While Sending: " + uwr.error);
            NPBinding.UI.ShowToast("Communication Error. Please try again later.", eToastMessageLength.SHORT);
        }
        else
        {
            UpdateSessions.JSON_Session("player", uwr.downloadHandler.text);
        }
        uwr.Dispose();
        StopCoroutine(GetPlayer());
    }

    //! Get JSON for User
    IEnumerator GetUser(bool all)
    {        
        UnityWebRequest uwr = UnityWebRequest.Get(Server.Address("read_user") + ZPlayerPrefs.GetString("id"));
        uwr.timeout = 10;
        yield return uwr.SendWebRequest();
        if (uwr.isNetworkError)
        {
            Debug.Log("Error While Sending: " + uwr.error);
            NPBinding.UI.ShowToast("Communication Error. Please try again later.", eToastMessageLength.SHORT);
        }
        else
        {
            if (User.CreateUserFromJSON(uwr.downloadHandler.text).GetID() == "") yield break;
            UpdateSessions.JSON_Session("user", uwr.downloadHandler.text);          
            if (all) yield return StartCoroutine(GetPlayer());
        }
        uwr.Dispose();
        StopCoroutine(GetUser(all));
    }
    
}
