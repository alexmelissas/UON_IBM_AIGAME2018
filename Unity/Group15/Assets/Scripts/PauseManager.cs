using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.SceneManagement;
using VoxelBusters.NativePlugins;

//! Keep User/Player data stored when exiting app, reload them when coming back
public class PauseManager : MonoBehaviour {

    private bool start = true;
    private bool skipSelected;

    private void Awake()
    {
        ZPlayerPrefs.Initialize("small fluffy puppies", "I'mASaltySalter");
    }

    private void Save()
    {
        skipSelected = (PlayerPrefs.GetInt("skip")) == 1 ? true : false;
        if (!skipSelected && SceneManager.GetActiveScene().name == "Battle") PlayerPrefs.SetInt("skip", 1);

        if (UserSession.us.user != null && UserSession.us.user.GetID() != "")
        {
            if(StartScreens() && !LoginTwitter.leftForTwitter && !AuthenticateUser.logging_in) //FIX THIS CONDITIONAL
            {
                StartCoroutine(Server.DeleteAccount());
            }
            else
            {
                ZPlayerPrefs.SetString("id", UserSession.us.user.GetID());
                ZPlayerPrefs.Save();
            }
        }
    }

    private bool AccountComplete()
    {
        gameObject.AddComponent<UpdateSessions>().U_Player();
        if (PlayerSession.ps.player.id == "") return false;
        return true;
    }
    

    private void Load()
    {
        if (!start)
        {
            if (skipSelected == false) PlayerPrefs.SetInt("skip", 0);
            if (ZPlayerPrefs.HasKey("id") && ZPlayerPrefs.GetRowString("id") != "")
            {
                if(StartScreens() && !LoginTwitter.leftForTwitter)
                    gameObject.AddComponent<ChangeScene>().Forward("StartScreen");
            }
            else if(!LoginTwitter.leftForTwitter)
            {
                Debug.Log("Exception: no ID");
                gameObject.AddComponent<ChangeScene>().Forward("StartScreen");
            }
        }         
    }

    private bool StartScreens()
    {
        string scene = SceneManager.GetActiveScene().name;
        if (scene == "Start_Login" || scene == "TwitterLogin" || scene == "CharacterCreation" || scene == "CreateAccount")
            return true;
        return false;
    }

    private void OnApplicationPause(bool pauseStatus)
    {
        if (pauseStatus)
        {
            start = false;
            Save();
        }
        else Load();
    }

}
