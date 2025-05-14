package org.example.Models.NPC;

import org.example.Models.Item.ItemDefinition;

import java.util.Map;

/*
    In a relation, we can have quests to do.
 */
public class Quest {
    private NPC npc;
    private ItemDefinition request;
    private int requestAmount;
    private ItemDefinition reward;
    private int rewardAmount;


    public Quest(ItemDefinition request, int requestAmount
                ,ItemDefinition reward, int rewardAmount) {
        this.request = request;
        this.requestAmount = requestAmount;
        this.reward = reward;
        this.rewardAmount = rewardAmount;
        this.npc = npc;
    }

    public int getRequestAmount() {
        return requestAmount;
    }

    public ItemDefinition getRequest() {
        return request;
    }

    public int getRewardAmount() {
        return rewardAmount;
    }

    public ItemDefinition getReward() {
        return reward;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public NPC getNpc() {
        return npc;
    }
}

