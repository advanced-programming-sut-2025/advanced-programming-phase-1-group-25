package advanced.org.example.Views.PreGameMenus;

import advanced.org.example.Controllers.PreGameMenuController.ProfileMenuController;
import advanced.org.example.Enums.PreGameMenuCommands.ProfileMenuCommands;
import advanced.org.example.Models.App;
import advanced.org.example.Models.User;
import advanced.org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu implements AppMenu {
    @Override
    public void handleInput(Scanner sc) {
        String command = sc.nextLine();
        command = command.trim();
        ProfileMenuController controller = new ProfileMenuController();
        Matcher matcher;
        if ((matcher = ProfileMenuCommands.Change_Username.getMatcher(command)) != null) {
            String username = matcher.group("username");
            username = username.trim();
            if (ProfileMenuCommands.Username_Validation.getMatcher(username) == null) {
                System.out.println("username format is invalid");
            } else if (App.getCurrentUser().getUsername().equals(username)) {
                System.out.println("Please enter a new username!");
            } else if (App.userExists(username)) {
                System.out.println("Username already exists!");
            } else {
                User user = App.getUser(username);
                controller.changeUsername(user, username);
            }
        } else if ((matcher = ProfileMenuCommands.Change_NickName.getMatcher(command)) != null) {
            String nickname = matcher.group("nickname");
            nickname = nickname.trim();
            if (App.getCurrentUser().getName().equals(nickname)) {
                System.out.println("Please enter a new nickname!");
            } else {
                App.getCurrentUser().setName(nickname);
            }
        } else if ((matcher = ProfileMenuCommands.Change_Email.getMatcher(command)) != null) {
            String email = matcher.group("email");
            email = email.trim();
            if (App.getCurrentUser().getEmail().equals(email)) {
                System.out.println("Please enter a new email!");
            } else if (ProfileMenuCommands.Email_Validation.getMatcher(email) == null) {
                System.out.println("Email format is invalid");
            } else {
                App.getCurrentUser().setEmail(email);
            }
        } else if ((matcher = ProfileMenuCommands.Change_Password.getMatcher(command)) != null) {
            String newPassword = matcher.group("newPassword");
            String oldPassword = matcher.group("oldPassword");
            newPassword = newPassword.trim();
            oldPassword = oldPassword.trim();
            if (!App.getCurrentUser().getPassword().equals(oldPassword)) {
                System.out.println("Password does not match!");
            } else if (App.getCurrentUser().getPassword().equals(newPassword)) {
                System.out.println("Please enter a new password!");
            } else {
                App.getCurrentUser().setPassword(newPassword);
            }
        } else if (ProfileMenuCommands.User_Info.getMatcher(command) != null) {
            System.out.println("Username: " + App.getCurrentUser().getUsername());
            System.out.println("Nickname: " + App.getCurrentUser().getName());
            System.out.println("Highest coins earned: " + controller.getHighestCoinsEarned());
            System.out.println("Number of games: " + App.getCurrentUser().getGameHistory().size());
        } else {
            System.out.println("Invalid command!");
        }
    }
}
