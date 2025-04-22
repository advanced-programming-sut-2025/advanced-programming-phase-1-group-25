package org.example.Models.NPC;

/*
    A class to handle relations between a player and an NPC.
 */

import org.example.Models.Player.Player;

public class Relation {
    private Player player;
    private NPC npc;
    private int friendShipLevel;
    private int friendShipPoints;
    private boolean areMarried; // are two sides of this relation married?
    private Quest activeQuest;

    public Relation(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
        this.friendShipLevel = 0;
        this.friendShipPoints = 0;
        this.areMarried = false;
        this.activeQuest = null;
    }

    public Player getPlayer() {
        return player;
    }

    public NPC getNpc() {
        return npc;
    }

    public int getFriendShipLevel() {
        return friendShipLevel;
    }

    public void setFriendShipLevel(int friendShipLevel) {
        this.friendShipLevel = friendShipLevel;
    }

    public int getFriendShipPoints() {
        return friendShipPoints;
    }

    public void setFriendShipPoints(int friendShipPoints) {
        this.friendShipPoints = friendShipPoints;
    }

    public Quest getActiveQuest() {
        return activeQuest;
    }

    public void setActiveQuest(Quest activeQuest) {
        this.activeQuest = activeQuest;
    }

    public boolean areMarried() {
        return areMarried();
    }

    public void setAreMarried(boolean areMarried) {
        this.areMarried = areMarried;
    }
}
