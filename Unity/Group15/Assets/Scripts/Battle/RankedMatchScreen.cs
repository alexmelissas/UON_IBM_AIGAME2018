using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;
using VoxelBusters.NativePlugins;

//! Ranked PvP screen handling
public class RankedMatchScreen : MonoBehaviour
{
    public GameObject rankingsDisplayGroup, loading_spin_Animation;
    public AudioSource audiosrc;

    private List<Player> players;
    private string oldRank = "";
    private int attempts;
    private float max_volume;

    //! Setup the screen
    private void Start()
    {
        loading_spin_Animation.SetActive(false);
        max_volume = PlayerPrefs.GetFloat("fx") / 2;
        audiosrc.playOnAwake = true;
        audiosrc.volume = 0; 
    }

    //! Used for fading in the music
    private void Update()
    {
        if(audiosrc.volume<max_volume)
        {
            audiosrc.volume += 0.008f; // fade-in for music
        }
    }

    //! GET the top 5 players of each rank
    IEnumerator GetPlayers(string newRank)
    {
        if (newRank != oldRank) // Only update when different rank is clicked.
        {
            //string url = Server.Address("ranked_leaderboards") + newRank;
            using (UnityWebRequest uwr = UnityWebRequest.Get(Server.Address("view_users"))) // new API eg. /players/rank/{rank}
            {
                uwr.timeout = 10;
                yield return uwr.SendWebRequest();

                string all = uwr.downloadHandler.text.Trim(new char[] { '[', ']' }); // Split the entire all-player JSON into individual-user JSONs.
                string[] separators = { "},", "}" };
                string[] entries = all.Split(separators, System.StringSplitOptions.RemoveEmptyEntries);

                for (int i = 0; i < entries.Length; i++)
                {
                    if (i == 5) break;
                    string newUserJson = entries[i] + "}";
                    Player newPlayer = Player.CreatePlayerFromJSON(newUserJson);
                    players.Add(newPlayer);
                }
                // Users.Sort(); //Need to sort based on Points (top 5).
                uwr.Dispose();
            }

            int s = 0;
            foreach (Player current in players)
            {
                string name = current.characterName;
                string currentslot = "slot" + (++s);
                rankingsDisplayGroup.transform.Find(currentslot).gameObject.GetComponentInChildren<Text>().text = name;
            }
            oldRank = newRank;
            StopCoroutine(GetPlayers(newRank));
            yield break;
        }
    }

    //! Show the top players of selected rank
    public void DisplayTopPlayers(string rank)
    {
        players = new List<Player>();
        StartCoroutine(GetPlayers(rank));
    }
    
    //! Initiate the PvP match
    public void Play()
    {
        if (PlayerSession.player_session.plays_left_ranked <= 0)
        {
            NPBinding.UI.ShowToast("No plays left. Check back tomorrow!", eToastMessageLength.SHORT);
            return;
        }
        loading_spin_Animation.SetActive(true);
        PlayerPrefs.SetInt("battle_type", 1);
        attempts = 0;
        Invoke("StartCheck", 0.4f);
    }

    //! Press button to initiate matchmaking
    private void StartCheck() { StartCoroutine(CheckEnemy()); }
    
    //! Increase attempts
    private void IncreaseAttempts() { attempts++; }

    //! Recursively try to find an enemy 3 times (in case of errors). If not found after 4 tries, stop.
    private IEnumerator CheckEnemy()
    {
        StartCoroutine(Server.GetEnemy(1));
        yield return new WaitUntil(() => Server.findEnemy_done == true);

        if (PlayerSession.player_session.enemy.id != "")
        {
            gameObject.AddComponent<ChangeScene>().Forward("Battle");
        }
        else if (attempts < 3) //recursively try to find enemy 3 times
        {
            IncreaseAttempts();
            StartCoroutine(CheckEnemy());
        }
        else
            NPBinding.UI.ShowToast("No enemy found. Try again later.", eToastMessageLength.SHORT);

        yield break;
    }

}
