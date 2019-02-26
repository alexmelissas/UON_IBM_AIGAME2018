using System.Collections;
using System.Collections.Generic;
using UnityEngine;

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
