package org.example.Views.InGameMenus;

import org.example.Controllers.InGameMenuController.MenuSwitcher;
import org.example.Enums.GameMenus.Menu;
import org.example.Enums.InGameMenuCommands.MenuSwitcherCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MenuSwitcherView implements AppMenu {
    public void handleInput(Scanner sc) {
        String input = sc.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (MenuSwitcherCommands command : MenuSwitcherCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, input);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    private static void executeCommand(MenuSwitcherCommands command, Matcher matcher, String input) {
        Game game = App.getCurrentGame();
        MenuSwitcher controller = new MenuSwitcher();
        switch (command) {
            case MENU_ENTER -> System.out.println(controller.switchMenu(matcher));
        }
    }
}
