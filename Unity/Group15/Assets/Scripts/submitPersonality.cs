using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class submitPersonality : MonoBehaviour {

    public Text totalLabel;

    public void checkSubmission () {
        float trait_total = float.Parse(totalLabel.text);
        if (trait_total != 15)
            Debug.Log("ERROR with totals.");
        else
            new ChangeScene().Forward("ModelCreated");
    }
}
