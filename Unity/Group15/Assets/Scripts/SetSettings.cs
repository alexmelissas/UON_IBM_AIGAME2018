using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

//! Handle the Settings screen
public class SetSettings : MonoBehaviour {

    public Slider musicSlider, fxSlider;
    public Toggle challengeToggle, skipToggle;
    public Text currentloginLabel, usernameLabel;
    public GameObject logoutButton, linkTwitterButton, unlinkTwitterButton;

    //! Display the currently selected settings properly
    private void Start()
    {
        musicSlider.normalizedValue = PlayerPrefs.GetFloat("music", 0.65f);
        fxSlider.normalizedValue = PlayerPrefs.GetFloat("fx", 0.7f);
        challengeToggle.isOn = (PlayerPrefs.GetInt("challenged") != 0);
        skipToggle.isOn = (PlayerPrefs.GetInt("skip") != 0);
    }

    //! Update the settings based on changes made to the sliders/checkboxes
    void Update () {
        PlayerPrefs.SetFloat("music",musicSlider.value);
        PlayerPrefs.SetFloat("fx", fxSlider.value);
        PlayerPrefs.SetInt("challenged", challengeToggle.isOn ? 1 : 0);
        PlayerPrefs.SetInt("skip", skipToggle.isOn ? 1 : 0);

        if (UserSession.us != null && UserSession.us.user.GetUsername() != "")
        {
            currentloginLabel.GetComponentInChildren<Text>().text = "Logged in as: ";
            usernameLabel.GetComponentInChildren<Text>().text = UserSession.us.user.GetUsername();

            if (Server.CheckTwitter())
            {
                linkTwitterButton.SetActive(false);
                unlinkTwitterButton.SetActive(true);
            }
            else
            {
                linkTwitterButton.SetActive(true);
                unlinkTwitterButton.SetActive(false);
            }

            logoutButton.SetActive(true);
            
        }
        else
        {
            usernameLabel.GetComponentInChildren<Text>().text = "";
            currentloginLabel.GetComponentInChildren<Text>().text = "No login.";
            linkTwitterButton.SetActive(false);
            unlinkTwitterButton.SetActive(false);
            logoutButton.SetActive(false);
        }
    }      
}
