package org.example.Models;

import org.example.Enums.MapSizes;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.Position;
import org.example.Models.MapElements.Tile;

import java.util.Objects;

public class PrepareMap {
    public static GameMap prepareMap() {
        GameMap map = new GameMap();
        for (int y = 0; y < MapSizes.MAP_ROWS.getSize(); y++) {
            for (int x = 0; x < MapSizes.MAP_COLS.getSize(); x++) {
                Tile newTile = new Tile(new Position(y, x), new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
                map.addTile(newTile);
            }
        }

        // designing the map!
        map.getTile(10, 20).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("home"))));
        map.getTile(10, 10).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("greenhouse"))));
        map.getTile(0, 15).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(20, 20).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
        map.getTile(25, 15).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));

        return map;
    }
}
