using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class LT_Confirm : MonoBehaviour {

    public GameObject consent;
    
	void Update () {
        if (Server.CheckTwitter()) consent.SetActive(true);        
        else consent.SetActive(false);
	}
}
