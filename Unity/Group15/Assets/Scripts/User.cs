using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class User{

    public string id;
    public string username;
    public string password;
    public string accessToken;
    public string accessTokenSecret;

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

    public string GetUsername()
    {
        return username;
    }

    public string GetPassword()
    {
        return password;
    }

    public string GetID()
    {
        return id;
    }

    public string GetAT()
    {
        return accessToken;
    }

    public string GetATS()
    {
        return accessTokenSecret;
    }
}
