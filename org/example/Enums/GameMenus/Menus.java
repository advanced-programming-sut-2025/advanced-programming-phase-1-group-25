package advanced.org.example.Enums.GameMenus;

import advanced.org.example.Views.AppMenu;
import advanced.org.example.Views.InGameMenus.*;
import advanced.org.example.Views.PreGameMenus.*;
import advanced.org.example.Views.PreGameMenus.ExitMenu;

import java.util.Scanner;

/*
    This class has enums containing every menu in the game.
    later we use Menus.InGameMenus.HOME_MENU for example to change the current menu.
 */
public class Menus {
    public enum PreGameMenus implements Menu {
        SIGNUP_MENU(new SignupMenu()),
        LOGIN_MENU(new LoginMenu()),
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
        ACTION_MENU(new ActionMenu()),
        SHOP_MENU(new ShopMenu()),
        EXIT_MENU(new ExitMenu());

        private final AppMenu menu;

        InGameMenus(AppMenu menu) {
            this.menu = menu;
        }

        public void check(Scanner sc) {
            this.menu.handleInput(sc);
        }
    }
}
