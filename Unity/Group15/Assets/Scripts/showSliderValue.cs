using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class showSliderValue : MonoBehaviour {

    Text sliderOutput;
    
	void Start () {
        sliderOutput = GetComponent<Text> ();
	}
	
	public void textUpdate (float value) {
        sliderOutput.text = "" + Mathf.RoundToInt(value);
	}
}
