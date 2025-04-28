package org.example.Controllers.UpdateMap;

import org.example.Controllers.InGameMenuController.GenerateRandomNumber;
import org.example.Enums.MapConsts.MapSizes;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Tile;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Map;

public class spawnRandom {

    public static void spawnRandomElements(ArrayList<PlayerMap> tmp) {
        spawn(100, 200, "wood", tmp);
        spawn(100, 200, "rock", tmp);
        spawn(100, 200, "fiber", tmp);
        spawn(50, 100, "tree", tmp);

    }
    public static void spawn(int min, int max, String id, ArrayList<PlayerMap> tmp) {
        Game currentGame = App.getCurrentGame();
        ArrayList<PlayerMap> playerMaps = currentGame.getPlayerMaps();
        for (PlayerMap playerMap : tmp) {
            int totalNumber = GenerateRandomNumber.generateRandomNumber(min, max);
            for (int i = 0; i < totalNumber; i++) {
                Tile tile;
                do {
                    int x = GenerateRandomNumber.generateRandomNumber(0, MapSizes.FARM_ROWS.getSize() - 1);
                    int y = GenerateRandomNumber.generateRandomNumber(0, MapSizes.MAP_COLS.getSize() - 1);
                    tile = playerMap.getTile(y, x);
                } while (tile.isEmpty());
                tile.setItem(new ItemInstance(App.getItemDefinition(id)));
            }
        }
    }
}
