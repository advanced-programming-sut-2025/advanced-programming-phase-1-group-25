package org.example.Controllers.InGameMenuController;

import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemInstance;

import java.util.Map;
import java.util.regex.Matcher;

public class InventoryController {
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
