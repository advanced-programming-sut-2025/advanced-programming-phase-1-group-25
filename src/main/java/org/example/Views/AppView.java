package org.example.Views;

import org.example.Enums.GameMenus.Menus;
import org.example.Models.App;

import java.util.Scanner;


/*
    Main loop of the program!
 */
public class AppView {
    public void run() {
        Scanner sc = new Scanner(System.in);
        do {
            App.getCurrentMenu().check(sc);
        } while (App.getCurrentMenu()
                != Menus.PreGameMenus.EXIT_MENU &&
                App.getCurrentMenu()
                != Menus.InGameMenus.EXIT_MENU);
    }
}
