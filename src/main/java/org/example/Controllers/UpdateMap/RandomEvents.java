package org.example.Controllers.UpdateMap;

import org.example.Controllers.InGameMenuController.GenerateRandomNumber;
import org.example.Enums.GameConsts.WeatherStates;
import org.example.Enums.InGameMenuCommands.PlayerCommands;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;

import java.util.Objects;

public class RandomEvents {
    public static void strikeLightning() {
        Game currentGame = App.getCurrentGame();
        if (currentGame.getWeather().getCurrentWeather().equals(WeatherStates.STORM)) {
            for (Player player : currentGame.getPlayers()) {
                Tile[][] tiles = player.getPlayerMap().getMap();
                int[] tilesY = new int[3];
                int[] tilesX = new int[3];
                for (int i = 0; i < 3; i++) {
                    tilesY[i] = GenerateRandomNumber.generateRandomNumber(0, 30);
                    tilesX[i] = GenerateRandomNumber.generateRandomNumber(0, 30);
                }
                Tile[] tilesToStrike = new Tile[3];
                for (int i = 0; i < 3; i++) {
                    tilesToStrike[i] = player.getPlayerMap().getTile(tilesY[i], tilesX[i]);
                }
                for (Tile tile : tilesToStrike) {
                    strikeTile(tile, player);
                }
            }
        }
    }

    public static void strikeTile(Tile tile, Player player) {
        if (tile.getItem().getDefinition().getType().equals(ItemType.all_crops)) {
            tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
            player.addMessage("Your tile(y : " + tile.getPosition().getY() +
                    ", x : " + tile.getPosition().getX() + ") has been struck by lightning and your plant is" +
                    "gone!");
        } else if (tile.getItem().getDefinition().getType().equals(ItemType.tree)
                || tile.getItem().getDefinition().getType().equals(ItemType.foraging_trees)) {
            tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("coal"))));
            player.addMessage("Your tile(y : " + tile.getPosition().getY() +
                    ", x : " + tile.getPosition().getX() + ") has been struck by lightning and your plant is" +
                    "gone!");
        }
    }
}
