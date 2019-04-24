using UnityEngine;
using UnityEngine.UI;

//! Check for twitter linkage and allow user to proceed.
public class AgreeTwitter : MonoBehaviour {

    public Toggle agreeToggle;
    public Button nextButton;
    public GameObject consent;
    
    private void Awake()
    {
        nextButton.enabled = false;
        nextButton.GetComponentInChildren<Text>().color = Color.gray;
    }

    //! Keep checking whether to display the Twitter consent option
    void Update() {

        if(LoginTwitter.allowNextForSkip)
        {
            nextButton.enabled = true;
            nextButton.GetComponentInChildren<Text>().color = Color.black;
            return;
        }

        if (Server.CheckTwitter()) consent.SetActive(true);
        else consent.SetActive(false);

        if (agreeToggle.isOn == true) {
            nextButton.enabled = true;
            nextButton.GetComponentInChildren<Text>().color = Color.black;
        }
        else {
            nextButton.enabled = false;
            nextButton.GetComponentInChildren<Text>().color = Color.gray;
        }
	}

    //! Done with twitter (either logged in or skipped) - move to Ideals selection
    public void Next()
    {
        LoginTwitter.allowNextForSkip = false;
        gameObject.AddComponent<ChangeScene>().Forward("Ideals");
    }
}
