using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

//! PvE screen and persona management
public class PersonaSelection : MonoBehaviour
{

    public GameObject selected1, selected2, selected3;
    private int persona;

    private void Start()
    {
        persona = 1;
    }

    private void Update()
    {
        switch (persona)
        {
            case 1:
                selected1.SetActive(true);
                selected2.SetActive(false);
                selected3.SetActive(false);
                break;
            case 2:
                persona = 2;
                selected1.SetActive(false);
                selected2.SetActive(true);
                selected3.SetActive(false);
                break;
            case 3:
                selected1.SetActive(false);
                selected2.SetActive(false);
                selected3.SetActive(true);
                break;
        }
    }

    //! Update the persona to the selected one.
    public void ChangePersona(int given_persona)
    {
        persona = given_persona;
    }

    //! PUT persona into server for comparisons
    private IEnumerator PutPersona(string json)
    {
        yield break;
    }

    //! Pass selected persona into Server, go to ModelCreated scene
    public void PassPersona()
    {
        StartCoroutine(PutPersona("JSON of persona traits? idk "));
        gameObject.AddComponent<ChangeScene>().Forward("ModelCreated");
    }
}
