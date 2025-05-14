package org.example.Views.InGameMenus;

import org.example.Views.AppMenu;

import java.util.Scanner;

public class TradeMenuView implements AppMenu {

    Scanner scanner;

    @Override
    public void handleInput(Scanner sc) {
        this.scanner = sc;
    }

    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
