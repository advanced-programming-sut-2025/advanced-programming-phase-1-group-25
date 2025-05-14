package org.example.Views.InGameMenus;

import org.example.Controllers.InGameMenuController.*;
import org.example.Enums.InGameMenuCommands.ActionMenuCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.MapElements.Shop;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenuView implements AppMenu {
    private Scanner scanner;
    private ShopMenuController controller;

    @Override
    public void handleInput(Scanner sc) {
        this.scanner = sc;
        String input = scanner.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (ActionMenuCommands command : ActionMenuCommands.values()) { // TODO: change to shopMenuCommands enum
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, input);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    private void executeCommand(ActionMenuCommands command, Matcher matcher, String input) {

        controller = new ShopMenuController(this);

        switch (command) {
            case SHOW_ALL_PRODUCTS:
                controller.showAllProducts();
                break;
            case SHOW_ALL_AVAILABLE_PRODUCTS:
                controller.showAllAvailableProducts();
                break;
            case PURCHASE:
                controller.purchase(matcher.group("productName"), matcher.group("count"));
                break;
            case CHEAT_ADD_DOLLARS:
                controller.cheatAddDollars(matcher.group("count"));
                break;
            case SWITCH_MENU:
                controller.changeMenu();
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

