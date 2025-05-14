package org.example.Views.InGameMenus;

import org.example.Controllers.InGameMenuController.CookingMenuController;
import org.example.Controllers.InGameMenuController.CraftingMenuController;
import org.example.Controllers.InGameMenuController.MenuSwitcher;
import org.example.Enums.InGameMenuCommands.CookingMenuCommands;
import org.example.Enums.InGameMenuCommands.CraftingMenuCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CookingMenu implements AppMenu {
    Scanner scanner;
    @Override
    public void handleInput(Scanner sc) {
        scanner = sc;
        String input = scanner.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (CookingMenuCommands command : CookingMenuCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, input);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    private void executeCommand(CookingMenuCommands command, Matcher matcher, String input) {
        Game game = App.getCurrentGame();
        CookingMenuController controller =new CookingMenuController(this);
        switch (command) {
            case SWITCH_MENU:
                System.out.println(MenuSwitcher.printMenus());
                controller.changeMenu();
                break;
            case COOKING_PREPARE :
                System.out.println(controller.cook(game, matcher.group("recipeName")));
                break;
            case COOKING_SHOW_RECIPES:
                System.out.println(controller.printRecipes(game));
                break;
            case COOKING_REFRIGERATOR_PUT:
                System.out.println(controller.putRef(matcher.group("item"), game));
                break;
            case COOKING_REFRIGERATOR_PICK:
                System.out.println(controller.pickRef(matcher.group("item"), game));
                break;
            case EAT:
                System.out.println(controller.eatFood(game, matcher.group("foodName")));
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
