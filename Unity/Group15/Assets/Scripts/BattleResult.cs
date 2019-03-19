using UnityEngine;
using System;

[Serializable]
//! JSON-able object to pass result of battle to server
public class BattleResult {

    //! Player ID
    private readonly string id1;
    //! Enemy ID
    private readonly string id2;
    //! True = Player win | False = Enemy win
    private readonly bool result;
    //! Extra EXP gained/lost by defeating higher/lower level enemy
    public int additionalExp;
    //! Extra money gained/lost by defeating higher/lower level enemy
    public int additionalMoney;

    //! Constructor
    private BattleResult(Player p1, Player p2, bool win)
    {
        id1 = p1.id; id2 = p2.id; result = win;
        additionalExp = CalculateBonusExp(p1,p2);
        additionalMoney = CalculateBonusMoney(p1,p2);
    }

    //! Calculates bonus EXP based on level comparison
    private int CalculateBonusExp(Player p1, Player p2)
    {
        if (p1.level > p2.level) return -10;
        else return 10;
    }

    //! Calculates bonus money based on level comparison
    private int CalculateBonusMoney(Player p1, Player p2)
    {
        if (p1.level > p2.level) return -10;
        else return 10;
    }

    //! Get a JSON equivalent of this object
    public static string GetJSON(Player p1, Player p2, bool win)
    {
        return JsonUtility.ToJson(new BattleResult(p1, p2, win));
    }


}
