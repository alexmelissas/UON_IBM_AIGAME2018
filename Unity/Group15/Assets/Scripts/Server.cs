using System;
using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using VoxelBusters.NativePlugins;

//! Collection of server-side responses, path URLs and functions.
public class Server {

    //! Server's home directory IP address/URL.
    private static readonly string address = "http://35.178.2.5:8080";

    //! General success response (eg. For confirming Twitter linkage)
    private static readonly string register_success = "Saved";

    //! Username taken response
    private static readonly string username_taken = "Username has been taken";

    //! Failed to authorise login response (either wrong username or password)
    public static readonly string fail_auth = "Sorrry, authorization fails. Please try again later.";

    //! Successful no-twitter login
    public static readonly string no_twitter_success = "Account creation succss.";

    //! Returns the particular address for one server API function.
    public static string Address(string service){
        string path;
        switch (service) {
            case "register_user": path = "/users/"; break;
            case "login_user":path = "/users/login"; break;
            case "view_users": path = "/users"; break;
            case "read_user": path = "/users/"; break;
            case "delete_user": path = "/users/"; break;
            case "update_twittr": path = "/reanalysis/"; break;

            case "login_twitter": path = "/auth/"; break;
            case "skip_twitter":  path = "/noauth/"; break;
            case "logout_twitter": path = "/auth/cancel/"; break;

            case "submit_ideals": path = "/ideals/"; break;

            case "players": path = "/players/"; break;

            case "battle": path = "/battle"; break;
            case "get_battle": path = "/battle/"; break;
            case "get_plays": path = "/battle/count/"; break;

            default: path = ""; break;
        }
        return address + path;
    }

    //! Check if username is entire registration process is OK
    public static int CheckRegistration(string output)
    {
        if (output == register_success) return 1;
        else if (output == username_taken) return 0;
        else return 2;
    }

    //! Check if valid login, then update User object
    public static bool CheckLogin(string output)
    {
        if (output == null || output == "") return false;
        else
        {
            UpdateSessions.JSON_Session("user", output);
            ZPlayerPrefs.SetString("id", UserSession.us.user.id);
        }
        return true;
    }

    //! Check if user has linked twitter, eg. User entry has twitter token
    public static bool CheckTwitter()
    {
        return (UserSession.us.user.accessToken!="" 
            && UserSession.us.user.accessTokenSecret!="") 
            ? true : false;
    }

    // ============ Coroutines for common things =============== //

    //! Request the server to reanalyse the twitter of the user for personality changes
    public static IEnumerator Reanalyse()
    {
        UnityWebRequest uwr = new UnityWebRequest((Server.Address("update_twitter")), "PUT");
        byte[] jsonToSend = new System.Text.UTF8Encoding().GetBytes(UserSession.us.user.id);
        uwr.uploadHandler = (UploadHandler)new UploadHandlerRaw(jsonToSend);
        uwr.downloadHandler = (DownloadHandler)new DownloadHandlerBuffer();
        uwr.SetRequestHeader("Content-Type", "application/json");

        yield return uwr.SendWebRequest();

        yield return new WaitForSeconds(4);

        if (uwr.isNetworkError)
            Debug.Log("Error While Sending: " + uwr.error);
        else
            //error handle
            PlayerSession.ps.updatedPlayer = Player.CreatePlayerFromJSON(uwr.downloadHandler.text);
        uwr.Dispose();
        yield break;

    }

    //! Delete the user's account - upon registration error or demand
    public static IEnumerator DeleteAccount()
    {
        UnityWebRequest uwr = UnityWebRequest.Delete(Server.Address("delete_user") + UserSession.us.user.GetID());
        yield return uwr.SendWebRequest();
        if (uwr.isNetworkError) yield return DeleteAccount();
        uwr.Dispose();

        UserSession.us.user = new User("", "");
        PlayerSession.ps.player = new Player();
        ZPlayerPrefs.DeleteKey("id");
        ZPlayerPrefs.Save();
        yield break;
    }

    // Battle Related //

    //! Get the player's remaining plays for the day
    public static IEnumerator GetPlaysLeft()
    {
        string address = Server.Address("get_plays") + PlayerSession.ps.player.id;

        using (UnityWebRequest uwr = UnityWebRequest.Get(address))
        {
            yield return uwr.SendWebRequest();
            if (uwr.isNetworkError)
                Debug.Log("Error While Sending: " + uwr.error);
            else
            {
                Debug.Log("Read played today: " + uwr.downloadHandler.text);
                int played_today = Int32.Parse(uwr.downloadHandler.text);
                PlayerSession.ps.plays_left = 10 - played_today;
            }
        }
        yield break;
    }

    //! Get either random player as enemy (PvP) or a bot (PvE) from server
    public static IEnumerator GetEnemy(int battletype)
    {
        PlayerSession.ps.enemy = new Player();

        string address = Server.Address("get_battle");
        if (battletype == 0) address += BotScreen.difficulty + "/";
        address += PlayerSession.ps.player.id;

        using (UnityWebRequest uwr = UnityWebRequest.Get(address))
        {
            yield return uwr.SendWebRequest();
            if (uwr.isNetworkError)
            {
                Debug.Log("Error While Sending: " + uwr.error);
                NPBinding.UI.ShowToast("Communication Error. Please try again later.", eToastMessageLength.SHORT);
                // API TO REFUND PLAY
            }
            else
            {
                Debug.Log("FINDING ENEMY BEEP BOOP");
                //Debug.Log("URL: " + address);
                //Debug.Log("got enemy " + uwr.downloadHandler.text);
                PlayerSession.ps.enemy = Player.CreatePlayerFromJSON(uwr.downloadHandler.text);
            }
        }
        yield break;
    }

    //! Pass the BattleResult object of the current battle to the server
    public static IEnumerator PassResult(string battle_result)
    {
        UnityWebRequest uwr = new UnityWebRequest(Server.Address("battle"), "PUT");
        byte[] jsonToSend = new System.Text.UTF8Encoding().GetBytes(battle_result);
        uwr.uploadHandler = (UploadHandler)new UploadHandlerRaw(jsonToSend);
        uwr.downloadHandler = (DownloadHandler)new DownloadHandlerBuffer();
        uwr.SetRequestHeader("Content-Type", "application/json");

        yield return uwr.SendWebRequest();

        if (uwr.isNetworkError)
        {
            Debug.Log("Error While Sending: " + uwr.error);
            NPBinding.UI.ShowToast("Communication Error. Please try again later.", eToastMessageLength.SHORT);
        }
        else
            PlayerSession.ps.updatedPlayer = Player.CreatePlayerFromJSON(uwr.downloadHandler.text);

        uwr.Dispose();
        yield break;
    }
    
}
