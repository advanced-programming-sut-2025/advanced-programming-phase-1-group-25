package org.example.Views.PreGameMenus;

import org.example.Controllers.PreGameMenuController.SignupMenuController;
import org.example.Enums.PreGameMenuCommands.SignupMenuCommands;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;


public class SignupMenuView implements AppMenu {

    Scanner scanner;

    @Override
    public void handleInput(Scanner sc) {
        this.scanner = sc;

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
            System.out.print("Invalid command. please try again.\n");
        }
    }

    private void executeCommand(SignupMenuCommands command, Matcher matcher, Scanner sc) {

        SignupMenuController controller = new SignupMenuController(this);

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
            case REGISTER:
                controller.register(
                        sc
                        , matcher.group("username")
                        , matcher.group("password")
                        , matcher.group("nickname")
                        , matcher.group("email")
                        , matcher.group("gender"));
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