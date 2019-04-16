using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using VoxelBusters.NativePlugins;

//! Singleton - Store the Player objects for the user and the enemy
public class PlayerSession : MonoBehaviour
{

    public static PlayerSession ps;

    public Player player;
    public Player player_before_battle;
    public Player enemy;
    public int plays_left;

    void Awake()
    {
        if (ps == null)
        {
            DontDestroyOnLoad(gameObject);
            ps = this;
        }
        else if (ps != this)
        {
            Destroy(gameObject);
        }
    }

    public static void GetPlaysLeft()
    {

    }
}
