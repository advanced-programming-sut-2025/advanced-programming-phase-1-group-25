package org.example.Views;

import java.util.Scanner;


/*
    Interface for menus.
 */
public interface AppMenu {
    void handleInput(Scanner sc);
    void showMessage(String message);
    String prompt(String message);
}
