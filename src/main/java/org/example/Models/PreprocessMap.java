package org.example.Models;

import org.example.Enums.MapSizes;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.Position;
import org.example.Models.MapElements.Tile;

public class PreprocessMap {
    private static void PrepareMap() {
        GameMap map = new GameMap();
        for (int y = 0; y < MapSizes.MAP_ROWS.getSize(); y++) {
            for (int x = 0; x < MapSizes.MAP_COLS.getSize(); x++) {
                Tile newTile = new Tile(new Position(y, x), null);
                map.addTile(newTile);
            }
        }
    }
}
