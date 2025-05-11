package org.example.Models.Player;

import org.example.Enums.GameConsts.Gender;
import org.example.Enums.GameConsts.WeatherStates;
import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Models.Animals.Animal;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Position;
import org.example.Models.MapElements.Tile;
import org.example.Models.User;

import java.util.ArrayList;
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
    private ItemInstance trashCan;
    private int energyPerTurn;
    private PlayerMap playerMap;
    private ArrayList<Animal> animals;

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
        this.trashCan = new ItemInstance(Objects.requireNonNull(App.getItemDefinition("base_trash_can")));
        animals = new ArrayList<>();
        setInventoryTools();
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
        ArrayList<ItemInstance> items = new ArrayList<>();
        items.add(newTool);
        this.inventory.getItems().put(newTool.getDefinition().getId(), items);
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

    public void increaseCoin(int coin) {
        this.coin += coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public ItemInstance getTrashCan() {
        return trashCan;
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

    public void setPosition(Position position) {
        this.position = position;
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

    public void reduceEnergy(int ability, ItemInstance tool, Player player, boolean canBeDownGraded, Game game) {
        double rate = 1;
        if (game.getWeather().getCurrentWeather().equals(WeatherStates.SNOW)) rate = 2;
        if (game.getWeather().getCurrentWeather().equals(WeatherStates.RAIN)) rate = 1.5;
        if (ability == 4) {
            this.energy -= (int) (rate * ((int) tool.getDefinition().getAttribute(ItemAttributes.energyCost) - 1));
        } else {
            this.energy -= (int) (rate * (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost));
        }
        if (canBeDownGraded) {
            int x = tool.decreaseDurability();
            if (x == 0)
                player.changeToolLevel(tool);
        }
    }

    public void setInventoryTools() {
        this.inventory.addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("base_hoe"))));
        this.inventory.addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("base_pickaxe"))));
        this.inventory.addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("base_axe"))));
        this.inventory.addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("base_watering_can"))));
        this.inventory.addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("training_fishing_pole"))));
        this.inventory.addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("scythe"))));
        this.inventory.addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("milk_pale"))));
        this.inventory.addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("shear"))));
    }

    public PlayerMap getPlayerMap() {
        return playerMap;
    }

    public void setPlayerMap(PlayerMap playerMap) {
        this.playerMap = playerMap;
    }
    public void setAnimal(Animal animal) {
        this.animals.add(animal);
    }
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}
