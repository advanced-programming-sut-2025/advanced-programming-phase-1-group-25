package org.example.Controllers.InGameMenuController;

import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Player.Player;

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
        }
        catch (NumberFormatException e) {
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
        }
        catch (NumberFormatException e) {
            return "please enter a valid day!\n";
        }
        game.getDateTime().updateTimeByDay(time);
        return "";
    }
//    public String cheatWeather(Matcher matcher, Game game) {
//        String weather = matcher.group("type");
//    }
}

