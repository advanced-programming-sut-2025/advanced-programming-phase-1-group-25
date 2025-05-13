package org.example.Controllers.PreGameMenuController;

import org.example.Enums.GameMenus.Menus;
import org.example.Models.App;
import org.example.Models.GameHistory;
import org.example.Models.User;

import java.util.ArrayList;
import java.util.Collections;

public class ProfileMenuController {
    public void changeUsername(String newUsername) {
        User user = App.getUser(newUsername);
        user.setUsername(newUsername);
        App.getUsers().remove(newUsername);
        App.addUser(newUsername, user);
    }
    public int getHighestCoinsEarned() {
        ArrayList<Integer> coinsEarned = new ArrayList<>();
        for (GameHistory gameHistory : App.getCurrentUser().getGameHistory()) {
            coinsEarned.add(gameHistory.getCoinsEarned());
        }
        return Collections.max(coinsEarned);
    }
    public void exitMenu() {
        App.setCurrentMenu(Menus.PreGameMenus.MAIN_MENU);
    }
    public void changePassword(String password) {
        App.getCurrentUser().setPassword(password);
    }
    public void changeEmail(String email) {
        App.getCurrentUser().setEmail(email);
    }
    public void changeNickname(String nickname) {
        App.getCurrentUser().setName(nickname);
    }

}
