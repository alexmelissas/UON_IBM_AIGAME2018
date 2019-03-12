using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using VoxelBusters.NativePlugins;

public class UpdateSessions : MonoBehaviour{

    /* Helper with methods to keep User and Player objects up to date with the server, in case user exits app
     * and objects are deleted */

    public void U_All() // Update all singleton objects
    {
        StartCoroutine(GetUser(true));
    }

    public void U_User() // Update user object
    {
        StartCoroutine(GetUser(false));
    }

    public void U_Player() // Update player stats
    {
        StartCoroutine(GetPlayer());
    }

    public static void JSON_Session(string session, string json)
    {
        if (session == "user") UserSession.us.user = User.CreateUserFromJSON(json);
        else if (session == "player") PlayerSession.ps.player = Player.CreatePlayerFromJSON(json);
        else return;
    }

    IEnumerator GetPlayer()
    {
        UnityWebRequest uwr = UnityWebRequest.Get(Server.Address("players") + UserSession.us.user.GetID());
        yield return uwr.SendWebRequest();
        if (uwr.isNetworkError)
        {
            Debug.Log("Error While Sending: " + uwr.error);
            NPBinding.UI.ShowToast("Communication Error. Please try again later.", eToastMessageLength.SHORT);
        }
        else
            UpdateSessions.JSON_Session("player",uwr.downloadHandler.text);
        StopCoroutine(GetPlayer());
    }

    IEnumerator GetUser(bool all)
    {        
        UnityWebRequest uwr = UnityWebRequest.Get(Server.Address("read_user") + ZPlayerPrefs.GetString("id"));
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
        StopCoroutine(GetUser(all));
    }
    
}
