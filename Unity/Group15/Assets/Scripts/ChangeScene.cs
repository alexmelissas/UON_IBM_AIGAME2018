using System;
using UnityEngine;
using UnityEngine.SceneManagement;

//! Scene forward/back management
public class ChangeScene : MonoBehaviour {

    //! Go forward a scene and add current scene to SceneHistory
    public void Forward(String next_scene){
        SceneHistory.sh.scenes.Add(SceneManager.GetActiveScene().name);
        if (next_scene == "Overworld" || next_scene == "StartScreen") SceneHistory.sh.scenes.Clear(); //to not go back to login/register after in game
        SceneManager.LoadScene(next_scene);
        return;
    }

    //! Go back to the top scene of SceneHistory
    public void Back() {
        if(SceneHistory.sh.scenes!=null)
        {
            int top = SceneHistory.sh.scenes.Count - 1;
            if (top >= 0)
            {
                SceneManager.LoadScene(SceneHistory.sh.scenes[top]);
                SceneHistory.sh.scenes.RemoveAt(top);
            }
            else
                Application.Quit();
        }
        else
            Application.Quit();
        return;
    }
}
