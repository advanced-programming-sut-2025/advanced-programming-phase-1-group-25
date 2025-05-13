package org.example.Controllers.PreGameMenuController;

import org.example.Enums.GameMenus.Menus;
import org.example.Models.App;
import org.example.Enums.GameMenus.Menu;
import org.example.Views.PreGameMenus.TerminalAnimation;

public class MainMenuController {
    public void changeMenu(Menu menu, String menuName) {
        try {
            TerminalAnimation.loadingAnimation("redirecting to " + menuName + " menu");
        }
        catch (InterruptedException e) {

        }
        App.setCurrentMenu(menu);
        System.out.println("Your are now in " + menuName);
    }
    public void userLogout() {
        App.setCurrentUser(null);
        App.setCurrentMenu(Menus.PreGameMenus.LOGIN_MENU);
        System.out.println("User logged out successfully");
    }
    public void switchMenu() {}
}
