package org.example.Controllers.InGameMenuController;

import org.example.Enums.GameMenus.Menus;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Player.Player;

import java.util.regex.Matcher;

public class MenuSwitcher {
    public static String printMenus() {//TODO
//        Game game = App.getCurrentGame();
//        Player player = game.getCurrentPlayer();
//
//        for(int i = 0; i < 6; i++) {
//
//        }
        return "1-action menu\n"
                + "2-cooking menu\n"
                + "3-crafting menu\n"
                + "4-inventory menu\n"
                + "5-exiting menu\n"
                + "6-shop menu\n";

    }

    public String switchMenu(Matcher matcher) {
        String menu = matcher.group("menu");
        switch (menu.toLowerCase()) {
            case "action", "action menu" -> {
                App.setCurrentMenu(Menus.InGameMenus.ACTION_MENU);
                return "you are now in action menu!\n";
            }
            case "cooking", "cooking menu" -> {
                App.setCurrentMenu(Menus.InGameMenus.COOKING_MENU);
                return "you are now in cooking menu!\n";
            }
            case "crafting", "crafting menu" -> {
                App.setCurrentMenu(Menus.InGameMenus.CRAFTING_MENU);
                return "you are now in crafting menu!\n";
            }
            case "exit", "exit menu" -> {
                App.setCurrentMenu(Menus.InGameMenus.EXIT_MENU);
                return "you are now in exit menu!\n";
            }
            case "home", "home menu" -> {
                App.setCurrentMenu(Menus.InGameMenus.HOME_MENU);
                return "you are now in home menu!\n";
            }
            case "inventory", "inventory menu" -> {
                App.setCurrentMenu(Menus.InGameMenus.INVENTORY_MENU);
                return "you are now in inventory menu!\n";
            }
            case "shop", "shop menu" -> {
                App.setCurrentMenu(Menus.InGameMenus.SHOP_MENU);
                return "you are now in shop menu!\n";
            }
            default -> {
                return "Invalid menu!\n";
            }
        }
    }
}
