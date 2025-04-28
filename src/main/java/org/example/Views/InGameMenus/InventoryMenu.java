package org.example.Views.InGameMenus;

import org.example.Controllers.InGameMenuController.ActionMenuController;
import org.example.Controllers.InGameMenuController.InventoryController;
import org.example.Enums.InGameMenuCommands.ActionMenuCommands;
import org.example.Enums.InGameMenuCommands.InventoryCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class InventoryMenu implements AppMenu {
    @Override
    public void handleInput(Scanner sc) {
        String input = sc.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (InventoryCommands command : InventoryCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, input);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    private static void executeCommand(InventoryCommands command, Matcher matcher, String input) {
        Game game = App.getCurrentGame();
        InventoryController controller = new InventoryController();
        switch (command) {
            case INVENTORY_SHOW:
                System.out.println(controller.showInventory(game));
                break;
            case INVENTORY_TRASH:
                System.out.println(controller.inventoryTrash(game, matcher, input));
                break;
        }
    }
}
