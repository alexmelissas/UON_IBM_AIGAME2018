﻿using UnityEngine;
using UnityEngine.UI;

//! Handle the Settings screen
public class SetSettings : MonoBehaviour {

    public Slider musicSlider, fxSlider;
    public Toggle skipToggle;
    public Text currentloginText, usernameText;
    public GameObject logoutButton, linkTwitterButton, unlinkTwitterButton;

    //! Display the currently selected settings properly
    private void Start()
    {
        musicSlider.normalizedValue = PlayerPrefs.GetFloat("music", 0.65f);
        fxSlider.normalizedValue = PlayerPrefs.GetFloat("fx", 0.7f);
        skipToggle.isOn = (PlayerPrefs.GetInt("skip") != 0);
    }

    //! Update the settings based on changes made to the sliders/checkboxes
    void Update () {
        PlayerPrefs.SetFloat("music",musicSlider.value);
        PlayerPrefs.SetFloat("fx", fxSlider.value);
        PlayerPrefs.SetInt("skip", skipToggle.isOn ? 1 : 0);

        if (UserSession.user_session != null && UserSession.user_session.user.GetUsername() != "")
        {
            currentloginText.GetComponentInChildren<Text>().text = "Logged in as: ";
            usernameText.GetComponentInChildren<Text>().text = UserSession.user_session.user.GetUsername();

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
            usernameText.GetComponentInChildren<Text>().text = "";
            currentloginText.GetComponentInChildren<Text>().text = "No login.";
            linkTwitterButton.SetActive(false);
            unlinkTwitterButton.SetActive(false);
            logoutButton.SetActive(false);
        }
    }      
}
