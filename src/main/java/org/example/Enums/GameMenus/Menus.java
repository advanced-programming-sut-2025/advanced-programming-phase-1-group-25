package org.example.Enums.GameMenus;

import org.example.Views.AppMenu;
import org.example.Views.InGameMenus.*;
import org.example.Views.PreGameMenus.*;
import org.example.Views.PreGameMenus.ExitMenu;

import java.util.Scanner;

/*
    This class has enums containing every menu in the game.
    later we use Menus.InGameMenus.HOME_MENU for example to change the current menu.
 */
public class Menus {
    public enum PreGameMenus implements Menu {
        SIGNUP_MENU(new SignupMenuView()),
        LOGIN_MENU(new LoginMenuView()),
        MAIN_MENU(new MainMenu()),
        PROFILE_MENU(new ProfileMenu()),
        AVATAR_MENU(new AvatarMenu()),
        GAME_MENU(new GameMenu()),
        EXIT_MENU(new ExitMenu());

        private final AppMenu menu;

        PreGameMenus(AppMenu menu) {
            this.menu = menu;
        }

        public void check(Scanner sc) {
            this.menu.handleInput(sc);
        }
    }

    public enum InGameMenus implements Menu {
        HOME_MENU(new HomeMenu()),
        CRAFTING_MENU(new CraftingMenu()),
        COOKING_MENU(new CookingMenu()),
        ACTION_MENU(new ActionMenuView()),
        SHOP_MENU(new ShopMenuView()),
        EXIT_MENU(new ExitMenu()),
        INVENTORY_MENU(new InventoryMenu()),
        MENU_SWITCHER(new MenuSwitcherView());
        private final AppMenu menu;

        InGameMenus(AppMenu menu) {
            this.menu = menu;
        }

        public void check(Scanner sc) {
            this.menu.handleInput(sc);
        }
    }
}
