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
        if (UserSession.us.user != null && UserSession.us.user.GetID() != "")
        {
            if (start || AccountComplete())
            {
                ZPlayerPrefs.SetString("id", UserSession.us.user.GetID());
                skipSelected = (PlayerPrefs.GetInt("skip")) == 1 ? true : false;
                if (!skipSelected && SceneManager.GetActiveScene().name == "Battle") PlayerPrefs.SetInt("skip", 1);
                ZPlayerPrefs.Save();
            }
            else //FIX THIS CONDITIONAL
            {
                ZPlayerPrefs.DeleteKey("id");
                skipSelected = (PlayerPrefs.GetInt("skip")) == 1 ? true : false;
                if (!skipSelected && SceneManager.GetActiveScene().name == "Battle") PlayerPrefs.SetInt("skip", 1);
                StartCoroutine(DeleteAccount());
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

    private IEnumerator DeleteAccount()
    {
        UnityWebRequest uwr = UnityWebRequest.Delete(Server.Address("delete_user") + UserSession.us.user.GetID());
        yield return uwr.SendWebRequest();

        if (uwr.isNetworkError) yield return DeleteAccount();
        yield break;
    }

    private void Load()
    {
        if (!start)
        {
            if (skipSelected == false) PlayerPrefs.SetInt("skip", 0);
            if (PlayerPrefs.HasKey("id") && ZPlayerPrefs.GetRowString("id") != "")
            {
                gameObject.AddComponent<UpdateSessions>().U_All();
                string scene = SceneManager.GetActiveScene().name;
                if (scene == "Start_Login" || scene == "TwitterLogin" || scene == "ModelCreated"
                    || scene == "CharacterCreation" || scene == "CreateAccount") //need other solution for settings
                {
                    gameObject.AddComponent<ChangeScene>().Forward("StartScreen");
                }
            } 
            else if(LoginTwitter.leftForTwitter==false)
                gameObject.AddComponent<ChangeScene>().Forward("StartScreen");
        }         
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
