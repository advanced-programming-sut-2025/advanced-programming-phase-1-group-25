package org.example.Models.Player;

import org.example.Enums.GameConsts.Gender;
import org.example.Models.Item.Inventory;
import org.example.Models.MapElements.Position;
import org.example.Models.User;

/*
    When a game is made, it has players; and when users enter the game, they become players of that game.
 */
public class Player {
   private User user;
   private String name;
   private Gender gender;
   private int energy;
   private int energyLimit;
   private int coin;
   private Inventory inventory;
   private PlayerAbilities abilities;
   private Position position;

    public Player(User user, String name, Gender gender, Position position) {
        this.user = user;
        this.name = name;
        this.gender = gender;
        this.energy = 200; // initial energy
        this.energyLimit = 200;
        this.coin = 0; // initial coin
        this.inventory = new Inventory();
        this.abilities = new PlayerAbilities();
        this.position = position; // initial position
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getCoin() {
        return coin;
    }

    public int getEnergy() {
        return energy;
    }

    public PlayerAbilities getAbilities() {
        return abilities;
    }

    public Position getPosition() {
        return position;
    }

    public User getUser() {
        return user;
    }

    public int getEnergyLimit() {
        return energyLimit;
    }

    public void setEnergyLimit(int energyLimit) {
        this.energyLimit = energyLimit;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
