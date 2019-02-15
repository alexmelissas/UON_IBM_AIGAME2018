using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

using System.Net;

public class registerUser : MonoBehaviour
{
    public InputField usernameInput;
    public InputField passwordInput;

    IEnumerator TryRegister(string url, string json)
    {
        UnityWebRequest uwr = new UnityWebRequest(url, "POST");
        byte[] jsonToSend = new System.Text.UTF8Encoding().GetBytes(json);
        uwr.uploadHandler = (UploadHandler)new UploadHandlerRaw(jsonToSend);
        uwr.downloadHandler = (DownloadHandler)new DownloadHandlerBuffer();
        uwr.SetRequestHeader("Content-Type", "application/json");

        yield return uwr.SendWebRequest();

        if (uwr.isNetworkError)
        {
            Debug.Log("Error While Sending: " + uwr.error);
        }
        else
        {
            if(Server.CheckRegistration(uwr.downloadHandler.text)){
                SceneManager.LoadScene("TwitterLogin");
            }
        }

        StopCoroutine(TryRegister(url, json));
    }

    public void checkUserPass()
    {
        string username = usernameInput.text;
        string password = passwordInput.text;
        if (username == "" || password == "")
        {
            Debug.Log("EMPTY");
        }
        else if (username.Length > 25)
        {
            Debug.Log("TOO LONG");
        }
        else
        {
            string url = Server.Address("register_user");
            string json = JsonUtility.ToJson(new User(username, password));
            StartCoroutine(TryRegister(url, json));
        }
        //DontDestroyOnLoad(GameObject.Find("username"));
        return;       
    }
}