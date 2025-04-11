package org.example.Controllers.PreGameMenuController;

import org.example.Enums.GameConsts.Gender;
import org.example.Enums.GameMenus.Menus;
import org.example.Models.App;
import org.example.Models.User;
import org.example.Views.PreGameMenus.ExitMenu;
import org.example.Views.PreGameMenus.TerminalAnimation;

import javax.swing.text.html.ListView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class SignupMenuController {
    public static String changeMenu(String menu) {
        switch (menu.toLowerCase()) {
            case "login menu", "loginmenu", "login" -> {
                App.setCurrentMenu(Menus.PreGameMenus.LOGIN_MENU);
                try {
                    TerminalAnimation.loadingAnimation("redirecting to login menu");
                    return "\nYou are now in login menu.\n";
                } catch (InterruptedException e) {
                    return "Problem redirecting to login menu. please try again later.\n";
                }
            }
            case "signup menu", "signupmenu", "signup" -> {
                return "You are in signup menu now!\n";
            }
            default -> {
                return "You only have access to login and signup menus right now.\n";
            }
        }
    }

    public static String exitMenu() {
        try {
            TerminalAnimation.loadingAnimation("Exiting the game\n");
        } catch (InterruptedException e) {
            return "Problem exiting the game. Please try again later.\n";
        }

        // logics to end the program.
        App.setCurrentMenu(Menus.PreGameMenus.EXIT_MENU);
        return "";
    }

    public static String showCurrentMenu() {
        return "You are in sign up menu.\n";
    }

    public static String register(Scanner sc,String username, String password,
                                  String nickname, String email, String gender) {
        String finalUsername = username;
        String finalPassword = password;
        if (App.hasUser(username)) {
            finalUsername = handleDuplicateUsername(username, sc);
        }
        if (!isUsernameValid(username)) {
            return "Username is not valid.\n";
        }
        if (!isEmailValid(email)) {
            return "Email address is not valid.\n";
        }

        ArrayList<String> randomPasswordCommands = new ArrayList<>(Arrays.asList(
                "random", "random pass", "random password", "randompassword"));


        if (randomPasswordCommands.contains(password)) {
            finalPassword = handleRandomPassword(username, sc);
        }

        if (!isPasswordValid(password)) {
            return "Password is not valid.\n";
        }
        if (isPasswordWeak(password)) {
            return handleWeakPassword(password);
        }

        System.out.printf("Enter your password again.\n");
        String passwordConfirm = sc.nextLine();
        if (!passwordConfirm.equals(finalPassword)) {
            return "Password confirmation doesn't match.\n";
        }

        if (!isNicknameValid(nickname)) {
            return "Nickname is not valid.\n";
        }

        Gender userGender;

        switch (gender.toLowerCase()) {
            case "male" -> {
                userGender = Gender.MALE;
            }
            case "female" -> {
                userGender = Gender.FEMALE;
            }
            case "other", "" -> {
                userGender = Gender.OTHER
            }
            default -> {
                return "Please enter male, female, or other.\n";
            }
        }

        try {
            TerminalAnimation.loadingAnimation("Creating your account");
        } catch (InterruptedException e) {
            return "Problem creating your account. Please try again later.\n";
        }

        User newUser = new User(nickname, finalUsername, finalPassword, email, userGender);
        App.addUser(finalUsername, newUser);
        return "Account has been created successfully.\n";
    }

    private static String handleDuplicateUsername(String username, Scanner sc) {

    }
    private static boolean isUsernameValid(String username) {

    }
    private static boolean isEmailValid(String email) {

    }
    private static boolean isPasswordValid(String password) {

    }
    private static boolean isPasswordWeak(String password) {

    }
    private static String handleRandomPassword(String username, Scanner sc) {

    }
    private static String handleWeakPassword(String password) {

    }
    private static boolean isNicknameValid(String nickname) {

    }
}


//        case PICK_QUESTION:
//        System.out.printf(SignupMenuController.pickQuestion(matcher.group("question_number"),
//                        matcher.group("answer"), matcher.group("answer_confirm")));
//        }