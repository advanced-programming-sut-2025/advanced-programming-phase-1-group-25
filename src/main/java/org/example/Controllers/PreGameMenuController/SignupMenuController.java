package org.example.Controllers.PreGameMenuController;


import org.example.Enums.GameConsts.Gender;
import org.example.Enums.GameMenus.Menus;
import org.example.Models.App;
import org.example.Models.Question;
import org.example.Models.User;

import org.example.Views.PreGameMenus.TerminalAnimation;

import java.util.ArrayList;
import java.util.Scanner;


import static org.example.Controllers.PreGameMenuController.SecurityQuestions.askSecurityQuestion;
import static org.example.Controllers.PreGameMenuController.Validation.*;

public class SignupMenuController {
    public static String changeMenu(String menu) {
        switch (menu.toLowerCase()) {
            case "login menu", "loginmenu", "login" -> {
                App.setCurrentMenu(Menus.PreGameMenus.LOGIN_MENU);
                try {
                    TerminalAnimation.loadingAnimation("redirecting to login menu");
                    return "\nYou are now in login menu.\n";
                } catch (InterruptedException e) {
                    return "Problem redirecting to login menu. Please try again later.\n";
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
        return "You are in signup menu.\n";
    }

    public static String register(Scanner sc,String username, String password,
                                  String nickname, String email, String gender) {
        String finalUsername = username;
        String finalPassword = password;
        if (App.userExists(username)) {
            finalUsername = handleDuplicateUsername(username, sc);
            if (finalUsername == null) {
                return "Register failed. please try again.";
            }
        }
        if (!isUsernameValid(username)) {
            return "Username is not valid.\n";
        }
        if (!isEmailValid(email)) {
            return "Email address is not valid.\n";
        }

        if (password.equals("random password")) {
            finalPassword = handleRandomPassword(username, sc);
            if (finalPassword == null) {
                return "Register failed. please try again.";
            }
        }

        if (!isPasswordValid(finalPassword)) {
            return "Password is not valid.\n";
        }
        int weaknessState;
        if ((weaknessState = isPasswordWeak(finalPassword)) != -1) {
            return handleWeakPassword(password, weaknessState);
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
                userGender = Gender.OTHER;
            }
            default -> {
                return "Please enter male, female, or other.\n";
            }
        }

        System.out.print("Enter your password again.\n");
        String passwordConfirm = sc.nextLine();
        if (!passwordConfirm.equals(finalPassword)) {
            return "Password confirmation doesn't match.\n";
        }

        if (!askSecurityQuestion(sc)) return "Register failed. Please try again.";
        Question userSecurityQuestion;
        if ((userSecurityQuestion = SecurityQuestions.addSecurityQuestions(sc)) == null) return "Register failed. Please try again.";

        try {
            TerminalAnimation.loadingAnimation("Creating your account");
        } catch (InterruptedException e) {
            return "Problem creating your account. Please try again later.";
        }
        User newUser = new User(nickname, finalUsername, finalPassword, email, userGender, userSecurityQuestion);
        App.addUser(finalUsername, newUser);
        App.setCurrentMenu(Menus.PreGameMenus.LOGIN_MENU);
        return "Account has been created successfully. You are now in login menu.\n";
    }
}


