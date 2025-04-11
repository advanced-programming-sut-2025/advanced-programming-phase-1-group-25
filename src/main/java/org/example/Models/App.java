package org.example.Models;

import org.example.Enums.GameMenus.Menu;
import org.example.Enums.GameMenus.Menus;

import java.util.LinkedHashMap;
import java.util.Map;

/*
    Saves some important data during runtime, like current menu, current players, and ...
 */
public abstract class App {
    private static Menu currentMenu = Menus.PreGameMenus.LOGIN_MENU;
    private static Map<String, User> users = new LinkedHashMap<>();

    public static Menu getCurrentMenu() {
        return currentMenu;
    }
    public static void setCurrentMenu(Menu menu) {
        App.currentMenu = menu;
    }
    public static boolean hasUser(String username) {
        return users.containsKey(username);
    }
    public static void addUser(String username, User user) {
        App.users.put(username, user);
    }
}
