package org.example.Controllers.PreGameMenuController;

import org.example.Models.App;
import org.example.Models.GameHistory;
import org.example.Models.User;

import java.util.ArrayList;
import java.util.Collections;

public class ProfileMenuController {
    public void changeUsername(User user, String newUsername) {
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
}
