package org.example.Controllers.InGameMenuController;

import org.example.Enums.GameConsts.WeatherStates;
import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemIDs;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Enums.NPCConsts.NPCConst;
import org.example.Models.Animals.Animal;
import org.example.Models.Animals.Barn;
import org.example.Models.Animals.Coop;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Position;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;
import org.example.Views.InGameMenus.ActionMenuView;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;

public class AnimalController {
    static ActionMenuView view = new ActionMenuView();

    public static boolean isNearLake(Player player, Game game) {
        Tile playerTile = player.getPlayerTile(game);
        GameMap gameMap = game.getGameMap();
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        return gameMap.getTile(y, x - 1).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y, x + 1).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y + 1, x - 1).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y + 1, x).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y + 1, x + 1).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y - 1, x - 1).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y - 1, x).getItem().getDefinition().getType().equals(ItemType.lake)
                || gameMap.getTile(y - 1, x + 1).getItem().getDefinition().getType().equals(ItemType.lake);
    }

    public static double getMBasedOnWeather(Game game) {
        WeatherStates weather = game.getWeather().getCurrentWeather();
        switch (weather) {
            case SUNNY -> {
                return 1.5;
            }
            case RAIN -> {
                return 1.2;
            }
            case STORM -> {
                return 0.5;
            }
            default -> {
                return 1;
            }
        }
    }

    public static int calculateQuality(int R, double M, int skill, double pole) {
        return (int) ((R * pole * (skill + 2)) / (7 - M));
    }

    public static void printFish(int quality, int number, ArrayList<ItemDefinition> caughtFish) {
        view.showMessage("Quality of fish : " + quality);
        view.showMessage("Number of fish : " + number);
        for (ItemDefinition fish : caughtFish) {
            view.showMessage("Fish name: " + fish.getDisplayName().toLowerCase() + " type: " + fish.getType().name());
        }
    }

    public static void putFishInInventory(Player player, ArrayList<ItemDefinition> caughtFish, int quality) {
        for (ItemDefinition fish : caughtFish) {
            ItemInstance item = new ItemInstance(fish);
            if (0.5 <= quality && quality < 0.7) {
                item.setAttribute(ItemAttributes.baseSellPrice,
                        (int) item.getAttribute(ItemAttributes.baseSellPrice) * 1.25);
                if (!player.getInventory().addItem(item)) {
                    view.showMessage("Your back pack is full!");
                } else {
                    player.getInventory().addItem(item);
                }
            } else if (0.7 <= quality && quality < 0.9) {
                item.setAttribute(ItemAttributes.baseSellPrice,
                        (int) item.getAttribute(ItemAttributes.baseSellPrice) * 1.5);
                if (!player.getInventory().addItem(item)) {
                    view.showMessage("Your back pack is full!");
                } else {
                    player.getInventory().addItem(item);
                }
            } else if (0.9 <= quality) {
                item.setAttribute(ItemAttributes.baseSellPrice,
                        (int) item.getAttribute(ItemAttributes.baseSellPrice) * 2);
                if (!player.getInventory().addItem(item)) {
                    view.showMessage("Your back pack is full!");
                } else {
                    player.getInventory().addItem(item);
                }
            }
        }
    }

    public static void buildBarnOrCoop(Matcher matcher, Game game) {
        Player player = game.getCurrentPlayer();
        PlayerMap playerMap = player.getPlayerMap();
        String buildingName = matcher.group("buildingName").trim().toLowerCase();
        String yStr = matcher.group("y").trim().toLowerCase();
        String xStr = matcher.group("x").trim().toLowerCase();
        Inventory inventory = player.getInventory();
        int x, y;
        try {
            x = Integer.parseInt(xStr);
            y = Integer.parseInt(yStr);
        } catch (NumberFormatException e) {
            view.showMessage("Please enter a valid position!");
            return;
        }
        int yShop = NPCConst.ShopPositions.CarpenterShop.getY();
        int xShop = NPCConst.ShopPositions.CarpenterShop.getX();
        int playerY = player.getPosition().getY();
        int playerX = player.getPosition().getX();
        if (!(xShop - 1 <= playerX && playerX <= xShop + 1
                && yShop - 1 <= playerY && playerY <= yShop + 1)) {
            view.showMessage("You should be near Carpenter's shop to buy " + buildingName + "!");
        }
        if (!(playerMap.getStartPosition().getX() <= x && x <= playerMap.getEndPosition().getX()
                && playerMap.getStartPosition().getY() <= y && y <= playerMap.getEndPosition().getY())) {
            view.showMessage("Please select a tile from your own farm!");
            return;
        }
        Tile tile = game.getGameMap().getTile(y, x);
        if (!tile.getItem().getDefinition().getId().equals(ItemIDs.VOID)) {
            view.showMessage("This tile is not empty!");
            return;
        }
        ItemDefinition building = Objects.requireNonNull(App.getItemDefinition(buildingName));
        Object ingredient = building.getAttribute(ItemAttributes.ingredients);
        if (ingredient instanceof Map<?, ?>) {
            Map<String, Object> ingredients = (Map<String, Object>) ingredient;
            for (Map.Entry<String, Object> entry : ingredients.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.equals("wood")) {
                    if (!inventory.hasItem(ItemIDs.wood, (int) value)) {
                        view.showMessage("You don't have enough wood!");
                        return;
                    }
                }
                if (key.equals("stone")) {
                    if (!inventory.hasItem(ItemIDs.stone, (int) value)) {
                        view.showMessage("You don't have enough stone!");
                        return;
                    }
                }
            }
        } else {
            view.showMessage("This building doesn't need any source to build!");
        }
        if (player.getCoin() < (int) building.getAttribute(ItemAttributes.shopPrice)) {
            view.showMessage("You don't have enough coin!");
            return;
        }
        ItemInstance item = new ItemInstance(building);
        tile.setItem(item);
        if (buildingName.contains("barn")) {
            playerMap.setBarn(new Barn(building, new Position(y, x)));
            player.setCoin(player.getCoin() - (int) building.getAttribute(ItemAttributes.shopPrice));
        } else if (buildingName.contains("coop")) {
            playerMap.setCoop(new Coop(building, new Position(y, x)));
            player.setCoin(player.getCoin() - (int) building.getAttribute(ItemAttributes.shopPrice));
        }

        view.showMessage("you've built " + buildingName + "!");
    }

    public static void buyAnimal(Matcher matcher, Game game) {

        String animalStr = matcher.group("animal").trim().toLowerCase();
        String name = matcher.group("name").trim().toLowerCase();
        Player player = game.getCurrentPlayer();
        PlayerMap playerMap = player.getPlayerMap();
        int yShop = NPCConst.ShopPositions.MarnieRanch.getY();
        int xShop = NPCConst.ShopPositions.MarnieRanch.getX();
        int playerY = player.getPosition().getY();
        int playerX = player.getPosition().getX();
        if (!(xShop - 1 <= playerX && playerX <= xShop + 1
                && yShop - 1 <= playerY && playerY <= yShop + 1)) {
            view.showMessage("You should be near Marine's Ranch to buy " + animalStr + "!");
            return;
        }
        boolean found = false;
        for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
            if (itemDefinition.getId().name().equals(animalStr) &&
                    (itemDefinition.getType().equals(ItemType.coop_animal))
                    || itemDefinition.getType().equals(ItemType.barn_animal)) {
                found = true;
            }
        }
        if (!found) {
            view.showMessage("You should enter an animal's name!");
            return;
        }
        for (Animal animal : player.getAnimals()) {
            if (animal.getName().equals(name)) {
                view.showMessage("You should enter an unique name for your animal!");
                return;
            }
        }
        ItemDefinition animalDef = Objects.requireNonNull(App.getItemDefinition(animalStr));
        if (player.getCoin() < (int) animalDef.getAttribute(ItemAttributes.price)) {
            view.showMessage("You don't have enough coin!");
            return;
        }

        if (animalDef.getType().equals(ItemType.coop_animal)) {
            if (!playerMap.hasCoop()) {
                view.showMessage("You haven't built a coop!");
                return;
            }
            player.setCoin(player.getCoin() - (int) animalDef.getAttribute(ItemAttributes.price));
            Animal animal = new Animal(animalDef, name, player, playerMap.getCoop().getPosition());
            playerMap.getCoop().setAnimal(animal);
            player.setAnimal(animal);
        } else if (animalDef.getType().equals(ItemType.barn_animal)) {
            if (!playerMap.hasBarn()) {
                view.showMessage("You haven't built a barn!");
                return;
            }
            player.setCoin(player.getCoin() - (int) animalDef.getAttribute(ItemAttributes.price));
            Animal animal = new Animal(animalDef, name, player, playerMap.getBarn().getPosition());
            playerMap.getBarn().setAnimal(animal);
            player.setAnimal(animal);
        }
    }

    public static void pet(Matcher matcher, Game game) {
        Player player = game.getCurrentPlayer();
        PlayerMap playerMap = player.getPlayerMap();
        String animalName = matcher.group("name").trim().toLowerCase();
        Animal animal = null;
        for (Animal animals : player.getAnimals()) {
            if (animals.getName().equals(animalName)) {
                animal = animals;
            }
        }
        if (animal == null) {
            view.showMessage("This animal does not exist!");
            return;
        }
        int animalY = animal.getPosition().getY();
        int animalX = animal.getPosition().getX();
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        if (!(animalX - 1 <= playerX && playerX <= animalX + 1
                && animalY - 1 <= playerY && playerY <= animalY + 1)) {
            view.showMessage("You should be near " + animalName + " to pet!");
            return;
        }
        animal.setPet(true);
        animal.increaseFriendShip(15);
        view.showMessage("You've pet" + animalName + "!");
    }

    public static void setAnimalFriendShip(Matcher matcher, Game game) {
        Player player = game.getCurrentPlayer();
        String animalName = matcher.group("animalName").trim().toLowerCase();
        String amountStr = matcher.group("amount");
        int amount;
        try {
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            view.showMessage("Please enter a valid amount!");
            return;
        }
        Animal animal = null;
        for (Animal animals : player.getAnimals()) {
            if (animals.getName().equals(animalName)) {
                animal = animals;
            }
        }
        if (animal == null) {
            view.showMessage("This animal does not exist!");
            return;
        }
        animal.setFriendShip(amount);
        view.showMessage("Your friendship has been set to " + amount + " with" + animalName + "!");
    }

    public static void showAnimals(Game game) {
        Player player = game.getCurrentPlayer();
        if (player.getAnimals().isEmpty()) {
            view.showMessage("You currently have no animal!");
            return;
        }
        for (Animal animal : player.getAnimals()) {
            view.showMessage("Name : " + animal.getName() + " friendship" + animal.getFriendShip()
                    + " isPet" + animal.isPet() + " isFed" + animal.isFed());
        }
    }

    public static void shepHerd(Matcher matcher, Game game) {
        String animalName = matcher.group("animalName").trim().toLowerCase();
        String xStr = matcher.group("x").trim();
        String yStr = matcher.group("y").trim();
        Player player = game.getCurrentPlayer();
        PlayerMap playerMap = player.getPlayerMap();
        int x, y;
        try {
            x = Integer.parseInt(xStr);
            y = Integer.parseInt(yStr);
        } catch (NumberFormatException e) {
            view.showMessage("Please enter a valid position!");
            return;
        }
        Animal animal = null;
        for (Animal playerAnimal : player.getAnimals()) {
            if (playerAnimal.getName().equals(animalName)) {
                animal = playerAnimal;
            }
        }
        if (animal == null) {
            view.showMessage("This animal does not exist!");
            return;
        }
        if (animal.getDefinition().getType().equals(ItemType.coop_animal)) {
            if (y == playerMap.getCoop().getPosition().getY() && x == playerMap.getCoop().getPosition().getX()) {
                if (animal.isOutside()) {
                    game.getGameMap().getTile(animal.getPosition().getX(), animal.getPosition().getY()).setItem(
                            new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
                    animal.setOutside(false);
                    animal.setPosition(new Position(playerMap.getCoop().getPosition().getY(),
                            playerMap.getCoop().getPosition().getX()));
                    view.showMessage(animalName + " is now inside the coop!");
                    return;
                } else {
                    view.showMessage(animalName + " is already inside the barn!");
                    return;
                }
            }
        } else if (animal.getDefinition().getType().equals(ItemType.barn_animal)) {
            if (y == playerMap.getBarn().getPosition().getY() && x == playerMap.getBarn().getPosition().getX()) {
                if (animal.isOutside()) {
                    game.getGameMap().getTile(animal.getPosition().getX(), animal.getPosition().getY()).setItem(
                            new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
                    animal.setOutside(false);
                    animal.setPosition(new Position(playerMap.getBarn().getPosition().getY(),
                            playerMap.getBarn().getPosition().getX()));
                    view.showMessage(animalName + " is now inside the barn!");
                    return;
                } else {
                    view.showMessage(animalName + " is already inside the barn!");
                    return;
                }
            }
        }
        Tile tile = game.getGameMap().getTile(y, x);
        if (!tile.getItem().getDefinition().getId().equals(ItemIDs.VOID)) {
            view.showMessage("You should put" + animalName + " on an empty tile!");
            return;
        }
        WeatherStates weatherStates = game.getWeather().getCurrentWeather();
        if (weatherStates.equals(WeatherStates.SNOW)
                || weatherStates.equals(WeatherStates.RAIN)
                || weatherStates.equals(WeatherStates.STORM)) {
            view.showMessage("Animals must stat inside, the weather is fucked up(" + weatherStates.name() + ")!");
            return;
        }
        tile.setItem(animal);
        animal.setFed(true);
        animal.setPosition(new Position(y, x));
        animal.setOutside(true);
        animal.increaseFriendShip(8);
    }

    public static void feedHay(Matcher matcher, Game game) {//TODO decrease yonjeh
        String animalName = matcher.group("animalName").trim().toLowerCase();
        Player player = game.getCurrentPlayer();
        Animal animal = null;
        for (Animal playerAnimal : player.getAnimals()) {
            if (playerAnimal.getName().equals(animalName)) {
                animal = playerAnimal;
            }
        }
        if (animal == null) {
            view.showMessage("This animal does not exist!");
            return;
        }
        if (!animal.isOutside()) {
            view.showMessage("This animal is outside!");
            return;
        }
        animal.setFed(true);
        view.showMessage("You've fed " + animalName + "!");
    }

    public static void animalProducts(Game game) {//TODO add product to each animal in game flow
        Player player = game.getCurrentPlayer();
        if (player.getAnimals().isEmpty()) {
            view.showMessage("You don't have any animal in your farm!");
            return;
        }
        for (Animal animal : player.getAnimals()) {
            for (Map.Entry<ItemInstance, Integer> entry : animal.getProducts().entrySet()) {
                ItemInstance item = entry.getKey();
                Integer quality = entry.getValue();
                view.showMessage("Animal Name : " + animal.getName() +
                        "Product Name : " + item.getDefinition().getDisplayName().toLowerCase()
                        + "Quality : " + quality);
            }
        }
    }

    public static void collectAnimalProduct(Matcher matcher, Game game) {
        Player player = game.getCurrentPlayer();
        String animalName = matcher.group("name").trim().toLowerCase();
        Animal animal = null;
        for (Animal animals : player.getAnimals()) {
            if (animals.getName().equals(animalName)) {
                animal = animals;
            }
        }
        if (animal == null) {
            view.showMessage("This animal does not exist!");
            return;
        }
        ItemIDs id = animal.getDefinition().getId();
        if (id.equals(ItemIDs.cow) || id.equals(ItemIDs.sheep) || id.equals(ItemIDs.goat)) {
            view.showMessage("You should use milk pale to collect cow, sheep and goat products!");
            return;
        }
        if (id.equals(ItemIDs.pig) && !animal.isOutside()) {
            view.showMessage("Pig should be outside to collect its products!");
            return;
        }
        for (Map.Entry<ItemInstance, Integer> entry : animal.getProducts().entrySet()) {
            ItemInstance item = entry.getKey();
            player.getInventory().addItem(item);
            view.showMessage("You've collected " + item.getDefinition().getDisplayName().toLowerCase() + " from "
                    + animalName + "!");
        }
    }

    public static void sellAnimal(Matcher matcher, Game game) {
        Player player = game.getCurrentPlayer();
        String animalName = matcher.group("name").trim().toLowerCase();
        PlayerMap playerMap = player.getPlayerMap();
        Animal animal = null;
        for (Animal animals : player.getAnimals()) {
            if (animals.getName().equals(animalName)) {
                animal = animals;
            }
        }
        if (animal == null) {
            view.showMessage("This animal does not exist!");
            return;
        }
        int price = (int) ((int) animal.getAttribute(ItemAttributes.price) * ((double) animal.getFriendShip() / 1000 + 0.3));
        player.increaseCoin(price);
        player.getAnimals().remove(animal);
        if (animal.getDefinition().getType().equals(ItemType.coop_animal)) {
            playerMap.getCoop().getAnimals().remove(animal);
        } else if (animal.getDefinition().getType().equals(ItemType.barn_animal)) {
            playerMap.getBarn().getAnimals().remove(animal);
        }
        view.showMessage("You've sold " + animalName + " for" + price + "g!");
    }
}
