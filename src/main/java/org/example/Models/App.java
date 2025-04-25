package org.example.Models;

import org.example.Enums.GameMenus.Menu;
import org.example.Enums.GameMenus.Menus;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.MapElements.GameMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/*
    Saves some important data during runtime, like current menu, current players, and ...
 */
public abstract class App {
    private static Game currentGame = null;
    private static ArrayList<ItemDefinition> itemDefinitions = new ArrayList<>();
    private static Menu currentMenu = Menus.PreGameMenus.GAME_MENU; // temporary
    private static Map<String, User> users = new LinkedHashMap<>();
    private static User currentUser = null;
    public static Menu getCurrentMenu() {
        return currentMenu;
    }
    public static void setCurrentMenu(Menu menu) {
        App.currentMenu = menu;
    }
    public static void addUser(String username, User user) {
        App.users.put(username, user);
    }
    public static User getUser(String username) {
        return users.get(username);
    }
    public static boolean userExists(String username) {
        return users.containsKey(username);
    }
    public static void setCurrentUser(User user) {
        App.currentUser = user;
    }
    public static User getCurrentUser() {
        return currentUser;
    }
    public static Map<String, User> getUsers() {
        return users;
    }
    public static void setItemDefinitions(ArrayList<ItemDefinition> itemDefinitions) {
        App.itemDefinitions = itemDefinitions;
    }
    public static ItemDefinition getItemDefinition(String itemId) {
        for (ItemDefinition itemDefinition : App.itemDefinitions) {
            if (itemDefinition.getId().equals(itemId)) {
                return itemDefinition;
            }
        }
        return null;
    }

    public static ArrayList<ItemDefinition> getItemDefinitions() {
        return App.itemDefinitions;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }
}
