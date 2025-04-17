package advanced.org.example.Controllers.PreGameMenuController;

import advanced.org.example.Models.App;
import advanced.org.example.Models.GameHistory;
import advanced.org.example.Models.User;

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
