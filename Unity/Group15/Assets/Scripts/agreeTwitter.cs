using UnityEngine;
using UnityEngine.UI;

public class agreeTwitter : MonoBehaviour {

    public Toggle toggle;
    public Button button;

    void Update() {
        if (toggle.isOn == true) {
            button.enabled = true;
            button.GetComponentInChildren<Text>().color = Color.black;
        }
        else {
            button.enabled = false;
            button.GetComponentInChildren<Text>().color = Color.gray;
        }
	}
}
