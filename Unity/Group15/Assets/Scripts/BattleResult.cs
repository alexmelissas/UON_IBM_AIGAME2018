using UnityEngine;
using System;

[Serializable]
public class BattleResult {

    private readonly string id1;
    private readonly string id2;
    private readonly bool result;
    public int additionalExp;
    public int additionalMoney;

    private BattleResult(Player p1, Player p2, bool win)
    {
        id1 = p1.id; id2 = p2.id; result = win;
        additionalExp = CalculateBonusExp(p1,p2);
        additionalMoney = CalculateBonusMoney(p1,p2);
    }

    private int CalculateBonusExp(Player p1, Player p2)
    {
        if (p1.level > p2.level) return -10;
        else return 10;
    }

    private int CalculateBonusMoney(Player p1, Player p2)
    {
        if (p1.level > p2.level) return -10;
        else return 10;
    }

    public static string GetJSON(Player p1, Player p2, bool win)
    {
        return JsonUtility.ToJson(new BattleResult(p1, p2, win));
    }


}
