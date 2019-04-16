using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class WinLosePopup : MonoBehaviour {

    public Text win_moneygained, win_current_level, win_next_level;
    public Text lose_moneygained, lose_current_level, lose_next_level;
    public Slider win_exp_slider, lose_exp_slider;
    public AudioSource soundsrc;
    public AudioClip levelup;

    private Text moneygained, current_level, next_level;
    private Slider exp_slider;

    private Player before, after;

    private bool didSetup = false;
    private bool gainedLevel = false;
    private float addedExp = 0;

    private void Update()
    {
        if(Gameplay.updatePlayer!=-1 && didSetup==false)
        {
            Setup();
            didSetup = true;
        }

        if(didSetup==true)
        {
            if (before.experience < after.experience)
            {
                if (exp_slider.normalizedValue != 1)
                {
                    exp_slider.normalizedValue += 0.002f;

                    if (gainedLevel) addedExp += after.exptolevel * 0.002f;
                    else addedExp += before.exptolevel * 0.002f;

                    if (addedExp >= 1)
                    {
                        before.experience++;
                        addedExp = 0;
                    }
                }
            }
            else if (before.level != after.level)
            {
                if (exp_slider.normalizedValue != 1)
                {
                    exp_slider.normalizedValue += 0.002f;

                    if (gainedLevel) addedExp += after.exptolevel * 0.002f;
                    else addedExp += before.exptolevel * 0.002f;

                    if (addedExp >= 1)
                    {
                        before.experience++;
                        addedExp = 0;
                    }

                }
                else
                {
                    exp_slider.normalizedValue = 0;
                    soundsrc.PlayOneShot(levelup, PlayerPrefs.GetFloat("fx"));
                    before.level++;
                    before.experience = 0;
                    gainedLevel = true;
                    current_level.text = "" + before.level;
                    next_level.text = "" + (before.level + 1);
                }
            }
        }
        
    }

    private void Setup()
    {
        before = PlayerSession.ps.player_before_battle;
        after = PlayerSession.ps.player;
        
        addedExp = 0;

        if(Gameplay.updatePlayer==1)
        {
            moneygained = lose_moneygained;
            current_level = lose_current_level;
            next_level = lose_next_level;
            exp_slider = lose_exp_slider;
        }
        else
        {
            moneygained = win_moneygained;
            current_level = win_current_level;
            next_level = win_next_level;
            exp_slider = win_exp_slider;
        }
        
        exp_slider.normalizedValue = (float)before.experience / (float)before.exptolevel;
        current_level.text = "" + before.level;
        next_level.text = "" + (before.level + 1);
        moneygained.text = "" + (after.money - before.money);

        PlayerSession.ps.player_before_battle = new Player();
    }
}
