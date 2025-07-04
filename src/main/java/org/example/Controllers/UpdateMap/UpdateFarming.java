package org.example.Controllers.UpdateMap;

import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.Tile;

import java.util.ArrayList;

public class UpdateFarming {
    public static void updateAllCrops() {
        Game game = App.getCurrentGame();
        Tile[][] tiles = game.getGameMap().getMap();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile tile = tiles[i][j];
                ItemInstance item = tile.getItem();
                if (item.getDefinition().getType() == ItemType.all_crops) {
                    tile.setDayPassedFromPlant(tile.getDayPassedFromPlant() + 1);
                    ArrayList<Integer> stages = (ArrayList<Integer>) item.getAttribute(ItemAttributes.stages);
                    if (stages == null) {
                        return;
                    }
                    for (int k = 0; k < stages.size(); k++) {
                        if (stages.get(k) != 0) {
                            stages.set(k, stages.get(k) - 1);
                        }
                    }
                    item.setAttribute(ItemAttributes.stages, stages);
                }
            }
        }
    }
}
