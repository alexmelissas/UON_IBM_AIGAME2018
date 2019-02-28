using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;
using VoxelBusters.NativePlugins;

public class LoginTwitter : MonoBehaviour {

    IEnumerator TryTwitter()
    {
        // need to make twitter sequence not have a timer or something else whatever
        int repeats = 0;
        while (repeats < 12) //wait 60 sec, check for login every 5 sec
        {
            //string id = (UserSession.us.user.getID()=="") ? ZPlayerPrefs.GetString("id") : UserSession.us.user.getID();
            UnityWebRequest uwr = UnityWebRequest.Get(Server.Address("read_user") + UserSession.us.user.getID());
            yield return uwr.SendWebRequest();

            if (uwr.isNetworkError)
            {
                Debug.Log("Error While Sending: " + uwr.error);
                NPBinding.UI.ShowToast("Communication Error. Please try again.", eToastMessageLength.SHORT);
                yield break;
            }
            else
            {
                UpdateSessions.JSON_Session("user", uwr.downloadHandler.text);
                if(Server.CheckTwitter())
                {
                    yield break;
                }
                else
                {
                    Debug.Log("Not registered yet: "+uwr.downloadHandler.text);
                    repeats++;
                    yield return new WaitForSeconds(5);
                }
            }
            uwr.Dispose();
            if(repeats==12) NPBinding.UI.ShowToast("Twitter connection failed. Please try again.", eToastMessageLength.SHORT);
        }
        StopCoroutine(TryTwitter());
    }
    
    public void openTwitter()
    {
        string url = Server.Address("login_twitter") + UserSession.us.user.getID();
        Application.OpenURL(url); // need nicer way -eg window in app
        StartCoroutine(TryTwitter());        
    }
}
