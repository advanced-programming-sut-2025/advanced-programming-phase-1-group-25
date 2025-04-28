package org.example.Controllers.InGameMenuController;

import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.Position;
import org.example.Models.Player.Player;

import java.util.Map;
import java.util.regex.Matcher;

public class ActionMenuController {
    public String buildGreenhouse() {
        Game currentGame = App.getCurrentGame();
        Player currentPlayer = currentGame.getCurrentPlayer();
        Inventory playerInventory = currentPlayer.getInventory();

        int playerWood = playerInventory.getItemAmount("wood");
        int playerCoin = currentPlayer.getCoin();

        if (playerWood < 500) {
            return "You don't have enough wood.\n";
        } else if (playerCoin < 1000) {
            return "You don't have enough coin.\n";
        } else {
            // TODO : Logic to rebuild the greenhouse.
            return "";
        }
    }

    public String cheatAdvanceTime(Matcher matcher, Game game) {
        String timeStr = matcher.group("hours");
        int time;
        try {
            time = Integer.parseInt(timeStr);
        } catch (NumberFormatException e) {
            return "please enter a valid hour!\n";
        }
        game.getDateTime().updateTimeByHour(time);
        return "";
    }

    public String cheatAdvanceDate(Matcher matcher, Game game) {
        String timeStr = matcher.group("day");
        int time;
        try {
            time = Integer.parseInt(timeStr);
        } catch (NumberFormatException e) {
            return "please enter a valid day!\n";
        }
        game.getDateTime().updateTimeByDay(time);
        return "";
    }

    //    public String cheatWeather(Matcher matcher, Game game) {
//        String weather = matcher.group("type");
//    }
    public String printMap(Game game, Matcher matcher) {
        String xStr = matcher.group("x");
        String yStr = matcher.group("y");
        String sizeStr = matcher.group("size");
        int x, y, size;
        try {
            x = Integer.parseInt(xStr);
            y = Integer.parseInt(yStr);
        } catch (NumberFormatException e) {
            return "please enter a valid position!\n";
        }
        try {
            size = Integer.parseInt(sizeStr);
        } catch (NumberFormatException e) {
            return "please enter a valid size!\n";
        }
        Position position = new Position(y, x);
        return getMapBySize(game, position, size);
    }

    public String getMapBySize(Game game, Position position, int size) {
        GameMap map = game.getGameMap();
        StringBuilder mapStr = new StringBuilder();
        for (int i = position.getY() - size; i < position.getY() + size; i++) {
            for (int j = position.getX() - size; j < position.getX() + size; j++) {
                try {
                    mapStr.append(map.getTile(i, j).getItem().getDefinition().getDisplayName());
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
            mapStr.append("\n");
        }
        return mapStr.toString();
    }

    public String cheatSetEnergy(Matcher matcher, Game game) {
        String energyStr = matcher.group("value");
        int energy;
        try {
            energy = Integer.parseInt(energyStr);
        } catch (NumberFormatException e) {
            return "please enter a valid energy amount!\n";
        }
        game.getCurrentPlayer().setEnergy(energy);
        return "your energy has been set to " + energy + "!\n";
    }

    public String energyUnlimited(Game game) {
        game.getCurrentPlayer().setEnergy(Integer.MAX_VALUE);
        return "your energy is now unlimited:)\n";
    }

    public String showInventory(Game game) {
        Inventory inventory = game.getCurrentPlayer().getInventory();
        StringBuilder inventoryStr = new StringBuilder();
        for (Map.Entry<ItemInstance, Integer> entry : inventory.getItems().entrySet()) {
            ItemInstance item = entry.getKey();
            Integer value = entry.getValue();
            inventoryStr.append("name: " + item.getUniqueId() + "number in inventory: " + value + "\n");
        }
        return inventoryStr.toString();
    }

    public String inventoryTrash(Game game, Matcher matcher, String input) {
        String itemName = matcher.group("itemName");
        Inventory inventory = game.getCurrentPlayer().getInventory();
        if (input.contains("-n")) {
            String numberStr = matcher.group("number");
            int number;
            try {
                number = Integer.parseInt(numberStr);
            } catch (NumberFormatException e) {
                return "please enter a valid number!\n";
            }
            for (Map.Entry<ItemInstance, Integer> entry : inventory.getItems().entrySet()) {
                ItemInstance item = entry.getKey();
                if (item.getUniqueId().equals(itemName)) {
                    entry.setValue(number);
                }
            }
            return number + "number of " + itemName + " has been trashed!\n";
        } else {
            for (Map.Entry<ItemInstance, Integer> entry : inventory.getItems().entrySet()) {
                ItemInstance item = entry.getKey();
                if (item.getUniqueId().equals(itemName)) {
                    inventory.getItems().remove(item);
                }
            }
            return itemName + "has been removed from your inventory!\n";
        }
    }

}

