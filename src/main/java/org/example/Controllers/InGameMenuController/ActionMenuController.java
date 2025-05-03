package org.example.Controllers.InGameMenuController;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import org.example.Enums.GameMenus.Menus;
import org.example.Enums.ItemConsts.ItemDisplay;
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
    public String printMap(String xStr, String yStr, String sizeStr) {
        Game game = App.getCurrentGame();
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
        return output.toString();
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

