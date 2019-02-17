using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SetSettings : MonoBehaviour {

    public Slider musicSlider;
    public Slider fxSlider;
    public Toggle challengeToggle;
    public Toggle winToggle;
    public Toggle loseToggle;
    public Text currentloginLabel;
    public GameObject notyouLabel;
    public GameObject logoutButton;

    private void Start()
    {
        if(Settings.set!=null)
        {
            musicSlider.normalizedValue = Settings.set.music; //issue with numbering i guess
            fxSlider.normalizedValue = Settings.set.soundfx;
            challengeToggle.isOn = Settings.set.challenged;
            winToggle.isOn = Settings.set.wonbattle;
            loseToggle.isOn = Settings.set.lostbattle;
        }
    }

    void Update () {
        Settings.set.music = musicSlider.value;
        Settings.set.soundfx = fxSlider.value;
        Settings.set.challenged = challengeToggle.isOn;
        Settings.set.wonbattle = winToggle.isOn;
        Settings.set.lostbattle = loseToggle.isOn;

        if (UserSession.us != null)
        {
            if (UserSession.us.user.getUsername() == "")
            {
                currentloginLabel.GetComponentInChildren<Text>().text = "You are not logged in yet.";
                logoutButton.SetActive(false);
                notyouLabel.SetActive(false);
            }
            else
            {
                currentloginLabel.GetComponentInChildren<Text>().text = "You are logged in as: " + UserSession.us.user.getUsername();
                logoutButton.SetActive(true);
                notyouLabel.SetActive(true);
            }
        }
        else
        {
            currentloginLabel.GetComponentInChildren<Text>().text = "You are not logged in yet.";
            logoutButton.SetActive(false);
            notyouLabel.SetActive(false);
        }
    }      
}
