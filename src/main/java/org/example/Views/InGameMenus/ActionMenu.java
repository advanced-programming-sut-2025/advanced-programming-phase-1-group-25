package org.example.Views.InGameMenus;

import org.example.Controllers.PreGameMenuController.SignupMenuController;
import org.example.Enums.InGameMenuCommands.ActionMenuCommands;
import org.example.Enums.PreGameMenuCommands.SignupMenuCommands;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ActionMenu implements AppMenu {
    @Override
    public void handleInput(Scanner sc) {
        String input = sc.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (ActionMenuCommands command : ActionMenuCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, sc);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    private static void executeCommand(ActionMenuCommands command, Matcher matcher, Scanner sc) {
        switch (command) {

        }
    }
}
