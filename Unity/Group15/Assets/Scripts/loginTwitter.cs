using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;
using VoxelBusters.NativePlugins;

//! Twitter login handler
public class LoginTwitter : MonoBehaviour {

    //! Keep checking if twitter linkage was OK - until timeout (10s after they return from twitter page)
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
                NPBinding.UI.ShowAlertDialogWithMultipleButtons("Personality bot is sad", "Twitter Connection wasn't happy :(", _buttons, OnButtonPressed);
            }
        }
        StopCoroutine(TryTwitter());
    }
    
    //! Display terms of usage - for ethics
    public void ShowTerms()
    {
        string[] _buttons = new string[] { "Decline", "Accept" };
        NPBinding.UI.ShowAlertDialogWithMultipleButtons("Terms of Usage"
            ,"By agreeing and linking your twitter you agree to " +
            "allow access to your public Twitter profile, in order " +
            "to analyse your personality. Only your tweets will be "+
            "accessed, and you will be made aware when. Your tweets "+
            "and data is never stored on our servers; the only personal "+
            "data stored is your factor of similarity between your"+
            "self-proclaimed ideal personality and your real one, "+
            "which will be read from your tweets. Your tweets are merely "+
            "passed temporarily into Watson, which analyses them and gives "+
            "us a personality analysis which use to calculate your similarity"+
            "factor, and then deleted."
            , _buttons, OnButtonPressed);

        return;
    }

    //! Try to open Twitter login page
    private void TryOpenTwitter(bool acceptedTerms)
    {
        if (acceptedTerms)
        {
            string url = Server.Address("login_twitter") + UserSession.us.user.GetID();
            Application.OpenURL(url); // need nicer way -eg window in app
            StartCoroutine(TryTwitter());
        }
    }

    //! Skip twitter login entirely
    public void SkipTwitter()
    {
        string[] _buttons = new string[] { "Link Twitter", "Play without Twitter" };
        NPBinding.UI.ShowAlertDialogWithMultipleButtons("You're missing out!"
            , "You can play this game without a Twitter account. \nHowever, by "+
            "not linking your Twitter profile, you'll be missing out on "+
            "the entire Personality improvement portion of this game." +
            "This means lower stats and more effort to reach the top!\n" +
            "Are you sure you'd like to proceed without Twitter?"
            , _buttons, OnButtonPressed);
        
        return;
    }

    private void PlayWithoutTwitter()
    {
        gameObject.AddComponent<ChangeScene>().Forward("CharacterCreation");
    }

    //! Handle dialog box button options
    private void OnButtonPressed(string _buttonPressed)
    {
        switch(_buttonPressed)
        {
            case "Retry": ShowTerms(); break;
            case "Play Without Twitter": SkipTwitter(); break;
            case "Accept": TryOpenTwitter(true); break;
            case "Decline": TryOpenTwitter(false); break;
            case "Link Twitter": ShowTerms(); break;
            case "Play without Twitter": PlayWithoutTwitter(); break;
        }
        return;
    }
}
