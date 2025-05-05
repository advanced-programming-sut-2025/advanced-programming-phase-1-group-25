package org.example.Views.InGameMenus;

import org.example.Controllers.InGameMenuController.ActionMenuController;
import org.example.Controllers.InGameMenuController.InventoryController;
import org.example.Controllers.InGameMenuController.MenuSwitcher;
import org.example.Enums.InGameMenuCommands.ActionMenuCommands;
import org.example.Enums.InGameMenuCommands.InventoryCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class InventoryMenu implements AppMenu {
    Scanner scanner;
    InventoryController controller;

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

    private void executeCommand(InventoryCommands command, Matcher matcher, String input) {
        Game game = App.getCurrentGame();
        this.controller = new InventoryController(this);
        switch (command) {
            case SWITCH_MENU:
                System.out.println(MenuSwitcher.printMenus());
                controller.changeMenu();
                break;
            case INVENTORY_SHOW:
                controller.showInventory(game);
                break;
            case INVENTORY_TRASH:
                controller.inventoryTrash(game, matcher, input);
                break;
        }
    }

    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
