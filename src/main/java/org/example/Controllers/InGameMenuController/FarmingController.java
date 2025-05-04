package org.example.Controllers.InGameMenuController;

import org.example.Enums.ItemConsts.ItemType;
import org.example.Enums.MapConsts.MapSizes;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Position;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;
import org.example.Views.InGameMenus.FarmingView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FarmingController {
    FarmingView view;
    public FarmingController(FarmingView view) {
        this.view = view;
    }
    public void plow(String dir) {
        Position tilePos = getPosByDir(dir);
        if (tilePos == null) return;
        plowTile(tilePos.getY(), tilePos.getX());
    }


    private void plowTile(int y, int x) {
        Game currentGame = App.getCurrentGame();
        PlayerMap currentPlayerMap = currentGame.getPlayerMap(App.getCurrentGame().getCurrentPlayer());
        if (y < 0 || y >= MapSizes.MAP_ROWS.getSize()
            || x < 0 || x >= MapSizes.MAP_COLS.getSize()) {
            this.view.showMessage("Tile doesn't exist!");
            return;
        }

        Tile tile = currentGame.getGameMap().getTile(y, x);
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

    public void plant(String seed, String dir) {
        Position tilePos = getPosByDir(dir);
        if (tilePos == null) return;
        plantSeedInTile(seed, tilePos.getY(), tilePos.getX());
    }

    private void plantSeedInTile(String seedName, int y, int x) {
        Game currentGame = App.getCurrentGame();
        PlayerMap currentPlayerMap = currentGame.getPlayerMap(App.getCurrentGame().getCurrentPlayer());

        if (y < 0 || y >= MapSizes.MAP_ROWS.getSize()
                || x < 0 || x >= MapSizes.MAP_COLS.getSize()) {
            this.view.showMessage("Tile doesn't exist!");
            return;
        }

        Tile tile = currentGame.getGameMap().getTile(y, x);
        if (!currentPlayerMap.hasTile(tile)) {
            this.view.showMessage("You can't plant in this tile!");
            return;
        }

        if (!tile.isEmpty()) {
            this.view.showMessage("Tile is not empty.");
            return;
        }

        // find seed
        ItemDefinition seedDefinition = getSeedByName(seedName);
        if (seedDefinition == null) {
            this.view.showMessage("Seed not found!");
            return;
        }

        Player currentPlayer = currentGame.getCurrentPlayer();
        if (!currentPlayer.getInventory().hasItem(seedDefinition.getId().name())) {
            this.view.showMessage("You don't have this seed.");
            return;
        }

        // plant seed
//        ItemInstance seed = currentPlayer.getInventory().getItem(seedDefinition.getId().name(), 1);
//        tile.plantSeed(seed);
        // decrease energy
    }

    private Position getPosByDir(String dir) {
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
            default:
                this.view.showMessage("Please enter a valid direction: up, down, left and right");
                return null;
        }

        return new Position(tileY, tileX);

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
}
