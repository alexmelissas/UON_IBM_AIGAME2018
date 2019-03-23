using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using VoxelBusters.NativePlugins;

//! Singleton - Store the Player objects for the user and the enemy
public class PlayerSession : MonoBehaviour
{

    public static PlayerSession ps;

    public Player player;
    public Player updatedPlayer;
    public Player enemy;

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
}
