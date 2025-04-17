package advanced.org.example.Views.PreGameMenus;

import advanced.org.example.Enums.GameMenus.Menus;
import advanced.org.example.Enums.PreGameMenuCommands.MainMenuCommands;
import advanced.org.example.Models.App;
import advanced.org.example.Views.AppMenu;
import java.util.Scanner;

public class MainMenu implements AppMenu {
    @Override
    public void handleInput(Scanner sc) {
        String command = sc.nextLine();
        command = command.trim();
        if(MainMenuCommands.Go_TO_AvatarMenu.getMatcher(command) != null) {
            System.out.println("redirecting to avatar menu...");
            App.setCurrentMenu(Menus.PreGameMenus.AVATAR_MENU);
        } else if(MainMenuCommands.Go_To_GameMenu.getMatcher(command) != null) {
            System.out.println("redirecting to game menu...");
            App.setCurrentMenu(Menus.PreGameMenus.GAME_MENU);
        } else if(MainMenuCommands.Go_To_ProfileMenu.getMatcher(command) != null) {
            System.out.println("redirecting to profile menu...");
            App.setCurrentMenu(Menus.PreGameMenus.PROFILE_MENU);
        } else if(MainMenuCommands.User_Logout.getMatcher(command) != null) {
            System.out.println("User logged out successfully");
            App.setCurrentUser(null);
            App.setCurrentMenu(Menus.PreGameMenus.LOGIN_MENU);
        } else {
            System.out.println("Invalid command");
        }
    }
}
