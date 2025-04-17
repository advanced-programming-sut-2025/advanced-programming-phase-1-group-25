package advanced.org.example.Views.PreGameMenus;

import advanced.org.example.Controllers.PreGameMenuController.LoginMenuController;
import advanced.org.example.Enums.PreGameMenuCommands.LoginMenuCommands;
import advanced.org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu {
    @Override
    public void handleInput(Scanner sc) {
        String input = sc.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (LoginMenuCommands command : LoginMenuCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, sc);
                break;
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    private static void executeCommand(LoginMenuCommands command, Matcher matcher, Scanner sc) {
        switch (command) {
            case CHANGE_MENU:
                System.out.printf(LoginMenuController.changeMenu(matcher.group("menu")));
                break;
            case MENU_EXIT:
                System.out.printf(LoginMenuController.exitMenu());
                break;
            case SHOW_CURRENT_MENU:
                System.out.printf(LoginMenuController.showCurrentMenu());
                break;
            case LOGIN:
                boolean stayLoggedIn = matcher.group("stayLoggedIn") != null;
                System.out.printf(LoginMenuController.login(
                        sc
                        , matcher.group("username")
                        , matcher.group("password")
                        , stayLoggedIn));
                break;
            case FORGET_PASSWORD:
                System.out.printf(LoginMenuController.forgetPassword(sc, matcher.group("username")));
                break;
        }
    }
}

