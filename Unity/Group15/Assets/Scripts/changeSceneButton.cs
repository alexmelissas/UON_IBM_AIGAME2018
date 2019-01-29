using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class changeSceneButton : MonoBehaviour {

    public void changeScene(String scenename){
        SceneManager.LoadScene(scenename);
    }

}
