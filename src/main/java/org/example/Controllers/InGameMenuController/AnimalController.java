package org.example.Controllers.InGameMenuController;

import org.example.Enums.GameConsts.WeatherStates;
import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Models.Game;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;
import org.example.Views.InGameMenus.ActionMenuView;

import java.util.ArrayList;

public class AnimalController {
    static ActionMenuView view = new ActionMenuView();

    public static boolean isNearLake(Player player, Game game) {
        Tile playerTile = player.getPlayerTile(game);
        GameMap gameMap = game.getGameMap();
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        return gameMap.getTile(y, x - 1).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y, x + 1).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y + 1, x - 1).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y + 1, x).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y + 1, x + 1).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y - 1, x - 1).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y - 1, x).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y - 1, x + 1).getItem().getDefinition().getType().equals(ItemType.lake);
    }

    public static double getMBasedOnWeather(Game game) {
        WeatherStates weather = game.getWeather().getCurrentWeather();
        switch (weather) {
            case SUNNY -> {
                return 1.5;
            }
            case RAIN -> {
                return 1.2;
            }
            case STORM -> {
                return 0.5;
            }
            default -> {
                return 1;
            }
        }
    }

    public static int calculateQuality(int R, double M, int skill, double pole) {
        return (int) ((R * pole * (skill + 2)) / (7 - M));
    }

    public static void printFish(int quality, int number, ArrayList<ItemDefinition> caughtFish) {
        view.showMessage("Quality of fish : " + quality);
        view.showMessage("Number of fish : " + number);
        for (ItemDefinition fish : caughtFish) {
            view.showMessage("Fish name: " + fish.getDisplayName().toLowerCase() + " type: " + fish.getType().name());
        }
    }

    public static void putFishInInventory(Player player, Game game, ArrayList<ItemDefinition> caughtFish, int quality) {
        for (ItemDefinition fish : caughtFish) {
            ItemInstance item = new ItemInstance(fish);
            if (0.5 <= quality && quality < 0.7) {
                item.setAttribute(ItemAttributes.baseSellPrice,
                        (int) item.getAttribute(ItemAttributes.baseSellPrice) * 1.25);
                if (!player.getInventory().addItem(item)) {
                    view.showMessage("Your back pack is full!");
                } else {
                    player.getInventory().addItem(item);
                }
            } else if (0.7 <= quality && quality < 0.9) {
                item.setAttribute(ItemAttributes.baseSellPrice,
                        (int) item.getAttribute(ItemAttributes.baseSellPrice) * 1.5);
                if (!player.getInventory().addItem(item)) {
                    view.showMessage("Your back pack is full!");
                } else {
                    player.getInventory().addItem(item);
                }
            } else if (0.9 <= quality) {
                item.setAttribute(ItemAttributes.baseSellPrice,
                        (int) item.getAttribute(ItemAttributes.baseSellPrice) * 2);
                if (!player.getInventory().addItem(item)) {
                    view.showMessage("Your back pack is full!");
                } else {
                    player.getInventory().addItem(item);
                }
            }
        }
    }
}
