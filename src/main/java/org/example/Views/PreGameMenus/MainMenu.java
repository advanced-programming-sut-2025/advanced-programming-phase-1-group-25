package org.example.Views.PreGameMenus;

import org.example.Controllers.PreGameMenuController.MainMenuController;
import org.example.Enums.GameMenus.Menus;
import org.example.Enums.PreGameMenuCommands.MainMenuCommands;
import org.example.Models.App;
import org.example.Views.AppMenu;

import java.util.Scanner;

public class MainMenu implements AppMenu {
    @Override
    public void handleInput(Scanner sc) {
        String command = sc.nextLine();
        command = command.trim();
        MainMenuController controller = new MainMenuController();
        if (MainMenuCommands.Current_Menu.getMatcher(command) != null) {
            System.out.println("you are in main menu");
        } else if (MainMenuCommands.Go_To_AvatarMenu.getMatcher(command) != null) {
            controller.changeMenu(Menus.PreGameMenus.AVATAR_MENU, "avatar menu");
        } else if (MainMenuCommands.Go_To_GameMenu.getMatcher(command) != null) {
            controller.changeMenu(Menus.PreGameMenus.GAME_MENU, "game menu");
        } else if (MainMenuCommands.Go_To_ProfileMenu.getMatcher(command) != null) {
            controller.changeMenu(Menus.PreGameMenus.PROFILE_MENU, "profile menu");
        } else if (MainMenuCommands.User_Logout.getMatcher(command) != null) {
            controller.userLogout();
        }else if(MainMenuCommands.Exit_Menu.getMatcher(command) != null) {
            controller.changeMenu(Menus.PreGameMenus.LOGIN_MENU, "login menu");
        }
        else {
            System.out.println("Invalid command");
        }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public String prompt(String message) {
        return "";
    }
}
