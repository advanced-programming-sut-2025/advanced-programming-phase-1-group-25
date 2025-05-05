package org.example.Controllers.UpdateMap;

import org.example.Controllers.InGameMenuController.GenerateRandomNumber;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Enums.MapConsts.AnsiColors;
import org.example.Enums.MapConsts.MapSizes;
import org.example.Models.App;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.PlayerAbilities;

import java.util.ArrayList;
import java.util.List;

/*
    Logins to update the foraging items of the map every morning.
 */
public class UpdateForaging {
    public static void updateForaging() {
        List<ItemDefinition> foragingMinerals = new ArrayList<>();
        List<ItemDefinition> foragingCrops = new ArrayList<>();
        List<ItemDefinition> foragingSeeds = new ArrayList<>();
        for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
            if (itemDefinition.getType() == ItemType.foraging_minerals) {
                foragingMinerals.add(itemDefinition);
            } else if (itemDefinition.getType() == ItemType.foraging_crops) {
                foragingCrops.add(itemDefinition);
            } else if (itemDefinition.getType() == ItemType.foraging_seeds) {
                foragingSeeds.add(itemDefinition);
            }
        }

        spawnRandomForaging(foragingCrops);
        spawnRandomForaging(foragingMinerals);
        spawnRandomForaging(foragingSeeds);
    }
    public static void spawnRandomForaging(List<ItemDefinition> foragingDefinitions) {
        List<PlayerMap> playerMaps = App.getCurrentGame().getPlayerMaps();
        for (PlayerMap playerMap : playerMaps) {
            Tile[][] tiles = playerMap.getMap();
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    Tile tile = tiles[i][j];
                    if (tile.isEmpty()) {
                        int randomNumber = GenerateRandomNumber.generateRandomNumber(0, 99);
                        if (randomNumber == 1) {
                            int itemDefinitionNumber = GenerateRandomNumber.generateRandomNumber(0, foragingDefinitions.size() - 1);
                            ItemDefinition itemDefinition = foragingDefinitions.get(itemDefinitionNumber);
                            tile.setItem(new ItemInstance(itemDefinition));
                            tile.setForGroundColor(AnsiColors.GREEN);
                        }
                    }
                }
            }
        }
    }

    public static void deleteForaging() {
        List<PlayerMap> playerMaps = App.getCurrentGame().getPlayerMaps();
        for (PlayerMap playerMap : playerMaps) {
            Tile[][] tiles = playerMap.getMap();
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    Tile tile = tiles[i][j];
                    ItemType type = tile.getItem().getDefinition().getType();
                    if (type == ItemType.foraging_seeds
                            || type == ItemType.foraging_crops
                            || type == ItemType.foraging_minerals) {
                        tile.setItem(new ItemInstance(App.getItemDefinition("VOID")));
                    }

                }
            }
        }
    }
}
