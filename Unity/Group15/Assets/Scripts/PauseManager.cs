using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using VoxelBusters.NativePlugins;

public class PauseManager : MonoBehaviour {

    private bool start = true;

    public void Save()
    {
        if (UserSession.us.user != null && UserSession.us.user.getID() != "")
        {
            PlayerPrefs.SetString("id", UserSession.us.user.getID());
            PlayerPrefs.Save();
        }
    }

    public void Load()
    {
        if (!start)
        {
            if (PlayerPrefs.HasKey("id") && PlayerPrefs.GetString("id") != "")
            {
                NPBinding.UI.ShowToast("id: "+PlayerPrefs.GetString("id"), eToastMessageLength.SHORT);
                string scene = SceneManager.GetActiveScene().name;
                if (scene == "StartScreen" || scene == "Start_Login" || scene == "TwitterLogin" || scene == "ModelCreated" || scene == "CharacterCreation" || scene == "CreateAccount") //need other solution for settings
                    gameObject.AddComponent<UpdateSessions>().U_User();
                else
                    gameObject.AddComponent<UpdateSessions>().U_All();
            }                          
        }         
    }

    void OnApplicationPause(bool pauseStatus)
    {
        if (pauseStatus)
        {
            start = false;
            Save();
        }
        else Load();
    }
}
