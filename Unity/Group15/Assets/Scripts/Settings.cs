using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Settings : MonoBehaviour{

    public static Settings set;

    public float music;
    public float soundfx;
    public bool challenged;
    public bool wonbattle;
    public bool lostbattle;
    public User current_user; //necessary?

    void Awake()
    {
        if (set == null)
        {
            DontDestroyOnLoad(gameObject);
            set = this;
        }
        else if (set != this)
        {
            Destroy(gameObject);
        }
    }
}
