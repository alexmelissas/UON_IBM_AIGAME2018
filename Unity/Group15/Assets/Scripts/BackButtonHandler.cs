using System.Collections;
using System.Collections.Generic;
using UnityEngine;

//! Singleton - Maintain the one screen history list.
public class BackButtonHandler : MonoBehaviour
{

    public static BackButtonHandler bbh;

    void Awake()
    {
        if (bbh == null)
        {
            DontDestroyOnLoad(gameObject);
            bbh = this;
        }
        else if (bbh != this)
        {
            Destroy(gameObject);
        }
    }

    void Update()
    {
        if (Application.platform == RuntimePlatform.Android)
        {
            if (Input.GetKey(KeyCode.Escape))
            {
                gameObject.AddComponent<ChangeScene>().Back();
                return;
            }
        }

    }
}
