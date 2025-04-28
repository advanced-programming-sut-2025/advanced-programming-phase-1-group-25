package org.example.Controllers.InGameMenuController;

import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Player.Player;

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
            currentGame.getPlayerMap(currentPlayer).getGreenHouse().repair();
            currentPlayer.setCoin(currentPlayer.getCoin() - 1000);
            currentPlayer.getInventory().dropItem("wood", 500);
        }
        return "Green house has been repaired.";
    }
}

