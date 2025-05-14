package org.example.Views.InGameMenus;

import org.example.Controllers.InGameMenuController.CraftingMenuController;
import org.example.Controllers.InGameMenuController.InventoryController;
import org.example.Controllers.InGameMenuController.MenuSwitcher;
import org.example.Enums.InGameMenuCommands.ActionMenuCommands;
import org.example.Enums.InGameMenuCommands.CraftingMenuCommands;
import org.example.Enums.InGameMenuCommands.InventoryCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CraftingMenu implements AppMenu  {
    Scanner scanner;
    @Override
    public void handleInput(Scanner sc) {
        scanner = sc;
        String input = scanner.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (CraftingMenuCommands command : CraftingMenuCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, input);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }
    private void executeCommand(CraftingMenuCommands command, Matcher matcher, String input) {
        Game game = App.getCurrentGame();
        CraftingMenuController controller =new CraftingMenuController(this);
        switch (command) {
            case CRAFTING_CRAFT:
                System.out.println(controller.craftingCraft(matcher.group("itemName"),game ));
                break;
            case CHEAT_ADD_ITEM:
                System.out.println(controller.cheatAddItem(matcher.group("itemName"),Integer.parseInt(matcher.group("count")) ,game));
                break;
            case SWITCH_MENU:
                System.out.println(MenuSwitcher.printMenus());
                controller.changeMenu();
                break;
            case PLACE_ITEM:
                System.out.println(controller.placeItem(matcher.group("direction"),game, matcher.group("itemName") ));
                break;
            case CRAFTING_SHOW_RECIPES:
                System.out.println(controller.printRecipes(game));
                break;
        }
    }
    public String prompt(String message) {
        System.out.println(message);
        String input = scanner.nextLine();
        return input;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
