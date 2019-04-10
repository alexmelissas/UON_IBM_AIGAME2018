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

    //! Determining when reanalysis of twitter is done (used by login class)
    public static bool reanalysis_done;

    //! Determining when find enemy is done (used by battle classes)
    public static bool findenemy_done;

    //! Returns the particular address for one server API function.
    public static string Address(string service){
        string path;
        switch (service) {
            case "register_user": path = "/users/"; break;
            case "login_user":path = "/users/login"; break;
            case "view_users": path = "/users"; break;
            case "read_user": path = "/users/"; break;
            case "delete_user": path = "/users/"; break;
            case "update_twitter": path = "/reanalysis/"; break;

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
        reanalysis_done = false;
        string address = Server.Address("update_twitter") + UserSession.us.user.id;
        UnityWebRequest uwr = new UnityWebRequest((address), "PUT");
        byte[] jsonToSend = new System.Text.UTF8Encoding().GetBytes(UserSession.us.user.id);
        uwr.uploadHandler = (UploadHandler)new UploadHandlerRaw(jsonToSend);
        uwr.downloadHandler = (DownloadHandler)new DownloadHandlerBuffer();
        uwr.SetRequestHeader("Content-Type", "application/json");
        uwr.timeout = 10;

        yield return uwr.SendWebRequest();
        if (uwr.isNetworkError)
        {
            reanalysis_done = true; // just to avoid waiting forever
            Debug.Log("Error While Sending: " + uwr.error);
        }
        else
        {
            reanalysis_done = true;
            PlayerSession.ps.updatedPlayer = Player.CreatePlayerFromJSON(uwr.downloadHandler.text);
        }
        uwr.Dispose();

        yield return new WaitForSeconds(1);
        reanalysis_done = false;

        yield break;

    }

    //! Delete the user's account - upon registration error or demand
    public static IEnumerator DeleteAccount()
    {
        Debug.Log("Delete user");
        UnityWebRequest uwr = UnityWebRequest.Delete(Server.Address("delete_user") + UserSession.us.user.GetID());
        uwr.timeout = 10;
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
        string address = Server.Address("get_plays") + UserSession.us.user.id;

        using (UnityWebRequest uwr = UnityWebRequest.Get(address))
        {
            uwr.timeout = 10;
            yield return uwr.SendWebRequest();
            if (uwr.isNetworkError)
                Debug.Log("Error While Sending: " + uwr.error);
            else
            {
                int played_today = Int32.Parse(uwr.downloadHandler.text);
                PlayerSession.ps.plays_left = 10 - played_today;
            }
            uwr.Dispose();
        }
        yield break;
    }

    //! Get either random player as enemy (PvP) or a bot (PvE) from server
    public static IEnumerator GetEnemy(int battletype)
    {
        findenemy_done = false;
        PlayerSession.ps.enemy = new Player();
        string address = Server.Address("get_battle");
        if (battletype == 0) address += BotScreen.difficulty + "/";
        address += PlayerSession.ps.player.id;
        using (UnityWebRequest uwr = UnityWebRequest.Get(address))
        {
            uwr.timeout = 10;
            yield return uwr.SendWebRequest();

            if (uwr.isNetworkError)
            {
                findenemy_done = true;
                Debug.Log("Error While Sending: " + uwr.error);
                NPBinding.UI.ShowToast("Communication Error. Please try again later.", eToastMessageLength.SHORT);
            }
            else
            {
                findenemy_done = true;
                Debug.Log("FINDING ENEMY: " + uwr.downloadHandler.text);
                PlayerSession.ps.enemy = Player.CreatePlayerFromJSON(uwr.downloadHandler.text);
            }
            uwr.Dispose();
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

        uwr.timeout = 10;
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
