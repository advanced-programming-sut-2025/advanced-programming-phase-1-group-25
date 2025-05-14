package org.example.Controllers.UpdateMap;

import org.example.Controllers.InGameMenuController.GenerateRandomNumber;
import org.example.Enums.InGameMenuCommands.PlayerCommands;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class RandomEvents {
    public static void strikeLightning() {
        Game currentGame = App.getCurrentGame();
        PlayerMap currentPlayerMap = currentGame.getPlayerMap(currentGame.getCurrentPlayer());
        Tile[][] tiles = currentPlayerMap.getMap();
        int[] tilesY = new int[3];
        int[] tilesX = new int[3];
        for (int i = 0; i < 3; i++) {
            tilesY[i] = GenerateRandomNumber.generateRandomNumber(0, 29);
            tilesX[i] = GenerateRandomNumber.generateRandomNumber(0, 29);
        }
        Tile[] tilesToStrike = new Tile[3];
        for (int i = 0; i < 3; i++) {
            tilesToStrike[i] = currentPlayerMap.getTile(tilesY[i], tilesX[i]);
        }

        for (Tile tile : tilesToStrike) {
            tile.strikeLightning();
        }
    }

    public static void crowAttack() {

        int attackRandomNumber = GenerateRandomNumber.generateRandomNumber(0, 3);
        if (attackRandomNumber != 1) return;

        Game game = App.getCurrentGame();
        Map<Player, PlayerMap> playerMaps = game.getPlayerAndPlayerMaps();

        for (Map.Entry<Player, PlayerMap> entry : playerMaps.entrySet()) {
            Player player = entry.getKey();
            PlayerMap playerMap = entry.getValue();
            Tile[][] tiles = playerMap.getMap();
            int counter = 0;
            ArrayList<Tile> tilesToAttack = new ArrayList<>();
            for (Tile[] row : tiles) {
                for (Tile tile : row) {
                    if (tile.getItem().getDefinition().getType() == ItemType.all_crops
                            || tile.getItem().getDefinition().getType() == ItemType.tree) {
                        counter++;
                        tilesToAttack.add(tile);
                        if (counter == 16) {
                            // TODO: check scarecrow effect
                            int randomNumber = GenerateRandomNumber.generateRandomNumber(0, 15);
                            Tile attackedTile = tilesToAttack.get(randomNumber);
                            if (attackedTile.getItem().getDefinition().getType() == ItemType.all_crops) {
                                tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
                                tile.setDayPassedFromPlant(0);
                                ;
                            } else if (attackedTile.getItem().getDefinition().getType() == ItemType.tree) {
                                // TODO: it should not give fruits tomorrow
                            }
                            counter = 0;
                            tilesToAttack = new ArrayList<>();
                            String message = "Your farm has been attacked by crows.";
                            player.addMessage(message);
                        }
                    }
                }
            }
        }

    }
}
