package advanced.org.example.Controllers.PreGameMenuController;

import advanced.org.example.Enums.GameMenus.Menus;
import advanced.org.example.Models.App;
import advanced.org.example.Views.PreGameMenus.TerminalAnimation;

import java.util.Scanner;

import static advanced.org.example.Controllers.PreGameMenuController.SecurityQuestions.askPersonalSecurityQuestion;

public class LoginMenuController {
    public static String changeMenu(String menu) {
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
        return "You are in login menu.\n";
    }

    public static String login(Scanner sc, String username, String password, boolean stayLoggedIn) {
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
    public static String forgetPassword(Scanner sc, String username) {
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

        // send email.

        return String.format("Your password is sent to your email address.\n");
    }
}
