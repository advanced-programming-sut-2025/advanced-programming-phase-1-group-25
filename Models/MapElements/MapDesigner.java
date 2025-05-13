package org.example.Models.MapElements;

import edu.stanford.nlp.parser.lexparser.Item;
import org.example.Enums.ItemConsts.ItemIDs;
import org.example.Enums.MapConsts.AnsiColors;
import org.example.Enums.MapConsts.FarmElementsPosition;
import org.example.Enums.MapConsts.MapSizes;
import org.example.Models.App;
import org.example.Models.Item.ItemInstance;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class MapDesigner {
    public static void designTopLeft(GameMap map) {
        map.getTile(FarmElementsPosition.TopLeftFarm.COTTAGE.getY(),
                FarmElementsPosition.TopLeftFarm.COTTAGE.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("cottage"))));
        map.getTile(FarmElementsPosition.TopLeftFarm.GREENHOUSE.getY(),
                FarmElementsPosition.TopLeftFarm.GREENHOUSE.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("greenhouse"))));
        map.getTile(FarmElementsPosition.TopLeftFarm.QUARRY.getY(),
                FarmElementsPosition.TopLeftFarm.QUARRY.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(FarmElementsPosition.TopLeftFarm.LAKE_1.getY(),
                FarmElementsPosition.TopLeftFarm.LAKE_1.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
        map.getTile(FarmElementsPosition.TopLeftFarm.LAKE_2.getY(),
                FarmElementsPosition.TopLeftFarm.LAKE_2.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
    }
    public static void designTopRight(GameMap map) {
        map.getTile(FarmElementsPosition.TopRightFarm.COTTAGE.getY(),
                FarmElementsPosition.TopRightFarm.COTTAGE.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("cottage"))));
        map.getTile(FarmElementsPosition.TopRightFarm.GREENHOUSE.getY(),
                FarmElementsPosition.TopRightFarm.GREENHOUSE.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("greenhouse"))));
        map.getTile(FarmElementsPosition.TopRightFarm.QUARRY_1.getY(),
                FarmElementsPosition.TopRightFarm.QUARRY_1.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(FarmElementsPosition.TopRightFarm.QUARRY_2.getY(),
                FarmElementsPosition.TopRightFarm.QUARRY_2.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(FarmElementsPosition.TopRightFarm.LAKE.getY(),
                FarmElementsPosition.TopRightFarm.LAKE.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
    }
    public static void designBottomLeft(GameMap map) {
        map.getTile(FarmElementsPosition.BottomLeftFarm.COTTAGE.getY(),
                FarmElementsPosition.BottomLeftFarm.COTTAGE.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("cottage"))));
        map.getTile(FarmElementsPosition.BottomLeftFarm.GREENHOUSE.getY(),
                FarmElementsPosition.BottomLeftFarm.GREENHOUSE.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("greenhouse"))));
        map.getTile(FarmElementsPosition.BottomLeftFarm.QUARRY_1.getY(),
                FarmElementsPosition.BottomLeftFarm.QUARRY_1.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(FarmElementsPosition.BottomLeftFarm.QUARRY_2.getY(),
                FarmElementsPosition.BottomLeftFarm.QUARRY_2.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(FarmElementsPosition.BottomLeftFarm.LAKE.getY(),
                FarmElementsPosition.BottomLeftFarm.LAKE.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
    }
    public static void designBottomRight(GameMap map) {
        map.getTile(FarmElementsPosition.BottomRightFarm.COTTAGE.getY(),
                FarmElementsPosition.BottomRightFarm.COTTAGE.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("cottage"))));
        map.getTile(FarmElementsPosition.BottomRightFarm.GREENHOUSE.getY(),
                FarmElementsPosition.BottomRightFarm.GREENHOUSE.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("greenhouse"))));
        map.getTile(FarmElementsPosition.BottomRightFarm.QUARRY.getY(),
                FarmElementsPosition.BottomRightFarm.QUARRY.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("quarry"))));
        map.getTile(FarmElementsPosition.BottomRightFarm.LAKE_1.getY(),
                FarmElementsPosition.BottomRightFarm.LAKE_1.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));
        map.getTile(FarmElementsPosition.BottomRightFarm.LAKE_2.getY(),
                FarmElementsPosition.BottomRightFarm.LAKE_2.getX()).setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("lake"))));

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
                        if (tile.getItem().getDefinition().getId() == ItemIDs.lake) {
                            tile.setBackGroundColor(AnsiColors.BLUE);
                        }
                        if (tile.getItem().getDefinition().getId() == ItemIDs.cottage) {
                            tile.setBackGroundColor(AnsiColors.BLACK);
                            tile.setForGroundColor(AnsiColors.WHITE);
                        }
                        if (tile.getItem().getDefinition().getId() == ItemIDs.quarry) {
                            tile.setBackGroundColor(AnsiColors.BLACK);
                            tile.setForGroundColor(AnsiColors.WHITE);
                        }
                        if (tile.getItem().getDefinition().getId() == ItemIDs.greenhouse){
                            tile.setBackGroundColor(AnsiColors.RED);
                        }
                    }
                }
            }
        }

        map.getTile(30, 59).setBackGroundColor(AnsiColors.BLACK);
    }
}
