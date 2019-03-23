using System.Collections;
using System.Collections.Generic;
using UnityEngine;

//! Singleton - Store list of scene viewing hierarchy
public class SceneHistory : MonoBehaviour
{

    public static SceneHistory sh;

    public List<string> scenes;

    void Awake()
    {
        if (sh == null)
        {
            DontDestroyOnLoad(gameObject);
            sh = this;
        }
        else if (sh != this)
        {
            Destroy(gameObject);
        }
    }
}
