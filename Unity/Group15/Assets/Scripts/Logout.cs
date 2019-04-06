using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using VoxelBusters.NativePlugins;

//! Logout User from app
public class Logout : MonoBehaviour {

    //! Remove User, Player and id from memory.
    public void LogoutUser()
    {
        UserSession.us.user = new User("","");
        PlayerSession.ps.player = new Player();
        PlayerPrefs.DeleteKey("id");
        gameObject.AddComponent<ChangeScene>().Forward("StartScreen");
    }

    //! Unlink the Twitter account from the User
    public void UnlinkTwitter()
    {
        StartCoroutine(UnauthTwitter());
    }

    private IEnumerator UnauthTwitter()
    {
        Debug.Log(Server.Address("logout_twitter") + UserSession.us.user.GetID());
        UnityWebRequest uwr = UnityWebRequest.Get(Server.Address("logout_twitter") + UserSession.us.user.GetID());
        yield return uwr.SendWebRequest();

        if (uwr.isNetworkError)
        {
            Debug.Log("Error While Sending: " + uwr.error);
            NPBinding.UI.ShowToast("Communication Error. Please try again.", eToastMessageLength.SHORT);
        }
        else
        {
            gameObject.AddComponent<UpdateSessions>().U_All();
        }
        yield break;
    }
}