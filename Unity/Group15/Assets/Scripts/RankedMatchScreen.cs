using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;
using VoxelBusters.NativePlugins;

//! PvP screen handling
public class RankedMatchScreen : MonoBehaviour
{
    public GameObject display;

    private List<User> users;
    private string oldRank = "";

    //! GET the top 5 players of each rank
    IEnumerator GetPlayers(string newRank)
    {
        if (newRank != oldRank) // Only update when different rank is clicked.
        { 
            // Here would need to get users of THAT RANK. Now just all people.
            // Also need to get top 5 - server-side implementation.

            using (UnityWebRequest uwr = UnityWebRequest.Get(Server.Address("view_users")))
            {
                uwr.timeout = 10;
                yield return uwr.SendWebRequest();

                string all = uwr.downloadHandler.text.Trim(new char[] { '[', ']' }); // Split the entire huge all-player JSON into individual-user JSONs.
                string[] separators = { "},", "}" };
                string[] entries = all.Split(separators, System.StringSplitOptions.RemoveEmptyEntries);

                for (int i = 0; i < entries.Length; i++)
                {
                    if (i == 5) break;
                    string newUserJson = entries[i] + "}";
                    User newuser = User.CreateUserFromJSON(newUserJson);
                    users.Add(newuser);
                }
                // Users.Sort(); //Need to sort based on Points (top 5).
                uwr.Dispose();
            }

            int s = 0;
            foreach (User current in users)
            {
                string name = current.username;
                string currentslot = "Slot" + (++s);
                display.transform.Find(currentslot).gameObject.GetComponentInChildren<Text>().text = name;
            }
            oldRank = newRank;
            StopCoroutine(GetPlayers(newRank));
            yield break;
        }
    }

    //! Show the top players of selected rank
    public void DisplayTopPlayers(string rank)
    {
        users = new List<User>();
        StartCoroutine(GetPlayers(rank));
    }
    
    //! Initiate the PvP match
    public void Play()
    {
        if (PlayerSession.ps.plays_left <= 0)
        {
            NPBinding.UI.ShowToast("No plays left. Check back tomorrow!", eToastMessageLength.SHORT);
            return;
        }
        PlayerPrefs.SetInt("battle_type", 1);
        StartCoroutine(CheckEnemy());
    }
    
    //! Recursively try to find an enemy 3 times (in case of errors). If not found after 4 tries, stop.
    private IEnumerator CheckEnemy()
    {
        int attempts = 0;
        StartCoroutine(Server.GetEnemy(1));
        if (PlayerSession.ps.enemy.id != "")
            gameObject.AddComponent<ChangeScene>().Forward("Battle");
        else if (attempts < 3) //recursively try to find enemy 3 times
        {
            yield return new WaitUntil(() => Server.findenemy_done == true);
            StartCoroutine(Server.GetEnemy(1));
            attempts++;
            Invoke("CheckEnemy", 0.5f);
        }
        else
            NPBinding.UI.ShowToast("No enemy found. Try again later.", eToastMessageLength.SHORT);
        yield break;
    }
}
