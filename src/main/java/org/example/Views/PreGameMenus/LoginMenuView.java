package org.example.Views.PreGameMenus;

import org.example.Controllers.PreGameMenuController.LoginMenuController;
import org.example.Enums.PreGameMenuCommands.LoginMenuCommands;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenuView implements AppMenu {
    private LoginMenuController controller;
    private Scanner scanner;
    @Override
    public void handleInput(Scanner sc) {
        this.scanner = sc;
        String input = sc.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (LoginMenuCommands command : LoginMenuCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, sc);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    private void executeCommand(LoginMenuCommands command, Matcher matcher, Scanner sc) {
        controller = new LoginMenuController(this);
        switch (command) {
            case CHANGE_MENU:
                controller.changeMenu(matcher.group("menu"));
                break;
            case MENU_EXIT:
                controller.exitMenu();
                break;
            case SHOW_CURRENT_MENU:
                controller.showCurrentMenu();
                break;
            case LOGIN:
                boolean stayLoggedIn = matcher.group("stayLoggedIn") != null;
                controller.login(
                        sc
                        , matcher.group("username")
                        , matcher.group("password")
                        , stayLoggedIn);
                break;
            case FORGET_PASSWORD:
                controller.forgetPassword(sc, matcher.group("username"));
                break;
        }
    }


    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}

