package org.example.Views.InGameMenus;

import org.example.Controllers.InGameMenuController.ActionMenuController;
import org.example.Controllers.InGameMenuController.MenuSwitcher;
import org.example.Controllers.PreGameMenuController.SignupMenuController;
import org.example.Enums.InGameMenuCommands.ActionMenuCommands;
import org.example.Enums.PreGameMenuCommands.SignupMenuCommands;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Views.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ActionMenu implements AppMenu {
    @Override
    public void handleInput(Scanner sc) {
        String input = sc.nextLine();
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

    private static void executeCommand(ActionMenuCommands command, Matcher matcher, String input) {
        Game game = App.getCurrentGame();
        ActionMenuController controller = new ActionMenuController();
        switch (command) {
            case SWITCH_MENU:
                System.out.println(MenuSwitcher.printMenus());
                controller.changeMenu();
                break;
            case NEXT_TURN:
                System.out.println(controller.nextTurn());
                break;
            case TIME:
                System.out.println(game.getDateTime().getHour());
                break;
            case DATE:
                System.out.println(game.getDateTime().getSeason().toString()
                        +" "+game.getDateTime().getDay());
                break;
            case DATE_TIME:
                System.out.println(game.getDateTime().getSeason().toString()
                        +" "+ game.getDateTime().getDay()
                        + " " + game.getDateTime().getHour());
                break;
            case DAY_OF_THE_WEEK:
                System.out.println(game.getDateTime().getDayOfWeek().toString());
                break;
            case CHEAT_ADVANCE_TIME:
                System.out.println(controller.cheatAdvanceTime(matcher, game));
                break;
            case CHEAT_ADVANCE_DATE:
                System.out.println(controller.cheatAdvanceDate(matcher, game));
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
//                System.out.println(controller.walk(matcher));
                break;
            case PRINT_MAP:
                System.out.println(controller.printMap(game, matcher));
                break;
            case HELP_READING_MAP:
                break;
            case ENERGY_SHOW:
                System.out.println("Energy: " + game.getCurrentPlayer().getEnergy());
                break;
            case ENERGY_SET:
                System.out.println(controller.cheatSetEnergy(matcher, game));
                break;
            case ENERGY_UNLIMITED:
                System.out.println(controller.energyUnlimited(game));
                break;
            case TOOLS_EQUIP:
                System.out.println(controller.equipTool(matcher));
                break;
            case TOOLS_SHOW_CURRENT:
                System.out.println(controller.showCurrentTool());
                break;
            case TOOLS_SHOW_AVAILABLE:
                System.out.println(controller.showInventoryTools());
                break;
            case TOOLS_UPGRADE:
                break;
            case TOOLS_USE:
                break;
            case CRAFT_INFO:
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
}
