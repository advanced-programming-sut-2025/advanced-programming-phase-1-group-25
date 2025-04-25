package org.example.Models.MapElements;

import org.example.Enums.MapConsts.MapSizes;
import org.example.Models.App;
import org.example.Models.Item.ItemInstance;

import java.util.Map;
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

        // add cottage, lake, quarry and ... to the map!
        MapDesigner.designTopLeft(map);
        MapDesigner.designTopRight(map);
        MapDesigner.designBottomLeft(map);
        MapDesigner.designBottomRight(map);

        // add color to the map!
        MapDesigner.addColor(map);

        return map;
    }
}
