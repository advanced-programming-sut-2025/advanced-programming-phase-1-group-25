package org.example.Models.MapElements;

import org.example.Enums.MapConsts.AnsiColors;
import org.example.Enums.MapConsts.MapSizes;
import org.example.Models.App;
import org.example.Models.Item.ItemInstance;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class MapDesigner {
    public static void designTopLeft(GameMap map) {
        map.getTile(5, 25).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("cottage"))));
        map.getTile(5, 10).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("greenhouse"))));
        map.getTile(0, 15).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(20, 20).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
        map.getTile(25, 15).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
    }
    public static void designTopRight(GameMap map) {
        map.getTile(5, 85).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("cottage"))));
        map.getTile(5, 70).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("greenhouse"))));
        map.getTile(0, 75).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(29, 65).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(20, 80).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
    }
    public static void designBottomLeft(GameMap map) {
        map.getTile(65, 25).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("cottage"))));
        map.getTile(65, 10).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("greenhouse"))));
        map.getTile(60, 15).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(89, 5).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(80, 15).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
    }
    public static void designBottomRight(GameMap map) {
        map.getTile(65, 85).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("cottage"))));
        map.getTile(65, 70).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("greenhouse"))));
        map.getTile(60, 75).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(80, 80).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
        map.getTile(85, 75).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));

    }

    public static void addColor(GameMap map) {
        int[] yStarts = {0, 60};
        int[] xStarts = {0, 60};

        for (Integer yStart : yStarts) {
            for (Integer xStart : xStarts) {
                for (int y = yStart; y < yStart + MapSizes.FARM_ROWS.getSize(); y++) {
                    for (int x = xStart; x < xStart + MapSizes.FARM_COLS.getSize(); x++) {
                        Tile tile = map.getTile(y, x);
                        tile.setForGroundColor(AnsiColors.BLACK);
                        tile.setBackGroundColor(AnsiColors.GREEN);
                    }
                }
            }
        }
    }
}
