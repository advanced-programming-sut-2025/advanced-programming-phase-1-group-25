package org.example.Controllers.UpdateMap;

import org.example.Controllers.InGameMenuController.GenerateRandomNumber;
import org.example.Enums.InGameMenuCommands.PlayerCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;

public class RandomEvents {
    public static void strikeLightning() {
        Game currentGame = App.getCurrentGame();
        PlayerMap currentPlayerMap = currentGame.getPlayerMap(currentGame.getCurrentPlayer());
        Tile[][] tiles = currentPlayerMap.getMap();
        int[] tilesY = new int[3];
        int[] tilesX = new int[3];
        for (int i = 0; i < 3; i++) {
            tilesY[i] = GenerateRandomNumber.generateRandomNumber(0, 30);
            tilesX[i] = GenerateRandomNumber.generateRandomNumber(0, 30);
        }
        Tile[] tilesToStrike = new Tile[3];
        for (int i = 0; i < 3; i++) {
            tilesToStrike[i] = currentPlayerMap.getTile(tilesY[i], tilesX[i]);
        }

        for (Tile tile : tilesToStrike) {
            tile.strikeLightning();
        }

    }
}
