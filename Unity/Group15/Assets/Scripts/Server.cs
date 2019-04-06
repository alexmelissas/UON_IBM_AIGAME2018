using System;
using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using VoxelBusters.NativePlugins;

//! Collection of server-side responses and path URLs.
public class Server {
    //! Server's home directory IP address/URL.
    private static readonly string address = "http://10.154.169.221:8080";
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

            case "login_twitter": path = "/auth/"; break;
            case "skip_twitter":  path = "/noauth/"; break;
            case "logout_twitter": path = "/auth/cancel/"; break;

            case "submit_ideals": path = "/ideals/"; break;

            case "players": path = "/players/"; break;

            case "battle": path = "/battle"; break;
            case "get_battle": path = "/battle/"; break;

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
            ZPlayerPrefs.SetString("id", UserSession.us.user.GetID());
        }
        return true;
    }

    //! Check if user has linked twitter, eg. User entry has twitter token
    public static bool CheckTwitter()
    {
        return (UserSession.us.user.GetAT()!="" && UserSession.us.user.GetATS()!="") ? true : false;
    }

}
