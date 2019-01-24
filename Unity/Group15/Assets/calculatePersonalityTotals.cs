using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class calculatePersonalityTotals : MonoBehaviour {

    public static float trait1 = 0;
    public static float trait2 = 0;
    public static float trait3 = 0;
    public static float trait4 = 0;
    public static float trait5 = 0;

    public Slider trait1slider;
    public Slider trait2slider;
    public Slider trait3slider;
    public Slider trait4slider;
    public Slider trait5slider;
    public Text totalLabel;
    public Button submitButton;
    
	void Update () {

        trait1 = trait1slider.value;
        trait2 = trait2slider.value;
        trait3 = trait3slider.value;
        trait4 = trait4slider.value;
        trait5 = trait5slider.value;
        float trait_total = trait1 + trait2 + trait3 + trait4 + trait5;

        totalLabel.text = "" + trait_total;

        if (trait_total == 15) { 
            totalLabel.color = Color.green;
            submitButton.enabled = true;
            submitButton.GetComponentInChildren<Text>().color = Color.black;
        }
        else {
            submitButton.enabled = false;
            submitButton.GetComponentInChildren<Text>().color = Color.gray;
            if (trait_total < 15)
                totalLabel.color = Color.blue;
            if (trait_total > 15)
                totalLabel.color = Color.red;
        }
        
    }
}
