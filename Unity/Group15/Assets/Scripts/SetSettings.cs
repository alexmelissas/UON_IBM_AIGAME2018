using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SetSettings : MonoBehaviour {

    public Slider musicSlider;
    public Slider fxSlider;
    public Toggle challengeToggle;
    public Toggle skipToggle;
    public Text currentloginLabel;
    public Text usernameLabel;
    public GameObject notyouLabel;
    public GameObject logoutButton;

    private void Start()
    {
        musicSlider.normalizedValue = PlayerPrefs.GetFloat("music");
        fxSlider.normalizedValue = PlayerPrefs.GetFloat("fx");
        challengeToggle.isOn = (PlayerPrefs.GetInt("challenged") != 0);
        skipToggle.isOn = (PlayerPrefs.GetInt("skip") != 0);
    }

    void Update () {
        PlayerPrefs.SetFloat("music",musicSlider.value);
        PlayerPrefs.SetFloat("fx", fxSlider.value);
        PlayerPrefs.SetInt("challenged", challengeToggle.isOn ? 1 : 0);
        PlayerPrefs.SetInt("skip", skipToggle.isOn ? 1 : 0);

        if (UserSession.us != null)
        {
            if (UserSession.us.user.GetUsername() == "")
            {
                usernameLabel.GetComponentInChildren<Text>().text = "";
                currentloginLabel.GetComponentInChildren<Text>().text = "Not logged in yet.";                
                logoutButton.SetActive(false);
                notyouLabel.SetActive(false);
            }
            else
            {
                currentloginLabel.GetComponentInChildren<Text>().text = "You are logged in as: ";
                usernameLabel.GetComponentInChildren<Text>().text = UserSession.us.user.GetUsername();
                logoutButton.SetActive(true);
                notyouLabel.SetActive(true);
            }
        }
        else
        {
            usernameLabel.GetComponentInChildren<Text>().text = "";
            currentloginLabel.GetComponentInChildren<Text>().text = "Not logged in yet.";            
            logoutButton.SetActive(false);
            notyouLabel.SetActive(false);
        }
    }      
}
