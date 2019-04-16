using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AnimationManager : MonoBehaviour {

    public GameObject player_hurt, player_idle, player_attack, player_die;
    public GameObject enemy_hurt, enemy_idle, enemy_attack, enemy_die;

    private Dictionary<string,GameObject> animations;
    private string currentAnimation_player;
    private string currentAnimation_enemy;

    void Start()
    {
        currentAnimation_player = "player_idle";
        currentAnimation_enemy = "enemy_idle";

        animations.Add("player_hurt", player_hurt);
        animations.Add("player_idle", player_idle);
        animations.Add("player_attack", player_attack);
        animations.Add("player_die", player_die);
        animations.Add("enemy_hurt", enemy_hurt);
        animations.Add("enemy_idle", enemy_idle);
        animations.Add("enemy_idle", enemy_attack);
        animations.Add("enemy_die",enemy_die);

        foreach(KeyValuePair<string,GameObject> animation in animations)
        {
            GameObject temp = animation.Value;
            temp.SetActive(false);
        }
    }

    void Update () {

        if(currentAnimation_player != Gameplay.currentAnimation_player)
        {
            GameObject new_animation = animations[Gameplay.currentAnimation_player];
            GameObject old_animation = animations[currentAnimation_player];
            new_animation.SetActive(true);
            old_animation.SetActive(false);
            currentAnimation_player = Gameplay.currentAnimation_player;
        }

        if(currentAnimation_enemy != Gameplay.currentAnimation_enemy)
        {
            GameObject new_animation = animations[Gameplay.currentAnimation_enemy];
            GameObject old_animation = animations[currentAnimation_enemy];
            new_animation.SetActive(true);
            old_animation.SetActive(false);
            currentAnimation_enemy = Gameplay.currentAnimation_enemy;
        }
            
    }

}
