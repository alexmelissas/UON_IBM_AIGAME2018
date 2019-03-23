using UnityEngine;
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
            ZPlayerPrefs.SetString("id", UserSession.us.user.GetID());
            skipSelected = (PlayerPrefs.GetInt("skip")) == 1 ? true : false;
            if(!skipSelected && SceneManager.GetActiveScene().name=="Battle") PlayerPrefs.SetInt("skip", 1);
            ZPlayerPrefs.Save();
        }
    }

    private void Load()
    {
        if (!start)
        {
            if (skipSelected == false) PlayerPrefs.SetInt("skip", 0);
            if (PlayerPrefs.HasKey("id") && ZPlayerPrefs.GetRowString("id") != "")
            {
                string scene = SceneManager.GetActiveScene().name;
                if (scene == "StartScreen" || scene == "Start_Login" || scene == "TwitterLogin" 
                    || scene == "ModelCreated" || scene == "CharacterCreation" || scene == "CreateAccount") //need other solution for settings
                    gameObject.AddComponent<UpdateSessions>().U_User();
                else
                    gameObject.AddComponent<UpdateSessions>().U_All();
                
            }                          
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
