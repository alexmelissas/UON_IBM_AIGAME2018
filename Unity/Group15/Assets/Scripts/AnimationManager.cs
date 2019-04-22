using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AnimationManager : MonoBehaviour {

    public GameObject player_hurt_Animation, player_idle_Animation, player_attack_Animation, player_die_Animation;
    public GameObject enemy_hurt_Animation, enemy_idle_Animation, enemy_attack_Animation, enemy_die_Animation;

    private Dictionary<string,GameObject> animations;
    private string currentAnimation_player;
    private string currentAnimation_enemy;
    private string played_player;
    private string played_enemy;

    void Start()
    {
        animations = new Dictionary<string, GameObject>
        {
            { "player_hurt_Animation", player_hurt_Animation },
            { "player_idle_Animation", player_idle_Animation },
            { "player_attack_Animation", player_attack_Animation },
            { "player_die_Animation", player_die_Animation },
            { "enemy_hurt_Animation", enemy_hurt_Animation },
            { "enemy_idle_Animation", enemy_idle_Animation },
            { "enemy_attack_Animation", enemy_attack_Animation },
            { "enemy_die_Animation", enemy_die_Animation }
        };

        foreach (KeyValuePair<string,GameObject> animation in animations)
            animation.Value.SetActive(false);

        currentAnimation_player = "player_idle_Animation";
        currentAnimation_enemy = "enemy_idle_Animation";
        animations["player_idle_Animation"].SetActive(true);
        animations["enemy_idle_Animation"].SetActive(true);
    }

    void Update () {

        if (Gameplay.ended)
        {
            animations[currentAnimation_player].SetActive(false);
            animations[currentAnimation_enemy].SetActive(false);
            return;
        }

        if (currentAnimation_player != Gameplay.currentAnimation_player)
        {
            string new_animation_name = "" + Gameplay.currentAnimation_player;
            string old_animation_name = "" + currentAnimation_player;
            animations[new_animation_name].SetActive(true);
            animations[old_animation_name].SetActive(false);
            currentAnimation_player = Gameplay.currentAnimation_player;
        }

        if(currentAnimation_enemy != Gameplay.currentAnimation_enemy)
        {
            string new_animation_name = "" + Gameplay.currentAnimation_enemy;
            string old_animation_name = "" + currentAnimation_enemy;
            animations[new_animation_name].SetActive(true);
            animations[old_animation_name].SetActive(false);
            currentAnimation_enemy = Gameplay.currentAnimation_enemy;
        }
            
    }

}
