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
        printMenus();
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

    @Override
    public void showMessage(String message) {

    }

    @Override
    public String prompt(String message) {
        return "";
    }

    private static void executeCommand(MenuSwitcherCommands command, Matcher matcher, String input) {
        Game game = App.getCurrentGame();
        MenuSwitcher controller = new MenuSwitcher();
        switch (command) {
            case MENU_ENTER -> System.out.println(controller.switchMenu(matcher));
        }
    }
    public String printMenus() {

        return "1. action menu\n"
                + "2. cooking menu\n"
                + "3. crafting menu\n"
                + "4. inventory menu\n"
                + "5. home menu\n"
                + "6. shop menu\n"
                + "7. exit menu\n";

    }

}
