package org.example.Views.PreGameMenus;

import org.example.Controllers.PreGameMenuController.GameMenuController;
import org.example.Controllers.PreGameMenuController.LoginMenuController;
import org.example.Enums.GameMenus.Menus;
import org.example.Enums.PreGameMenuCommands.GameMenuCommands;
import org.example.Enums.PreGameMenuCommands.LoginMenuCommands;
import org.example.Views.AppMenu;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    @Override
    public void handleInput(Scanner sc) {
        String input = sc.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (GameMenuCommands command : GameMenuCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, sc);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public String prompt(String message) {
        return "";
    }

    private static void executeCommand(GameMenuCommands command, Matcher matcher, Scanner sc) {
        switch (command) {
            case NEW_GAME:
                System.out.printf(GameMenuController.makeNewGame(sc));
                break;
            case Exit_Menu:
                GameMenuController.changeMenu(Menus.PreGameMenus.MAIN_MENU, "main menu");
        }
    }
}