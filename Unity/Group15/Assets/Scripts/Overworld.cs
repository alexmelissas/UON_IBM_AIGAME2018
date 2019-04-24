using UnityEngine;
using UnityEngine.UI;

//! View username on the overworld
public class Overworld : MonoBehaviour {

    public Text usernameText, playsUnrankedText, playsRankedText;

    //! Check the user's remaining Plays for the day
    private void GetPlays() { StartCoroutine(Server.GetPlaysLeft()); }

    //! Update the Player and the Plays left
    private void Start()
    {
        gameObject.AddComponent<UpdateSessions>().U_All();
        Invoke("GetPlays", 0.5f);
    }
    
    //! Keep the logged-in user's username and plays left on the top of the screen
    private void Update ()
    {
        if (UserSession.user_session != null && UserSession.user_session.user.GetUsername()!="")
        {
            usernameText.GetComponentInChildren<Text>().text = UserSession.user_session.user.GetUsername();
            playsUnrankedText.GetComponentInChildren<Text>().text = "" + PlayerSession.player_session.plays_left_unranked;
            playsRankedText.GetComponentInChildren<Text>().text = "" + PlayerSession.player_session.plays_left_ranked;
        }
        else
        {
            usernameText.GetComponentInChildren<Text>().text = "<Invalid Session>";
            playsUnrankedText.GetComponentInChildren<Text>().text = "";
            playsRankedText.GetComponentInChildren<Text>().text = "";
        }
    }
}
