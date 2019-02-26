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
        musicSlider.normalizedValue = PlayerPrefs.GetFloat("music");
        fxSlider.normalizedValue = PlayerPrefs.GetFloat("fx");
        challengeToggle.isOn = (PlayerPrefs.GetInt("challenged") != 0);
        winToggle.isOn = (PlayerPrefs.GetInt("winbattle") != 0);
        loseToggle.isOn = (PlayerPrefs.GetInt("losebattle") != 0);
    }

    void Update () {
        PlayerPrefs.SetFloat("music",musicSlider.value);
        PlayerPrefs.SetFloat("fx", fxSlider.value);
        PlayerPrefs.SetInt("challenged", challengeToggle.isOn ? 1 : 0);
        PlayerPrefs.SetInt("winbattle", winToggle.isOn ? 1 : 0);
        PlayerPrefs.SetInt("losebattle", loseToggle.isOn ? 1 : 0);

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
