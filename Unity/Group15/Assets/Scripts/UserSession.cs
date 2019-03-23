using UnityEngine;

//! Singleton - Store the User object
public class UserSession : MonoBehaviour {

    public static UserSession us;

    public User user;

	void Awake () {
        if (us == null)
        {
            DontDestroyOnLoad(gameObject);
            us = this;
        }
        else if(us!=this)
        {
            Destroy(gameObject);
        }
	}
}
