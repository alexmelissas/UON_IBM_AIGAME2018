using UnityEngine;

//! Singleton - Store the Player objects for the user and the enemy
public class PlayerSession : MonoBehaviour
{
    // The Singleton object
    public static PlayerSession player_session;

    public Player player;
    //! An old copy of the player for comparisons before and after a battle
    public Player player_before_battle;
    public Player enemy;
    public int plays_left;

    //! Handle the Singleton object
    void Awake()
    {
        if (player_session == null)
        {
            DontDestroyOnLoad(gameObject);
            player_session = this;
        }
        else if (player_session != this)
        {
            Destroy(gameObject);
        }
    }
}
