package org.example.Controllers.InGameMenuController;

import org.example.Enums.GameMenus.Menus;
import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemLevels;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.Player.Player;
import org.example.Views.InGameMenus.InventoryMenu;

import java.util.Map;
import java.util.regex.Matcher;

public class InventoryController {
    InventoryMenu view;

    public InventoryController(InventoryMenu view) {
        this.view = view;
    }
    public void changeMenu() {
        App.setCurrentMenu(Menus.InGameMenus.MENU_SWITCHER);
    }

    public void showInventory(Game game) {
        Inventory inventory = game.getCurrentPlayer().getInventory();
        StringBuilder inventoryStr = new StringBuilder();
        for (Map.Entry<ItemDefinition, Integer> entry : inventory.getItems().entrySet()) {
            ItemDefinition item = entry.getKey();
            Integer value = entry.getValue();
            inventoryStr.append("name: \"").append(item.getDisplayName()).append("\", number in inventory: ")
                    .append(value).append("\n");
        }
        view.showMessage(inventoryStr.toString());
    }

    public void inventoryTrash(Game game, Matcher matcher, String input) {
        String itemName = matcher.group("itemName");
        Inventory inventory = game.getCurrentPlayer().getInventory();
        if (input.contains("-n")) {
            String numberStr = matcher.group("number");
            int number;
            try {
                number = Integer.parseInt(numberStr);
            } catch (NumberFormatException e) {
                view.showMessage("please enter a valid number!");
                return;
            }
            for (Map.Entry<ItemDefinition, Integer> entry : inventory.getItems().entrySet()) {
                ItemDefinition item = entry.getKey();
                if (item.getDisplayName().equals(itemName)) {
                    checkTrashCanLevel(item, game.getCurrentPlayer(), game.getCurrentPlayer().getTrashCan(), number);
                    entry.setValue(number);
                }
            }
            view.showMessage(number + "number of " + itemName + " has been trashed!");
        } else {
            for (Map.Entry<ItemDefinition, Integer> entry : inventory.getItems().entrySet()) {
                ItemDefinition item = entry.getKey();
                if (item.getDisplayName().equals(itemName)) {
                    checkTrashCanLevel(item, game.getCurrentPlayer(), game.getCurrentPlayer().getTrashCan(), entry.getValue());
                    inventory.getItems().remove(item);
                }
            }
            view.showMessage(itemName + "has been removed from your inventory!");
        }
    }

    public void checkTrashCanLevel(ItemDefinition item, Player player, ItemInstance trashCan, int amount) {
        if (trashCan.getDefinition().getId().name().equals("copper_trash_can")) {
            player.increaseCoin((int) ((int) item.getAttribute(ItemAttributes.price) * 0.15 * amount));
        } else if (trashCan.getDefinition().getId().name().equals("iron_trash_can")) {
            player.increaseCoin((int) ((int) item.getAttribute(ItemAttributes.price) * 0.3 * amount));
        } else if (trashCan.getDefinition().getId().name().equals("golden_trash_can")) {
            player.increaseCoin((int) ((int) item.getAttribute(ItemAttributes.price) * 0.45 * amount));
        } else if (trashCan.getDefinition().getId().name().equals("iridium_trash_can")) {
            player.increaseCoin((int) ((int) item.getAttribute(ItemAttributes.price) * 0.6 * amount));
        }
    }
}
