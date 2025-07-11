package org.example.Controllers.PreGameMenuController;


import org.example.Enums.GameConsts.Gender;
import org.example.Enums.GameMenus.Menus;
import org.example.Models.App;
import org.example.Models.Question;
import org.example.Models.User;

import org.example.Views.PreGameMenus.SignupMenuView;
import org.example.Views.PreGameMenus.TerminalAnimation;

import java.util.Scanner;


import static org.example.Controllers.PreGameMenuController.SecurityQuestions.askSecurityQuestion;
import static org.example.Controllers.PreGameMenuController.Validation.*;

public class SignupMenuController {

    SignupMenuView view;

    public SignupMenuController(SignupMenuView view) {
        this.view = view;
    }

    public void changeMenu(String menu) {
        switch (menu.toLowerCase()) {
            case "login menu", "loginmenu", "login" -> {
                App.setCurrentMenu(Menus.PreGameMenus.LOGIN_MENU);
                try {
                    TerminalAnimation.loadingAnimation("redirecting to login menu");
                    this.view.showMessage("You are now in login menu.");
                } catch (InterruptedException e) {
                    this.view.showMessage("Problem redirecting to login menu. Please try again later.");
                }
            }
            case "signup menu", "signupmenu", "signup" -> {
                this.view.showMessage("You are in signup menu now!");
            }
            default -> {
                this.view.showMessage("You only have access to login and signup menus right now.");
            }
        }
    }

    public void exitMenu() {
        try {
            TerminalAnimation.loadingAnimation("Exiting the game\n");
        } catch (InterruptedException e) {
            this.view.showMessage("Problem exiting the game. Please try again later.");
            return;
        }

        // logics to end the program.

        App.setCurrentMenu(Menus.PreGameMenus.EXIT_MENU);
        return;
    }

    public void showCurrentMenu() {
        this.view.showMessage("You are in signup menu.");
    }

    public void register(Scanner sc,String username, String password,
                                  String nickname, String email, String gender) {
        String finalUsername = username;
        String finalPassword = password;
        if (App.userExists(username)) {
            finalUsername = handleDuplicateUsername(username, sc);
            if (finalUsername == null) {
                this.view.showMessage("Register failed. please try again.");
                return;
            }
        }
        if (!isUsernameValid(username)) {
            this.view.showMessage("Username is not valid.");
            return;
        }
        if (!isEmailValid(email)) {
            this.view.showMessage("Email address is not valid.");
            return;
        }

        if (password.equals("random password")) {
            finalPassword = handleRandomPassword(username, sc);
            if (finalPassword == null) {
                this.view.showMessage("Register failed. please try again.");
                return;
            }
        }

        if (!isPasswordValid(finalPassword)) {
            this.view.showMessage("Password is not valid.");
            return;
        }
        int weaknessState;
        if ((weaknessState = isPasswordWeak(finalPassword)) != -1) {
            this.view.showMessage(handleWeakPassword(password, weaknessState));
            return;
        }

        if (!isNicknameValid(nickname)) {
            this.view.showMessage("Nickname is not valid.");
            return;
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
                this.view.showMessage("Please enter male, female, or other.");
                return;
            }
        }

        System.out.print("Enter your password again.\n");
        String passwordConfirm = sc.nextLine();
        if (!passwordConfirm.equals(finalPassword)) {
            this.view.showMessage("Password confirmation doesn't match.");
            return;
        }

        if (!askSecurityQuestion(sc)) {
            this.view.showMessage("Register failed. Please try again.");
            return;
        }

        Question userSecurityQuestion;
        if ((userSecurityQuestion = SecurityQuestions.addSecurityQuestions(sc)) == null) {
            this.view.showMessage("Register failed. Please try again.");
            return;
        }

        try {
            TerminalAnimation.loadingAnimation("Creating your account");
        } catch (InterruptedException e) {
            this.view.showMessage("Problem creating your account. Please try again later.");
            return;
        }
        User newUser = new User(nickname, finalUsername, finalPassword, email, userGender, userSecurityQuestion);
        App.addUser(finalUsername, newUser);
        App.setCurrentMenu(Menus.PreGameMenus.LOGIN_MENU);
        this.view.showMessage("Account has been created successfully. You are now in login menu.\n");
    }
}


