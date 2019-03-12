using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;
using VoxelBusters.NativePlugins;

public class AuthenticateUser : MonoBehaviour {

    public InputField usernameInput;
    public InputField passwordInput;
    public string auth_type;

    void Start()
    {
        usernameInput.onEndEdit.AddListener(delegate { EndInput("username"); });
        passwordInput.onEndEdit.AddListener(delegate { EndInput("password"); });
    }

    public void EndInput(string input)
    {
        if (input=="username") passwordInput.Select();
        else if (input=="password") CheckUserPass();
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
            UpdateSessions.JSON_Session("player", uwr.downloadHandler.text);
        StopCoroutine(GetPlayer());
    }

    IEnumerator TryLogin(bool first_login, string json, User user)
    {
        if (first_login && UserSession.us.user.GetUsername() == "") yield break;
        UnityWebRequest uwr = new UnityWebRequest(Server.Address("login_user"), "POST");
        byte[] jsonToSend = new System.Text.UTF8Encoding().GetBytes(json);
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
        {
            if (Server.CheckLogin(uwr.downloadHandler.text))
            {
                string next_scene = "Overworld";
                if (!first_login) yield return StartCoroutine(GetPlayer());
                if (first_login) next_scene = "TwitterLogin";
                gameObject.AddComponent<ChangeScene>().Forward(next_scene);
                if (!first_login) NPBinding.UI.ShowToast("Welcome back, " + user.GetUsername(), eToastMessageLength.SHORT);
            }
            else
            {
                Debug.Log("Invalid Credentials");
                NPBinding.UI.ShowToast("Invalid Credentials.", eToastMessageLength.SHORT);
                passwordInput.text = "";
                passwordInput.Select();
            }
            StopCoroutine(TryLogin(first_login,json, user));
        }
    }


    IEnumerator TryRegister(string json, User user)
    {
        UnityWebRequest uwr = new UnityWebRequest(Server.Address("register_user"), "POST");
        byte[] jsonToSend = new System.Text.UTF8Encoding().GetBytes(json);
        uwr.uploadHandler = (UploadHandler)new UploadHandlerRaw(jsonToSend);
        uwr.downloadHandler = (DownloadHandler)new DownloadHandlerBuffer();
        uwr.SetRequestHeader("Content-Type", "application/json");

        yield return uwr.SendWebRequest();

        if (uwr.isNetworkError) {
            Debug.Log("Error While Sending: " + uwr.error);
            NPBinding.UI.ShowToast("Communication Error. Please try again later.", eToastMessageLength.SHORT);
        }
        else { 
            int response = Server.CheckRegistration(uwr.downloadHandler.text);
            if (response == 1)
            {
                UserSession.us.user = user;
                Debug.Log("Account created");
                NPBinding.UI.ShowToast("Account Created.", eToastMessageLength.SHORT);
            }
            else
            {
                UserSession.us.user = new User("","");
                if (response == 0) NPBinding.UI.ShowToast("Sorry, that username is taken. Please try something else.", eToastMessageLength.SHORT);
                if (response == 0) Debug.Log("Username taken.");
                usernameInput.text = "";
                usernameInput.Select();
            }
        }
        yield return StartCoroutine(TryLogin(true,json,user));
        StopCoroutine(TryRegister(json, user));
    }

    public void CheckUserPass()
    {
        string username = usernameInput.text;
        string password = passwordInput.text;
        
        if (username == "")
        {
            Debug.Log("Enter username.");
            NPBinding.UI.ShowToast("Need a username.", eToastMessageLength.SHORT);
            return;
        }
        else if (password == "")
        {
            Debug.Log("Enter password.");
            NPBinding.UI.ShowToast("Need a password.", eToastMessageLength.SHORT);
            return;
        }
        else if (username.Length > 25)
        {
            Debug.Log("Username max length 25 characters.");
            NPBinding.UI.ShowToast("Username max length 25 characters.", eToastMessageLength.SHORT);
            return;
        }
        else
        {
            User user = new User(username, password);
            string json = JsonUtility.ToJson(user);
            if (auth_type == "register")
                StartCoroutine(TryRegister(json, user));
            else
                StartCoroutine(TryLogin(false, json, user));
        }
        return;
    }
}
