using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class loginTwitter : MonoBehaviour {

	public void openTwitter()
    {
        Application.OpenURL("http://www.twitter.com"); //go to our server's authorisation page
        // then need to check if user did log in, if not disable next button
    }
}
