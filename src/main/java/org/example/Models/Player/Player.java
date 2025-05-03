package org.example.Models.Player;

import org.example.Enums.GameConsts.Gender;
import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.Position;
import org.example.Models.MapElements.Tile;
import org.example.Models.User;

import java.util.Objects;

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
    private ItemInstance currentTool;
    private int energyPerTurn;

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
        this.energyPerTurn = 50;
    }

    public void changeToolLevel(ItemInstance tool) {
        String name = tool.getDefinition().getDisplayName().toLowerCase();
        if (name.contains("hoe")) {
            changeInventoryTool(tool, "base_hoe");
        } else if (name.contains("pickaxe")) {
            changeInventoryTool(tool, "base_pickaxe");
        } else if (name.contains("axe")) {
            changeInventoryTool(tool, "base_axe");
        } else if (name.contains("watering can")) {
            changeInventoryTool(tool, "base_watering_can");
        } else if (name.contains("fishing pole")) {
            changeInventoryTool(tool, "training_fishing_pole");
        }
    }

    public void changeInventoryTool(ItemInstance tool, String newToolName) {
        this.inventory.getItems().remove(tool);
        ItemInstance newTool = new ItemInstance(Objects.requireNonNull(App.getItemDefinition(newToolName)));
        this.inventory.getItems().put(newTool, 1);
        this.currentTool = newTool;
    }

    public ItemInstance getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(ItemInstance currentTool) {
        this.currentTool = currentTool;
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

    public void setCoin(int coin) {
        this.coin = coin;
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

    public Tile getPlayerTile(Game game) {
        GameMap gameMap = game.getGameMap();
        return gameMap.getTile(this.position.getY(), this.position.getX());
    }

    public void reduceEnergy(int ability, ItemInstance tool, Player player) {
        if (ability == 4) {
            this.energy -= ((int) tool.getDefinition().getAttribute(ItemAttributes.energyCost) - 1);
        } else {
            this.energy -= (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost);
        }
        int x = tool.getDefinition().decreaseDurability();
        if (x == 0)
            player.changeToolLevel(tool);
    }
}
