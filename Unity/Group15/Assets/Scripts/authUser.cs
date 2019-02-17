using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
using VoxelBusters.NativePlugins;

public class authUser : MonoBehaviour {

    public InputField usernameInput;
    public InputField passwordInput;
    public string auth_type;

    void Start()
    {
        usernameInput.onEndEdit.AddListener(delegate { endInput("username"); });
        passwordInput.onEndEdit.AddListener(delegate { endInput("password"); });
    }

    public void endInput(string input)
    {
        if (input=="username") passwordInput.Select();
        else if (input=="password") checkUserPass();
    }

    IEnumerator TryRegister(string auth_type, string url, string json, User user)
    {
        UnityWebRequest uwr = new UnityWebRequest(url, "POST");
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
            if (auth_type == "login") {
                if (Server.CheckLogin(uwr.downloadHandler.text))
                {
                    UserSession.us.user = user;
                    new ChangeScene().Forward("Overworld");
                    string message = "Welcome back, " + user.getUsername();
                    NPBinding.UI.ShowToast(message, eToastMessageLength.SHORT);
                }
                else
                {
                    NPBinding.UI.ShowToast("Invalid Credentials.", eToastMessageLength.SHORT);
                    passwordInput.text = "";
                    passwordInput.Select();
                }
            }
            else if (auth_type == "register") {
                int response = Server.CheckRegistration(uwr.downloadHandler.text);
                if (response == 1)
                {
                    UserSession.us.user = user;
                    new ChangeScene().Forward("TwitterLogin");
                    NPBinding.UI.ShowToast("Account Created.", eToastMessageLength.SHORT);
                }
                else
                {
                    if (response == 0)
                        NPBinding.UI.ShowToast("Sorry, that username is taken. Please try something else.", eToastMessageLength.SHORT);
                    usernameInput.text = "";
                    usernameInput.Select();
                }
            }
        }
        StopCoroutine(TryRegister(auth_type, url, json, user));
    }

    public void checkUserPass()
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
            string url=null;
            if (auth_type == "register")
                url = Server.Address("register_user");
            else if (auth_type == "login")
                url = Server.Address("login_user");
            User user = new User(username, password);
            string json = JsonUtility.ToJson(user);
            StartCoroutine(TryRegister(auth_type,url,json,user));
        }
        //DontDestroyOnLoad(GameObject.Find("username"));
        return;
    }
}
