using System;
using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using VoxelBusters.NativePlugins;

public class Server {

    //Class with a bunch of server-side response definitions and paths, to have one unified
    //place if changes are made to the server urls/responses.

    private static readonly string address = "http://3.8.137.254:8080";
    private static readonly string register_success = "Saved";
    private static readonly string username_taken = "Username has been taken";
    public static readonly string fail_auth = "Sorrry, authorization fails. Please try again later.";

    public static string Address(string service){
        string path;
        switch (service) {
            case "register_user":
                path = "/users/";
                break;
            case "login_user":
                path = "/users/login";
                break;
            case "view_users":
                path = "/users";
                break;
            case "read_user":
                path = "/users/";
                break;
            case "login_twitter":
                path = "/auth/";
                break;
            case "submit_ideals":
                path = "/ideals/";
                break;
            case "players":
                path = "/players/";
                break;
            case "battle":
                path = "/battle";
                break;
            case "get_battle":
                path = "/battle/";
                break;
            default:
                path = "";
                break;
        }
        return address + path;
    }

    public static int CheckRegistration(string output)
    {
        if (output == register_success) return 1;
        else if (output == username_taken) return 0;
        else return 2;
    }

    public static bool CheckLogin(string output)
    {
        if (output == null || output == "") return false;
        else UpdateSessions.JSON_Session("user",output);
        return true;
    }

    public static bool CheckTwitter()
    {
        return (UserSession.us.user.GetAT()!="" && UserSession.us.user.GetATS()!="") ? true : false;
    }

    public static IEnumerator GetEnemy(Action<Player> playerCallback)
    {
        using (UnityWebRequest uwr = UnityWebRequest.Get(Server.Address("get_battle") + PlayerSession.ps.player.id))
        {
            yield return uwr.SendWebRequest();
            if (uwr.isNetworkError)
            {
                Debug.Log("Error While Sending: " + uwr.error);
                NPBinding.UI.ShowToast("Communication Error. Please try again later.", eToastMessageLength.SHORT);
                yield break; //return error
            }
            else
            {
                Player enemy = Player.CreatePlayerFromJSON(uwr.downloadHandler.text);
                playerCallback(enemy);
                yield break;
            }
        }
    }
}
