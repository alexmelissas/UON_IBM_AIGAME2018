using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;
using VoxelBusters.NativePlugins;

public class LoginTwitter : MonoBehaviour {

    private IEnumerator TryTwitter()
    {
        // need to make twitter sequence not have a timer or something else whatever
        int repeats = 0;
        while (repeats < 5)
        {
            //string id = (UserSession.us.user.getID()=="") ? ZPlayerPrefs.GetString("id") : UserSession.us.user.getID();
            UnityWebRequest uwr = UnityWebRequest.Get(Server.Address("read_user") + UserSession.us.user.GetID());
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
                    yield return new WaitForSeconds(2);
                }
            }
            uwr.Dispose();
            if (repeats == 5)// NPBinding.UI.ShowToast("Twitter connection failed. Please try again, or play without Twitter.", eToastMessageLength.SHORT);
            {
                string[] _buttons = new string[] { "Retry", "Play Without Twitter"};
                NPBinding.UI.ShowAlertDialogWithMultipleButtons("Personality bot is sad", "Twitter Connection wasn't happy :(.", _buttons, OnButtonPressed);
            }
        }
        StopCoroutine(TryTwitter());
    }
    
    public void OpenTwitter()
    {
        string url = Server.Address("login_twitter") + UserSession.us.user.GetID();
        Application.OpenURL(url); // need nicer way -eg window in app
        StartCoroutine(TryTwitter());
    }

    public void SkipTwitter()
    {
        gameObject.AddComponent<ChangeScene>().Forward("CharacterCreation");
    }

    private void OnButtonPressed(string _buttonPressed)
    {
        if (_buttonPressed == "Retry") OpenTwitter();
        else if (_buttonPressed == "Play Without Twitter") SkipTwitter();
    }
}
