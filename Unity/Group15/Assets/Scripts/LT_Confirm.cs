using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

//! Check if user agreed to Terms of Usage with twitter
public class LT_Confirm : MonoBehaviour {

    public GameObject consent;
    
	void Update () {
        if (Server.CheckTwitter()) consent.SetActive(true);        
        else consent.SetActive(false);
	}
}
