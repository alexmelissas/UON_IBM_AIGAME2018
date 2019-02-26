using UnityEngine;
using UnityEngine.UI;

public class ShowPlayerStats : MonoBehaviour {

    public Text text;

	void Start () {
        text.text = JsonUtility.ToJson(PlayerSession.ps.player);
	}
}
