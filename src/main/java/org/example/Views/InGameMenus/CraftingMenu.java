package org.example.Views.InGameMenus;

import org.example.Controllers.InGameMenuController.CraftingMenuController;
import org.example.Controllers.InGameMenuController.InventoryController;
import org.example.Enums.InGameMenuCommands.ActionMenuCommands;
import org.example.Enums.InGameMenuCommands.CraftingMenuCommands;
import org.example.Enums.InGameMenuCommands.InventoryCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CraftingMenu implements AppMenu  {
    @Override
    public void handleInput(Scanner sc) {
        String input = sc.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (ActionMenuCommands command : ActionMenuCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, input);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    private static void executeCommand(ActionMenuCommands command, Matcher matcher, String input) {
        Game game = App.getCurrentGame();
        CraftingMenuController controller = new CraftingMenuController();
        switch (command) {
            case CRAFTING_CRAFT:
                System.out.println(controller.craftingCraft(matcher.group("itemName"),game ));
                break;
            case CHEAT_ADD_ITEM:
                System.out.println(controller.cheatAddItem(matcher.group("itemName"),Integer.parseInt(matcher.group("count")),game));
                break;
        }
    }
}
