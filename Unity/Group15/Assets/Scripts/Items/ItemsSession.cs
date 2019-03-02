using UnityEngine;

public class ItemsSession : MonoBehaviour
{

    public static ItemsSession its;

    public Items items;

    void Awake()
    {
        if (its == null)
        {
            DontDestroyOnLoad(gameObject);
            its = this;
        }
        else if (its != this)
        {
            Destroy(gameObject);
        }
    }
}
