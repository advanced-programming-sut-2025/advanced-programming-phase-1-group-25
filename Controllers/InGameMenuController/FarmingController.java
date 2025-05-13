package org.example.Controllers.InGameMenuController;

import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemDisplay;
import org.example.Enums.ItemConsts.ItemIDs;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Enums.MapConsts.MapSizes;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Position;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;
import org.example.Views.InGameMenus.ActionMenuView;
import org.example.Views.InGameMenus.FarmingView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FarmingController {
    ActionMenuView view;

    public FarmingController(ActionMenuView view) {
        this.view = view;
    }

    public void plow(String dir) {
        Tile tile = getTileByDir(dir);
        if (tile == null) {
            this.view.showMessage("Tile doesn't exist");
            return;
        }
        plowTile(tile);
    }


    private void plowTile(Tile tile) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        PlayerMap currentPlayerMap = App.getCurrentGame().getPlayerMap(currentPlayer);

        if (!currentPlayerMap.hasTile(tile)) {
            this.view.showMessage("You can't plow this tile!");
            return;
        }

        if (!tile.isEmpty()) {
            this.view.showMessage("Tile is not empty.");
            return;
        }

        tile.setPlowed(true);
        // decrease energy for plowing!
        this.view.showMessage("Tile plowed!");
    }

    public void plant(String seed, String dir, Game game) {
        if (!game.isPlayerActive(game.getCurrentPlayer())) {
            view.showMessage("You are ran out of energy for this turn!");
            return;
        }
        Tile tile = getTileByDir(dir);
        if (tile == null) {
            this.view.showMessage("Tile doesn't exist.");
            return;
        }
        plantSeedInTile(seed, tile);
    }

    private void plantSeedInTile(String seedName, Tile tile) {
        Game currentGame = App.getCurrentGame();
        PlayerMap currentPlayerMap = currentGame.getPlayerMap(App.getCurrentGame().getCurrentPlayer());

        if (!currentPlayerMap.hasTile(tile)) {
            this.view.showMessage("You can't plant in this tile!");
            return;
        }

        if (!tile.isEmpty()) {
            this.view.showMessage("Tile is not empty.");
            return;
        }

        if (!tile.isPlowed()) {
            this.view.showMessage("Tile is not plowed.");
            return;
        }

        // find seed
        ItemDefinition seedDefinition = getSeedByName(seedName);
        if (seedDefinition == null) {
            this.view.showMessage("Seed not found!");
            return;
        }

        Player currentPlayer = currentGame.getCurrentPlayer();
        if (!currentPlayer.getInventory().hasItem(seedDefinition.getId())) {
            this.view.showMessage("You don't have this seed.");
            return;
        }

        ItemInstance seed = currentPlayer.getInventory().getItem(seedDefinition.getId());
        tile.setItem(seed);
        tile.setDayPassedFromPlant(0);
        this.view.showMessage("You have planted the seed successfully.");
        // decrease energy TODO
    }

    private Tile getTileByDir(String dir) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        int tileY, tileX;
        switch (dir.toLowerCase()) {
            case "up":
                tileY = currentPlayer.getPosition().getY() - 1;
                tileX = currentPlayer.getPosition().getX();
                break;
            case "down":
                tileY = currentPlayer.getPosition().getY() + 1;
                tileX = currentPlayer.getPosition().getX();
                break;
            case "left":
                tileY = currentPlayer.getPosition().getY();
                tileX = currentPlayer.getPosition().getX() - 1;
                break;
            case "right":
                tileY = currentPlayer.getPosition().getY();
                tileX = currentPlayer.getPosition().getX() + 1;
                break;
            case "up right":
                tileY = currentPlayer.getPosition().getY() - 1;
                tileX = currentPlayer.getPosition().getX() + 1;
                break;
            case "up left":
                tileY = currentPlayer.getPosition().getY() - 1;
                tileX = currentPlayer.getPosition().getX() - 1;
                break;
            case "down right":
                tileY = currentPlayer.getPosition().getY() + 1;
                tileX = currentPlayer.getPosition().getX() + 1;
                break;
            case "down left":
                tileY = currentPlayer.getPosition().getY() + 1;
                tileX = currentPlayer.getPosition().getX() - 1;
                break;
            default:
                this.view.showMessage("Please enter a valid direction: up, down, left and right");
                return null;
        }

        Tile tile;
        try {
            tile = App.getCurrentGame().getGameMap().getTile(tileY, tileX);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return tile;
    }

    public void showPlant(String yStr, String xStr, Game game) {
        if (!game.isPlayerActive(game.getCurrentPlayer())) {
            view.showMessage("You are ran out of energy for this turn!");
            return;
        }
        int y, x;
        try {
            y = Integer.parseInt(yStr);
            x = Integer.parseInt(xStr);
        } catch (NumberFormatException e) {
            this.view.showMessage("Please enter a valid position.");
            return;
        }

        GameMap map = App.getCurrentGame().getGameMap();
        Tile tile = map.getTile(y, x);
        if (tile == null) {
            this.view.showMessage("Tile doesn't exist");
            return;
        }

        ItemInstance item = tile.getItem();
        boolean isSeed = false;
        String name;
        String isWateredToday;
        String isFertilized;
        int timeRemains = -1;

        if (item.getDefinition().getType() == ItemType.foraging_seeds) {
            isSeed = true;
            ItemDefinition plant = getPlantBySeed(item.getDefinition());
            if (plant == null) {
                this.view.showMessage("Error occurred (plant for this seed doesn't exist)");
                return;
            }
            name = item.getDefinition().getDisplayName();
            try {
                timeRemains = ((int) plant.getAttribute(ItemAttributes.totalHarvestTime)) - tile.getDayPassedFromPlant();
            } catch (Exception e) {
                this.view.showMessage("Error occurred (not have totalHarvestTime)");
                return;
            }

            isWateredToday = tile.isWatered() ? "is watered today" : "is not watered today";
            isFertilized = tile.isFertilized() ? "is fertilized" : "is not fertilized";
            //TODO: stage and quality?!
        } else if (item.getDefinition().getType() == ItemType.foraging_crops) {
            name = item.getDefinition().getDisplayName();
            isWateredToday = tile.isWatered() ? "is watered today" : "is not watered today";
            isFertilized = tile.isFertilized() ? "is fertilized" : "is not fertilized";
            //TODO: stage and quality?!
        } else {
            this.view.showMessage("Tile doesn't have any plant!");
            return;
        }

        StringBuilder output = new StringBuilder();
        output.append("Tile plant description:\n");
        output.append("Plant/seed name: ").append(name).append("\n");
        output.append(isWateredToday).append("\n").append(isFertilized).append("\n");
        if (isSeed) output.append("Time remains ").append(timeRemains);
        this.view.showMessage(output.toString());
    }

    public void fertilize(String fertilizer, String dir, Game game) {
        if (!game.isPlayerActive(game.getCurrentPlayer())) {
            view.showMessage("You are ran out of energy for this turn!");
            return;
        }
        Tile tile = getTileByDir(dir);
        if (tile == null) {
            this.view.showMessage("Tile doesn't exist.");
            return;
        }
        // TODO: check that the fertilizer exits
        fertilizeTileWithFertilizer(tile, fertilizer);
    }

    private void fertilizeTileWithFertilizer(Tile tile, String fertilizer) {

        // TODO: fertilize with fertilizer
    }

    public void reap(String dir) {
        Tile tile = getTileByDir(dir);
        if (tile == null) {
            this.view.showMessage("Tile doesn't exist.");
            return;
        }
        reapTile(tile);
    }

    private void reapTile(Tile tile) {
        ItemInstance item = tile.getItem();
        if (item.getDefinition().getType() != ItemType.all_crops) {
            this.view.showMessage("There is no crop here.");
            return;
        }

        // decrease energy
        tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        currentPlayer.getInventory().addItem(item);
        this.view.showMessage("Plant harvested successfully.");
    }

    private ItemDefinition getSeedByName(String name) {
        for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
            if (itemDefinition.getType() == ItemType.foraging_seeds) {
                if (name.equalsIgnoreCase(itemDefinition.getDisplayName())) {
                    return itemDefinition;
                }
            }
        }
        return null;
    }

    private ItemDefinition getPlantBySeed(ItemDefinition seed) {
        for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
            if((itemDefinition.getType() == ItemType.all_crops) &&
               ((String)itemDefinition.getAttribute(ItemAttributes.source)).equalsIgnoreCase(seed.getId().name())) {
                return itemDefinition;
            }
        }
        return null;
    }
    public void howMuchWater(){
        Game game = App.getCurrentGame();
        if (!game.isPlayerActive(game.getCurrentPlayer())) {
            view.showMessage("You are ran out of energy for this turn!");
            return;
        }
        Player player = game.getCurrentPlayer();
        Inventory inventory = player.getInventory();
        for(Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : inventory.getItems().entrySet()){
            ItemIDs itemID = entry.getKey();
            ArrayList<ItemInstance> items = entry.getValue();
            for (ItemInstance item : items) {
                if(item.getDefinition().getId().name().contains("watering_can")){
                    int water = (int) item.getAttribute(ItemAttributes.durability);
                    view.showMessage("You have " + water + " in your watering can");
                    return;
                }
            }
        }
    }
}
