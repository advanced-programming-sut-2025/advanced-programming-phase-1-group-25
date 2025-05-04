package org.example.Controllers.InGameMenuController;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import org.example.Enums.GameConsts.DayOfWeek;
import org.example.Enums.GameMenus.Menus;
import org.example.Enums.ItemConsts.ItemDisplay;
import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.Position;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;
import org.example.Views.InGameMenus.ActionMenuView;
import org.example.Views.PreGameMenus.TerminalAnimation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;

public class ActionMenuController {
    ActionMenuView view;

    public ActionMenuController(ActionMenuView view) {
        this.view = view;
    }

    public void walk(String yString, String xString) {
        int y, x;
        try {
            y = Integer.parseInt(yString);
            x = Integer.parseInt(xString);
        } catch (NumberFormatException e) {
            this.view.showMessage("Please Enter valid Y and X");
            return;
        }

        // check whether the player can go to the destination or not

        Game currentGame = App.getCurrentGame();
        Player currentPlayer = currentGame.getCurrentPlayer();
        GameMap map = currentGame.getGameMap();
        int currentPlayerY = currentPlayer.getPosition().getY();
        int currentPlayerX = currentPlayer.getPosition().getX();
        Tile start = map.getTile(currentPlayerY, currentPlayerX);
        Tile goal = map.getTile(y, x);

        List<Tile> path = Walk.findPath(start, goal, map);
        if (path == null) {
            this.view.showMessage("No path found.");
            return;
        }

        int energyCost = Walk.calculateWalkEnergyCost(path);

        String input = this.view.prompt(String.format("The best path's energy cost is %d. (Your current energy: %d)\nDo you want to go to the destination?\n" +
                "1. Yes\n" +
                "2. No\n" +
                "3. Walk until my energy runs out.", energyCost, currentPlayer.getEnergy()));
        int number;

        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            this.view.showMessage("Please enter a valid number");
            return;
        }

        int playerEnergy = currentPlayer.getEnergy();

        if (number == 1) {
            if (playerEnergy < energyCost) {
                this.view.showMessage("You don't have enough energy.");
            } else {
                try {
                    TerminalAnimation.loadingAnimation("Walking");
                } catch (InterruptedException e) {
                    this.view.showMessage("Problem walking to the destination. Please try again.");
                    return;
                }
                Walk.walkToDestination(energyCost, y, x);
                this.view.showMessage("You are now in destination!");
            }
        } else if (number == 2) {
            this.view.showMessage("Cancelled.");
        } else if (number == 3) {
            Position finalPosition = Walk.walkUntilEnergyRunsOut(path);
            this.view.showMessage(String.format("You final position is y = %d | x = %d", finalPosition.getY(), finalPosition.getX()));
        } else {
            this.view.showMessage("Invalid command");
        }
    }

    public void nextTurn() {
        Game currentGame = App.getCurrentGame();
        Player nextPlayer = currentGame.getNextPlayer();
        currentGame.setCurrentPlayer(nextPlayer);
        view.showMessage(nextPlayer.getName() + "'s turn!");
    }

    public void buildGreenhouse() {
        Game currentGame = App.getCurrentGame();
        Player currentPlayer = currentGame.getCurrentPlayer();
        Inventory playerInventory = currentPlayer.getInventory();

        int playerWood = playerInventory.getItemAmount("wood");
        int playerCoin = currentPlayer.getCoin();

        if (playerWood < 500) {
            view.showMessage("You don't have enough wood!");
        } else if (playerCoin < 1000) {
            view.showMessage("You don't have enough coin!");
        } else {
            currentGame.getPlayerMap(currentPlayer).getGreenHouse().repair();
            currentPlayer.setCoin(currentPlayer.getCoin() - 1000);
            currentPlayer.getInventory().dropItem("wood", 500);
        }
        view.showMessage("Greenhouse has been repaired!");
    }

    public void cheatAdvanceTime(Matcher matcher, Game game) {
        String timeStr = matcher.group("hours");
        int time;
        try {
            time = Integer.parseInt(timeStr);
        } catch (NumberFormatException e) {
            this.view.showMessage("Please enter a valid hour!");
            return;
        }
        if (time < 0) {
            this.view.showMessage("time must be a positive integer!");
        }
        int newHour = game.getDateTime().updateTimeByHour(time);
        this.view.showMessage("time is now " + newHour + "!");
    }

    public void cheatAdvanceDate(Matcher matcher, Game game) {
        String timeStr = matcher.group("day");
        int time;
        try {
            time = Integer.parseInt(timeStr);
        } catch (NumberFormatException e) {
            this.view.showMessage("Please enter a valid day!");
            return;
        }
        if (time < 0) {
            this.view.showMessage("time must be a positive integer!");
        }
        DayOfWeek newDay = game.getDateTime().updateTimeByDay(time);
        view.showMessage("day is now " + newDay.toString().toLowerCase() + "!");
    }

    //    public String cheatWeather(Matcher matcher, Game game) {
//        String weather = matcher.group("type");
//    }
    public void printMap(String xStr, String yStr, String sizeStr) {
        Game game = App.getCurrentGame();
        int x, y, size;
        try {
            x = Integer.parseInt(xStr);
            y = Integer.parseInt(yStr);
        } catch (NumberFormatException e) {
            this.view.showMessage("Please enter a valid position!");
            return;
        }
        try {
            size = Integer.parseInt(sizeStr);
        } catch (NumberFormatException e) {
            this.view.showMessage("Please enter a valid size!");
            return;
        }
        Position position = new Position(y, x);
        getMapBySize(game, position, size);
    }

    public void getMapBySize(Game game, Position position, int size) {
        GameMap map = game.getGameMap();
        String[][] mapArray = new String[2 * size][2 * size];
        for (int i = position.getY() - size; i < position.getY() + size; i++) {
            for (int j = position.getX() - size; j < position.getX() + size; j++) {
                try {
                    ItemType type = map.getTile(i, j).getItem().getDefinition().getType();
                    String symbol = ItemDisplay.getDisplayByType(type);
                    mapArray[i - position.getY() + size][j - position.getX() + size] = symbol;
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        ArrayList<Player> players = App.getCurrentGame().getPlayers();
        int playerNumber = 1;
        for (Player player : players) {
            int playerY = player.getPosition().getY();
            int playerX = player.getPosition().getX();

            if (playerY >= position.getY() - size && playerY < position.getY() + size) {
                if (playerX >= position.getX() - size && playerX < position.getX() + size) {
                    mapArray[playerY - position.getY() + size][playerX - position.getX() + size] = Integer.toString(playerNumber);
                }
            }
            playerNumber++;
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 2 * size; i++) {
            for (int j = 0; j < 2 * size; j++) {
                output.append(mapArray[i][j]).append(" ");
            }
            output.append("\n");
        }
        view.showMessage(output.toString());
    }

    public void cheatSetEnergy(Matcher matcher, Game game) {
        String energyStr = matcher.group("value");
        int energy;
        try {
            energy = Integer.parseInt(energyStr);
        } catch (NumberFormatException e) {
            view.showMessage("Please enter a valid energy amount!");
            return;
        }
        if (energy <= 0) {
            view.showMessage("energy must be a positive integer!");
            return;
        }
        if (energy > 200) {
            view.showMessage("energy must be a less than 200!");
            return;
        }
        game.getCurrentPlayer().setEnergy(energy);
        view.showMessage("your energy has been set to " + energy + "!");
    }

    public void energyUnlimited(Game game) {
        game.getCurrentPlayer().setEnergy(Integer.MAX_VALUE);
        view.showMessage("your energy is now unlimited:)");
    }

    public void changeMenu() {
        App.setCurrentMenu(Menus.InGameMenus.MENU_SWITCHER);
    }

    public void equipTool(Matcher matcher) {
        Game game = App.getCurrentGame();
        String toolName = matcher.group("toolName").toLowerCase();
        Inventory inventory = game.getCurrentPlayer().getInventory();
        boolean found = false;
        for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
            if (itemDefinition.getDisplayName().equalsIgnoreCase(toolName)) {
                found = true;
            }
        }
        if (!found) {
            view.showMessage("please enter a valid tool name!");
            return;
        }
        ItemInstance tool = null;
        for (Map.Entry<ItemDefinition, Integer> entry : inventory.getItems().entrySet()) {
            ItemDefinition item = entry.getKey();
            if (item.getDisplayName().equalsIgnoreCase(toolName)) {
                tool = new ItemInstance(item);
            }
        }
        if (tool == null) {
            view.showMessage("you don't have " + toolName + " in your inventory!");
            return;
        }
        game.getCurrentPlayer().setCurrentTool(tool);
        view.showMessage("your current tool has been set to " + toolName + "!");
    }

    public void showCurrentTool() {
        Game game = App.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        if (currentPlayer.getCurrentTool() == null) {
            view.showMessage("you don't have a current tool!");
            return;
        }
        view.showMessage(currentPlayer.getCurrentTool().getDefinition().getDisplayName().toLowerCase());
    }

    public void showInventoryTools() {
        Game game = App.getCurrentGame();
        Inventory inventory = game.getCurrentPlayer().getInventory();
        StringBuilder toolsStr = new StringBuilder();
        for (Map.Entry<ItemDefinition, Integer> entry : inventory.getItems().entrySet()) {
            ItemDefinition item = entry.getKey();
            if (item.getType().equals(ItemType.tool)) {
                toolsStr.append(item.getDisplayName().toLowerCase()).append("\n");
            }
        }
        view.showMessage(toolsStr.toString());
    }

    public void craftInfo(Matcher matcher) {
        String name = matcher.group("craftName");
        ItemDefinition itemDefinition = null;
        for (ItemDefinition tmp : App.getItemDefinitions()) {
            if (tmp.getDisplayName().equalsIgnoreCase(name) && tmp.getType().equals(ItemType.all_crops)) {
                itemDefinition = tmp;
            }
        }
        if (itemDefinition == null) {
            view.showMessage("please select a crop!");
            return;
        }
        StringBuilder info = new StringBuilder();
        info.append("Name: ").append(itemDefinition.getDisplayName().toLowerCase()).append("\n");
        for (Map.Entry<ItemAttributes, Object> entry : itemDefinition.getBaseAttributes().entrySet()) {
            ItemAttributes itemAttributes = entry.getKey();
            Object object = entry.getValue();
            info.append(itemAttributes.toString()).append(": ").append(object.toString()).append("\n");
        }
        view.showMessage(info.toString());
    }

    public void useTool(Matcher matcher) {
        String direction = matcher.group("direction").trim();
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Tile tile = player.getPlayerTile(game);
        ItemInstance tool = player.getCurrentTool();
        if (tool == null) {
            view.showMessage("you don't have a tool in your hand!");
            return;
        }
        switch (direction) {
            case "up" -> ToolController.applyTool(tool, game.getGameMap().getTile
                    (tile.getPosition().getY() - 1, tile.getPosition().getX()), player);
            case "down" -> ToolController.applyTool(tool, game.getGameMap().getTile
                    (tile.getPosition().getY() + 1, tile.getPosition().getX()), player);
            case "left" -> ToolController.applyTool(tool, game.getGameMap().getTile
                    (tile.getPosition().getY(), tile.getPosition().getX() - 1), player);
            case "right" -> ToolController.applyTool(tool, game.getGameMap().getTile
                    (tile.getPosition().getY(), tile.getPosition().getX() + 1), player);
            case "up left" -> ToolController.applyTool(tool, game.getGameMap().getTile
                    (tile.getPosition().getY() - 1, tile.getPosition().getX() - 1), player);
            case "up right" -> ToolController.applyTool(tool, game.getGameMap().getTile
                    (tile.getPosition().getY() - 1, tile.getPosition().getX() + 1), player);
            case "down left" -> ToolController.applyTool(tool, game.getGameMap().getTile
                    (tile.getPosition().getY() + 1, tile.getPosition().getX() - 1), player);
            case "down right" -> ToolController.applyTool(tool, game.getGameMap().getTile
                    (tile.getPosition().getY() + 1, tile.getPosition().getX() + 1), player);
            default -> view.showMessage("please select a valid direction!");
        }
        ;
    }


}

