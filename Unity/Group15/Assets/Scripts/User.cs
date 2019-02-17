using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class User{

    public string id;// { get; set; }
    public string username;// { get; set; }
    public string password;// { get; set; }
    public object accessToken;// { get; set; }
    public object accessTokenSecret;// { get; set; }

    public User(string name, string pass)
    {
        username = name;
        password = pass;
    }

    public static User CreateUserFromJSON (string json)
    {
        User temp = new User(" ", " ");
        JsonUtility.FromJsonOverwrite(json,temp);
        return temp;
    }

    public string getUsername()
    {
        return username;
    }

    public string getPassword()
    {
        return password;
    }

    public string getID()
    {
        return id;
    }

    public object getAT()
    {
        return accessToken;
    }

    public object getATS()
    {
        return accessTokenSecret;
    }
}
