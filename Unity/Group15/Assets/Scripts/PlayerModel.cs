using UnityEngine;
using UnityEngine.UI;

public class PlayerModel : MonoBehaviour{
    
    //Contains animations

    public GameObject base_model; //basic model for all players
    private Items items; // add items to model

    public PlayerModel(Items i)
    {
        //return base model + holding items;
    }

    /* ======= Animations ========= */

    public void Strike()
    {
        //base_model.PlayAnimation(strike_animation);
    }

    public void HitBlockless()
    {
        //base_model.PlayAnimation(hit_blockless_animation);
    }

    public void HitBlock()
    {
        //base_model.PlayAnimation(hit_block_animation);
    }

    public void HitWillDie()
    {
        //base_model.PlayAnimation(hit_willdie_animation);
    }

    public void DieSink()
    {
        //base_model.PlayAnimation(die_sink_animation);
    }
}
