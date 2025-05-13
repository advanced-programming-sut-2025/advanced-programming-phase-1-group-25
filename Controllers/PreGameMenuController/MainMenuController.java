package org.example.Controllers.PreGameMenuController;

import org.example.Enums.GameMenus.Menus;
import org.example.Models.App;
import org.example.Enums.GameMenus.Menu;

public class MainMenuController {
    public void changeMenu(Menu menu) {
        App.setCurrentMenu(menu);
    }
    public void userLogout() {
        App.setCurrentUser(null);
        App.setCurrentMenu(Menus.PreGameMenus.LOGIN_MENU);
    }
}
