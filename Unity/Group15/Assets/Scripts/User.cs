using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class User{
    
    public string uid { get; set; }
    public string username { get; set; }
    public string password { get; set; }
    public object accessToken { get; set; }
    public object accessTokenSecret { get; set; }

    public User(string name, string pass)
    {
        this.username = name;
        this.password = pass;
    }
}
