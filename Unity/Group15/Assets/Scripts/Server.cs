using UnityEngine;

public class Server {

    //Class with a bunch of server-side response definitions and paths, to have one unified
    //place if changes are made to the server urls/responses.

    private static readonly string address = "http://3.8.137.254:8080";
    private static readonly string register_success = "Saved";
    private static readonly string username_taken = "Username has been taken";

    public static string Address(string service){
        string path;
        switch (service) {
            case "register_user":
                path = "/users/";
                break;
            case "view_users":
                path = "/users";
                break;
            case "login_twitter":
                path = "/auth/";
                break;
            default:
                path = "";
                break;
        }
        return address + path;
    }

    public static bool CheckRegistration(string output)
    {
        if (output == register_success) return true;
        else if (output == username_taken) { Debug.Log(username_taken); return false; }
        else { Debug.Log("" + output); return false;}
    }
}
