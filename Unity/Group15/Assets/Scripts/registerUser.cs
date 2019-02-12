using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

using System.Net;

public class registerUser : MonoBehaviour
{
    public InputField username_field;
    public InputField password_field;

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
            Debug.Log("Received: " + uwr.downloadHandler.text);
        }
    }

    public void checkUserPass()
    {
        string username = username_field.text;
        string password = password_field.text;
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
            string url = "http://132.232.30.215:8080/users/";
            string json = JsonUtility.ToJson(new User(username, password));
            StartCoroutine(TryRegister(url, json));
        }
        return;       
    }
}