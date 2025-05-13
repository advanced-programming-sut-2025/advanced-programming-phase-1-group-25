package org.example.Controllers.PreGameMenuController;

import org.example.Enums.GameMenus.Menus;
import org.example.Models.App;
import org.example.Models.User;
import org.example.Views.PreGameMenus.LoginMenuView;
import org.example.Views.PreGameMenus.TerminalAnimation;

import java.util.Scanner;

import static org.example.Controllers.PreGameMenuController.SecurityQuestions.askPersonalSecurityQuestion;

public class LoginMenuController {

    private LoginMenuView view;
    public LoginMenuController(LoginMenuView view) {
        this.view = view;
    }

    public String changeMenu(String menu) {
        switch (menu.toLowerCase()) {
            case "signup menu", "signupmenu", "signup" -> {
                App.setCurrentMenu(Menus.PreGameMenus.SIGNUP_MENU);
                try {
                    TerminalAnimation.loadingAnimation("redirecting to signup menu");
                    return "\nYou are now in signup menu.\n";
                } catch (InterruptedException e) {
                    return "Problem redirecting to signup menu. Please try again later.\n";
                }
            }
            case "login menu", "loginmenu", "login" -> {
                return "You are in login menu now!\n";
            }
            default -> {
                return "You only have access to signup and login menus right now.\n";
            }
        }
    }

    public String exitMenu() {
        try {
            TerminalAnimation.loadingAnimation("Exiting the game\n");
        } catch (InterruptedException e) {
            return "Problem exiting the game. Please try again later.\n";
        }

        // logics to end the program.
        App.setCurrentMenu(Menus.PreGameMenus.EXIT_MENU);
        return "";
    }
    public String showCurrentMenu() {
        return "You are in login menu.\n";
    }

    public String login(Scanner sc, String username, String password, boolean stayLoggedIn) {
        if(!App.userExists(username)) {
            return "User not found.\n";
        }
        String correctPassword = App.getUser(username).getPassword();
        if (!password.equals(correctPassword)) {
            return "Password is not correct.\n";
        }
        if (!SecurityQuestions.askSecurityQuestion(sc)) {
            return "Login failed. Please try again.\n";
        }
        if (stayLoggedIn) {
            // login to save the session of the player.
        }
        App.setCurrentUser(App.getUser(username));
        App.setCurrentMenu(Menus.PreGameMenus.MAIN_MENU);
        try {
            TerminalAnimation.loadingAnimation("Redirecting to main menu");
        } catch (InterruptedException e) {
            return "Problem logging you in. Please try again later.\n";
        }
        return "Logged in successfully. You are now in main menu.\n";

}
    public String forgetPassword(Scanner sc, String username) {
        if (!App.userExists(username)) {
            return "User not found.\n";
        }
        System.out.printf("Enter you email address.\n");
        String email = sc.nextLine();
        String correctEmail = App.getUser(username).getEmail();
        if (!email.equals(correctEmail)) {
            return "Incorrect email address.\n";
        }

        if (!askPersonalSecurityQuestion(username, sc)) return "Retrieving failed. Please try again later.";

        User currentUser = App.getCurrentUser();
        String password = currentUser.getPassword();
        String emailMessage = String.format("Your password is %s", password); // TODO: a web page to change the password
        if (EmailSender.sendEmail(email, emailMessage)) {
            this.view.showMessage("Your password is sent to your email address.\n");
        } else {
            this.view.showMessage("Problem sending you the email. Please try again later.");
        }
        return "";
        // TODO: must be changed
    }
}
