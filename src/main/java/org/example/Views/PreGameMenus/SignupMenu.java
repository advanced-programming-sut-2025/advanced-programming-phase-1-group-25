package org.example.Views.PreGameMenus;

import org.example.Controllers.PreGameMenuController.SignupMenuController;
import org.example.Enums.PreGameMenuCommands.SignupMenuCommands;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;


public class SignupMenu implements AppMenu {
    @Override
    public void handleInput(Scanner sc) {
        String input = sc.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (SignupMenuCommands command : SignupMenuCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, sc);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    private static void executeCommand(SignupMenuCommands command, Matcher matcher, Scanner sc) {
        switch (command) {
            case CHANGE_MENU:
                System.out.printf(SignupMenuController.changeMenu(matcher.group("menu")));
                break;
            case MENU_EXIT:
                System.out.printf(SignupMenuController.exitMenu());
                break;
            case SHOW_CURRENT_MENU:
                System.out.printf(SignupMenuController.showCurrentMenu());
                break;
            case REGISTER:
                System.out.printf(SignupMenuController.register(
                        sc
                        , matcher.group("username")
                        , matcher.group("password")
                        , matcher.group("nickname")
                        , matcher.group("email")
                        , matcher.group("gender")));
                break;
        }
    }
}