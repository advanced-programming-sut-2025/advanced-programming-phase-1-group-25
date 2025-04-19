package org.example.Controllers.PreGameMenuController;

import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Position;
import org.example.Models.Player.Player;
import org.example.Models.User;
import org.example.Views.PreGameMenus.GameMenu;
import org.example.Views.PreGameMenus.TerminalAnimation;

import java.io.LineNumberReader;
import java.lang.management.PlatformLoggingMXBean;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

public class GameMenuController {

    public static String makeNewGame(Scanner sc) {
        ArrayList<User> gameUsers = getUsersForNewGame(sc);
        ArrayList<Player> gamePlayers = new ArrayList<>();

        for (User user : gameUsers) {
            Player newPlayer = new Player(user, user.getName(), user.getGender(), new Position(0, 0));
            gamePlayers.add(newPlayer);
        }

        Map<Player, PlayerMap> playerMaps = getPlayerMaps(sc, gamePlayers);

        Game newGame = new Game(gamePlayers, playerMaps);
        // TODO

    }
    private static ArrayList<User> getUsersForNewGame(Scanner sc) {
        ArrayList<User> gameUsers = new ArrayList<>();
        gameUsers.add(App.getCurrentUser());
        int counter = 0;
        System.out.print("Enter the users you want to play with. " +
                "(next : \"next\") (back to menu : \"back\")");
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("back")) {
                try {
                    TerminalAnimation.loadingAnimation("cancelling process");
                    return null;
                } catch (InterruptedException e) {
                    return null;
                }
            }
            if (input.equalsIgnoreCase("next")) {
                try {
                    TerminalAnimation.loadingAnimation("Wait for users to accept your invitation");
                    break;
                } catch (InterruptedException e) {
                    System.out.print("Problem making a new game. Please try again later.\n");
                    return null;
                }
            }
            if (input.isEmpty()) {
                System.out.print("Please enter a valid username.\n");
                continue;
            }
            if (!App.userExists(input)) {
                System.out.print("User doesn't exist. Try again.\n");
                continue;
            }
            User user = App.getUser(input);
            if (user.isInAnyGame()) {
                System.out.printf("User %s is in another game right now! Try another one.\n", input);
                continue;
            }
            gameUsers.add(user);
            counter++;
            if (counter == 3) {
                System.out.printf("User %s added successfully.\n", input);
                try {
                    TerminalAnimation.loadingAnimation("Wait for users to accept your invitation");
                    break;
                } catch (InterruptedException e) {
                    System.out.printf("Problem making a new game. Please try again later.\n");
                    return null;
                }
            }
            System.out.printf("User %s added successfully. Enter the next username.\n", input);
        }

        return gameUsers;
    }
    private static Map<Player, PlayerMap> getPlayerMaps(Scanner sc, ArrayList<Player> players) {
        Map<Player, PlayerMap> playerMaps = new LinkedHashMap<>();
        Map<Integer, PlayerMap> maps; // this should be initialized
        // print maps
        System.out.print("Choose you map.\n");
        int counter = 0;
        while (true) {
            System.out.printf("Player %s: ", players.get(counter));
            int mapNumber;
            try {
                mapNumber = Integer.parseInt(sc.nextLine());
                System.out.print("\n");
            } catch (NumberFormatException e) {
                System.out.print("\nPlease enter a valid number.\n");
                continue;
            }

            if (mapNumber < 1 || mapNumber > 4) {
                System.out.print("Please enter a number between 1 and 4.\n");
                continue;
            }

            PlayerMap playerMap = maps.get(mapNumber);
            if (playerMaps.containsValue(playerMap)) {
                System.out.print("Selected map is already chosen by another player.\n");
                continue;
            }

            playerMaps.put(players.get(counter), playerMap);
            counter++;
            if (counter == players.size()) {

                return playerMaps;
            }
        }
    }

}
