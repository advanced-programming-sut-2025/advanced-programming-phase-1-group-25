package org.example.Controllers.PreGameMenuController;

import org.example.Controllers.UpdateMap.spawnRandom;
import org.example.Enums.GameMenus.Menus;
import org.example.Enums.ItemConsts.ItemDisplay;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Enums.MapConsts.AnsiColors;
import org.example.Enums.MapConsts.MapSizes;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.ItemLoader;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Position;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;
import org.example.Models.MapElements.PrepareMap;
import org.example.Models.User;
import org.example.Views.PreGameMenus.TerminalAnimation;

import java.util.*;

public class GameMenuController {

    public static String makeNewGame(Scanner sc) {

        ItemLoader.loadItems();

        GameMap newGameMap = PrepareMap.prepareMap();
        ArrayList<PlayerMap> farms = PrepareMap.makePlayerMaps(newGameMap);


        ArrayList<User> gameUsers = getUsersForNewGame(sc);
        if (gameUsers == null) return "New game canceled, You are now in game menu.\n";

        ArrayList<Player> gamePlayers = new ArrayList<>();

        for (User user : gameUsers) {
            Player newPlayer = new Player(user, user.getName(), user.getGender(), new Position(0, 0));
            gamePlayers.add(newPlayer);
        }

        Map<Player, PlayerMap> playerMaps = getPlayerMaps(sc, gamePlayers, newGameMap, farms);
        for (Map.Entry<Player, PlayerMap> entry : playerMaps.entrySet()) {
            Player player = entry.getKey();
            PlayerMap map = entry.getValue();
            int cottageY = map.getCottage().getTile().getPosition().getY();
            int cottageX = map.getCottage().getTile().getPosition().getX();
            player.setPosition(new Position(cottageY, cottageX));
        }

        Game newGame = new Game(gamePlayers, playerMaps, gamePlayers.get(0), newGameMap);
        App.setCurrentGame(newGame);

        spawnRandom.spawnRandomElements();

        App.setCurrentMenu(Menus.InGameMenus.ACTION_MENU);
        return "Game created successfully!\n";
    }

    private static ArrayList<User> getUsersForNewGame(Scanner sc) {
        ArrayList<User> gameUsers = new ArrayList<>();
        gameUsers.add(App.getCurrentUser());
        int counter = 0;
        System.out.print("Enter the users you want to play with. " +
                "(next : \"next\") (back to menu : \"back\")\n");
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
            if (gameUsers.contains(user)) {
                System.out.printf("User %s is already added!\n", input);
                continue;
            }
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

    private static Map<Player, PlayerMap> getPlayerMaps(Scanner sc, ArrayList<Player> players, GameMap gameMap, ArrayList<PlayerMap> farms) {
        Map<Player, PlayerMap> playerMaps = new LinkedHashMap<>();
        Map<Integer, PlayerMap> farmsWithNumber = new LinkedHashMap<>();
        int number = 1;
        for (PlayerMap playerMap : farms) {
            farmsWithNumber.put(number++, playerMap);
        }

        printMaps(farmsWithNumber);

        System.out.print("Choose your map.\n");
        int counter = 0;
        while (true) {
            System.out.printf("Player %s: ", players.get(counter).getName());
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

            PlayerMap playerMap = farmsWithNumber.get(mapNumber);
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

    private static void printMaps(Map<Integer, PlayerMap> maps) {
        for (Map.Entry<Integer, PlayerMap> entry : maps.entrySet()) {
            int number = entry.getKey();
            PlayerMap map = entry.getValue();
            System.out.printf("Map number %d:\n", number);
            for (int y = 0; y < MapSizes.FARM_ROWS.getSize(); y++) {
                for (int x = 0; x < MapSizes.FARM_COLS.getSize(); x++) {
                    Tile tile = map.getTile(y, x);
                    ItemType itemType = tile.getItem().getDefinition().getType();
                    String symbol = ItemDisplay.getDisplayByType(itemType);
                    System.out.print(AnsiColors.wrap(symbol + " ", tile.getForGroundColor(), tile.getBackGroundColor()));
                }
                System.out.println();
            }
            System.out.print("\n\n");
        }
    }
}
