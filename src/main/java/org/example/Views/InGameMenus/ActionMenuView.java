package org.example.Views.InGameMenus;

import org.example.Controllers.InGameMenuController.ActionMenuController;
import org.example.Controllers.InGameMenuController.AnimalController;
import org.example.Controllers.InGameMenuController.FarmingController;
import org.example.Controllers.InGameMenuController.MenuSwitcher;
import org.example.Enums.InGameMenuCommands.ActionMenuCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ActionMenuView implements AppMenu {
    Scanner scanner;
    ActionMenuController actionController;
    FarmingController farmingController;
    AnimalController animalController;
    @Override
    public void handleInput(Scanner sc) {
        this.scanner = sc;
        String input = scanner.nextLine();
        Matcher matcher;
        boolean matched = false;
        for (ActionMenuCommands command : ActionMenuCommands.values()) {
            if ((matcher = command.getMatcher(input)) != null) {
                matched = true;
                executeCommand(command, matcher, input);
            }
        }
        if (!matched) {
            System.out.printf("Invalid command. please try again.\n");
        }
    }

    private void executeCommand(ActionMenuCommands command, Matcher matcher, String input) {
        Game game = App.getCurrentGame();
        this.actionController = new ActionMenuController(this);
        this.farmingController = new FarmingController(this);
        this.animalController = new AnimalController(this);
        switch (command) {
            case SWITCH_MENU:
                System.out.println(MenuSwitcher.printMenus());
                actionController.changeMenu();
                break;
            case NEXT_TURN:
                actionController.nextTurn();
                break;
            case TIME:
                System.out.println(game.getDateTime().getHour());
                break;
            case DATE:
                System.out.println("season: " + game.getDateTime().getSeason().toString().toLowerCase()
                        + "\nday: " + game.getDateTime().getDay());
                break;
            case DATE_TIME:
                System.out.println("season: " + game.getDateTime().getSeason().toString().toLowerCase()
                        + "\nday: " + game.getDateTime().getDay()
                        + "\nday of week: " + game.getDateTime().getDayOfWeek().name().toLowerCase()
                        + "\nhour: " + game.getDateTime().getHour());
                break;
            case DAY_OF_THE_WEEK:
                System.out.println(game.getDateTime().getDayOfWeek().toString().toLowerCase());
                break;
            case CHEAT_ADVANCE_TIME:
                actionController.cheatAdvanceTime(matcher, game);
                break;
            case CHEAT_ADVANCE_DATE:
                actionController.cheatAdvanceDate(matcher, game);
                break;
            case SEASON:
                System.out.println(game.getDateTime().getSeason().toString().toLowerCase());
                break;
            case CHEAT_THOR:
                break;
            case WEATHER:
                System.out.println(game.getWeather().getCurrentWeather().toString().toLowerCase());
                break;
            case WEATHER_FORECAST:
                break;
            case CHEAT_WEATHER_SET:
                break;
            case GREENHOUSE_BUILD:
                actionController.buildGreenhouse();
                break;
            case WALK:
                actionController.walk(matcher.group("y"), matcher.group("x"));
                break;
            case PRINT_MAP:
                actionController.printMap(matcher.group("x"), matcher.group("y"), matcher.group("size"));
                break;
            case HELP_READING_MAP:

                break;
            case ENERGY_SHOW:
                System.out.println("Energy: " + game.getCurrentPlayer().getEnergy());
                break;
            case ENERGY_SET:
                actionController.cheatSetEnergy(matcher, game);
                break;
            case ENERGY_UNLIMITED:
                actionController.energyUnlimited(game);
                break;
            case TOOLS_EQUIP:
                actionController.equipTool(matcher);
                break;
            case TOOLS_SHOW_CURRENT:
                actionController.showCurrentTool();
                break;
            case TOOLS_SHOW_AVAILABLE:
                actionController.showInventoryTools();
                break;
            case TOOLS_UPGRADE:
                break;
            case TOOLS_USE:
                actionController.useTool(matcher);
                break;
            case CRAFT_INFO:
                actionController.craftInfo(matcher);
                break;
            case PLANT:
                farmingController.plant(matcher.group("seed"), matcher.group("direction"));
                break;
            case SHOW_PLANT:
                farmingController.showPlant(matcher.group("y"), matcher.group("x"));
                break;
            case FERTILIZE:
                farmingController.fertilize(matcher.group("fertilizer"), matcher.group("direction"));
                break;
            case HOW_MUCH_WATER:
                farmingController.howMuchWater();
                break;
            case CRAFTING_SHOW_RECIPES:
                break;
            case CRAFTING_CRAFT:
                break;
            case PLACE_ITEM:
                break;
            case CHEAT_ADD_ITEM:
                break;
            case COOKING_REFRIGERATOR:
                break;
            case COOKING_SHOW_RECIPES:
                break;
            case COOKING_PREPARE:
                break;
            case EAT:
                break;
            case BUILD:
                animalController.buildBarnOrCoop(matcher, game);
                break;
            case BUY_ANIMAL:
                animalController.buyAnimal(matcher, game);
                break;
            case PET:
                animalController.pet(matcher, game);
                break;
            case ANIMALS:
                animalController.showAnimals(game);
                break;
            case SHEPHERD_ANIMALS:
                animalController.shepHerd(matcher, game);
                break;
            case FEED_HAY:
                animalController.feedHay(matcher, game);
                break;
            case CHEAT_SET_FRIENDSHIP:
                animalController.setAnimalFriendShip(matcher, game);
                break;
            case PRODUCES:
                animalController.animalProducts(game);
                break;
            case COLLECT_PRODUCE:
                animalController.collectAnimalProduct(matcher, game);
                break;
            case SELL_ANIMAL:
                animalController.sellAnimal(matcher, game);
                break;
            case FISHING:
                animalController.fishing(matcher, game);
                break;
            case TRADE:
                break;
            case ARTISAN_USE:
                actionController.artisanUse(matcher, game, input);
                break;
            case ARTISAN_GET:
                actionController.artisanGet(matcher, game);
                break;

        }
    }

    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
