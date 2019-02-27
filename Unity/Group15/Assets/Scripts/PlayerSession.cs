using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using VoxelBusters.NativePlugins;

public class PlayerSession : MonoBehaviour
{

    public static PlayerSession ps;

    public Player player;

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
