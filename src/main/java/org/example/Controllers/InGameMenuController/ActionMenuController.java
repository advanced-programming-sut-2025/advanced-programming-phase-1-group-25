package org.example.Controllers.InGameMenuController;

import org.example.Enums.GameMenus.Menus;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.Position;
import org.example.Models.Player.Player;

import java.util.Map;
import java.util.regex.Matcher;

public class ActionMenuController {

    public String nextTurn() {
        Game currentGame = App.getCurrentGame();
        Player nextPlayer = currentGame.getNextPlayer();
        currentGame.setCurrentPlayer(nextPlayer);

        return String.format("%s's turn!\n", nextPlayer.getName());
    }
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
            currentGame.getPlayerMap(currentPlayer).getGreenHouse().repair();
            currentPlayer.setCoin(currentPlayer.getCoin() - 1000);
            currentPlayer.getInventory().dropItem("wood", 500);
        }
        return "Green house has been repaired.";
    }

    public String cheatAdvanceTime(Matcher matcher, Game game) {
        String timeStr = matcher.group("hours");
        int time;
        try {
            time = Integer.parseInt(timeStr);
        } catch (NumberFormatException e) {
            return "please enter a valid hour!\n";
        }
        if (time < 0) {
            return "time must be a positive number!\n";
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
        if (time < 0) {
            return "time must be a positive number!\n";
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

    public void changeMenu() {
        App.setCurrentMenu(Menus.InGameMenus.MENU_SWITCHER);
    }
//
//    public String walk(Matcher matcher) {
//        Game game = App.getCurrentGame();
//        String xStr = matcher.group("x");
//        String yStr = matcher.group("y");
//        int currentY = game.getCurrentPlayer().getPosition().getY();
//        int currentX = game.getCurrentPlayer().getPosition().getX();
//        int x, y;
//        try {
//            x = Integer.parseInt(xStr);
//            y = Integer.parseInt(yStr);
//        } catch (NumberFormatException e) {
//            return "please enter a valid position!\n";
//        }
//    }

    public String equipTool(Matcher matcher) {
        Game game = App.getCurrentGame();
        String toolName = matcher.group("toolName").toLowerCase();
        Inventory inventory = game.getCurrentPlayer().getInventory();
        boolean found = false;
        for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
            if (itemDefinition.getDisplayName().equals(toolName)) {
                found = true;
            }
        }
        if (!found) {
            return "please enter a valid tool name!\n";
        }
        found = false;
        ItemInstance tool = null;
        for (Map.Entry<ItemInstance, Integer> entry : inventory.getItems().entrySet()) {
            ItemInstance item = entry.getKey();
            if (item.getDefinition().getDisplayName().equals(toolName)) {
                found = true;
                tool = item;
            }
        }
        if (!found) {
            return "you don't have " + toolName + " in your inventory!\n";
        }
        game.getCurrentPlayer().setCurrentTool(tool);
        return "your current tool has been set to " + toolName + "!\n";
    }
    public String showCurrentTool() {
        Game game = App.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        if(currentPlayer.getCurrentTool() == null) {
            return "you don't have a current tool!\n";
        }
        return currentPlayer.getCurrentTool().getDefinition().getDisplayName();
    }
    public String showInventoryTools() {
        Game game = App.getCurrentGame();
        Inventory inventory = game.getCurrentPlayer().getInventory();
        StringBuilder toolsStr = new StringBuilder();
        for (Map.Entry<ItemInstance, Integer> entry : inventory.getItems().entrySet()) {
            ItemInstance item = entry.getKey();
            if(item.getDefinition().getType().equals(ItemType.tool)) {
                toolsStr.append(item.getDefinition().getDisplayName()).append("\n");
            }
        }
        return toolsStr.toString();
    }
}

