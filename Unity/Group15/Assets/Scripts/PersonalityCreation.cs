using System.Collections;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
using VoxelBusters.NativePlugins;

//! Ideal Personality Creation management
public class PersonalityCreation : MonoBehaviour {

    public Slider trait1slider, trait2slider, trait3slider, trait4slider, trait5slider;
    public Button submitButton;
    public Text t1out, t2out, t3out, t4out, t5out;
    private float trait1, trait2, trait3, trait4, trait5;

    //! Take the value of the slider for that attribute
    private float HandleAttribute(Slider slider, Text display)
    {
        float slider_value = Mathf.RoundToInt(slider.value);
        display.text = "" + slider_value;
        if (slider_value > 8) slider_value = 8;
        else if (slider_value < 2) slider_value = 2;
        return slider_value /10;
    }

    //! Keep actual attribute values up-to-date with sliders
    private void Update()
    {
        trait1 = HandleAttribute(trait1slider, t1out);
        trait2 = HandleAttribute(trait2slider, t2out);
        trait3 = HandleAttribute(trait3slider, t3out);
        trait4 = HandleAttribute(trait4slider, t4out);
        trait5 = HandleAttribute(trait5slider, t5out);
    }

    //! PUT ideals into server for comparisons
    private IEnumerator PutIdeals(string json)
    {
        UnityWebRequest uwr = new UnityWebRequest(Server.Address("submit_ideals") + UserSession.us.user.GetID(), "PUT");
        byte[] jsonToSend = new System.Text.UTF8Encoding().GetBytes(json);
        uwr.uploadHandler = (UploadHandler)new UploadHandlerRaw(jsonToSend);
        uwr.downloadHandler = (DownloadHandler)new DownloadHandlerBuffer();
        uwr.SetRequestHeader("Content-Type", "application/json");

        yield return uwr.SendWebRequest();

        if (uwr.isNetworkError)
        {
            Debug.Log("Error While Sending: " + uwr.error);
            NPBinding.UI.ShowToast("Communication Error. Please try again later.", eToastMessageLength.SHORT);
        }
        else
        {
            Debug.Log("" + uwr.downloadHandler.text);
            if (uwr.downloadHandler.text == Server.fail_auth)
            {
                //error
            }
            else
            {
                UpdateSessions.JSON_Session("player",uwr.downloadHandler.text);
                // create the model here somehow 
                NPBinding.UI.ShowToast("Ideal Personality Submitted. Player created.", eToastMessageLength.SHORT);
                gameObject.AddComponent<ChangeScene>().Forward("ModelCreated");
            }
        }
        StopCoroutine(PutIdeals(json));
    }

    //! Create JSON to pass to server
    public void Submit () {                                             
        string json = JsonUtility.ToJson(new Ideals(UserSession.us.user.GetID(), trait1, trait2, trait3, trait4, trait5));
        Debug.Log("Ideal Personality: " + json);
        StartCoroutine(PutIdeals(json));
    }
}
