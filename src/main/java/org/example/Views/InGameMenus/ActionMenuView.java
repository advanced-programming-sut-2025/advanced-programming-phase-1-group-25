package org.example.Views.InGameMenus;

import org.example.Controllers.InGameMenuController.ActionMenuController;
import org.example.Controllers.InGameMenuController.MenuSwitcher;
import org.example.Enums.InGameMenuCommands.ActionMenuCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ActionMenuView implements AppMenu {
    Scanner scanner;
    ActionMenuController controller;

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
        this.controller = new ActionMenuController(this);
        switch (command) {
            case SWITCH_MENU:
                System.out.println(MenuSwitcher.printMenus());
                controller.changeMenu();
                break;
            case NEXT_TURN:
                controller.nextTurn();
                break;
            case TIME:
                System.out.println(game.getDateTime().getHour());
                break;
            case DATE:
                System.out.println(game.getDateTime().getSeason().toString()
                        + " " + game.getDateTime().getDay());
                break;
            case DATE_TIME:
                System.out.println(game.getDateTime().getSeason().toString()
                        + " " + game.getDateTime().getDay()
                        + " " + game.getDateTime().getHour());
                break;
            case DAY_OF_THE_WEEK:
                System.out.println(game.getDateTime().getDayOfWeek().toString());
                break;
            case CHEAT_ADVANCE_TIME:
                controller.cheatAdvanceTime(matcher, game);
                break;
            case CHEAT_ADVANCE_DATE:
                controller.cheatAdvanceDate(matcher, game);
                break;
            case SEASON:
                System.out.println(game.getDateTime().getSeason().toString());
                break;
            case CHEAT_THOR:
                break;
            case WEATHER:
                System.out.println(game.getWeather().getCurrentWeather().toString());
                break;
            case WEATHER_FORECAST:
                break;
            case CHEAT_WEATHER_SET:
                break;
            case GREENHOUSE_BUILD:
                break;
            case WALK:
                controller.walk(matcher.group("y"), matcher.group("x"));
                break;
            case PRINT_MAP:
                controller.printMap(matcher.group("x"), matcher.group("y"), matcher.group("size"));
                break;
            case HELP_READING_MAP:
                break;
            case ENERGY_SHOW:
                System.out.println("Energy: " + game.getCurrentPlayer().getEnergy());
                break;
            case ENERGY_SET:
                controller.cheatSetEnergy(matcher, game);
                break;
            case ENERGY_UNLIMITED:
                controller.energyUnlimited(game);
                break;
            case TOOLS_EQUIP:
                controller.equipTool(matcher);
                break;
            case TOOLS_SHOW_CURRENT:
                controller.showCurrentTool();
                break;
            case TOOLS_SHOW_AVAILABLE:
                controller.showInventoryTools();
                break;
            case TOOLS_UPGRADE:
                break;
            case TOOLS_USE:
                controller.useTool(matcher);
                break;
            case CRAFT_INFO:
                controller.craftInfo(matcher);
                break;
            case PLANT:
                break;
            case SHOW_PLANT:
                break;
            case FERTILIZE:
                break;
            case HOW_MUCH_WATER:
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
                break;
            case BUY_ANIMAL:
                break;
            case PET:
                break;
            case ANIMALS:
                break;
            case SHEPHERD_ANIMALS:
                break;
            case FEED_HAY:
                break;
            case CHEAT_SET_FRIENDSHIP:
                break;
            case PRODUCES:
                break;
            case COLLECT_PRODUCE:
                break;
            case SELL_ANIMAL:
                break;
            case FISHING:
                break;
            case TRADE:
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
