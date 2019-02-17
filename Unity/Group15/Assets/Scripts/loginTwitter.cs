using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class loginTwitter : MonoBehaviour {

    GameObject reference;

	public void openTwitter()
    {
        // get id from username on server
        string url = Server.Address("login_twitter");
        // url += id;
        Application.OpenURL(url);
        // then need to check if user did log in, if not disable next button
    }
}
