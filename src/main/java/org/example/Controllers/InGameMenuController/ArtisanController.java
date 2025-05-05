package org.example.Controllers.InGameMenuController;

import org.example.Models.App;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Player.Player;
import org.example.Views.InGameMenus.ActionMenuView;

public class ArtisanController {
    static ActionMenuView view = new ActionMenuView();

    public static void beeHouse(String itemName, Player player) {
        ItemDefinition item = App.getItemDefinitionByName(itemName);
        switch (itemName) {
            case "honey":

                break;
            default:
                view.showMessage("please enter a correct item!");
                break;
        }
    }

    public static void cheesePress(String itemName) {
        switch (itemName) {
            case "cheese":
                break;
            case "goat cheese":
                break;
            default:
                view.showMessage("please enter a correct item!");
                break;
        }
    }

    public static void keg(String itemName) {
        switch (itemName) {
            case "beer":
                break;
            case "vinegar":
                break;
            case "coffee":
                break;
            case "juice":
                break;
            case "mead":
                break;
            case "pale ale":
                break;
            case "wine":
                break;
            default:
                view.showMessage("please enter a correct item!");
                break;
        }
    }

    public static void dehydrator(String itemName) {
        switch (itemName) {
            case "dried mushroom":
                break;
            case "dried fruit":
                break;
            case "raisins":
                break;
            default:
                view.showMessage("please enter a correct item!");
                break;
        }
    }

    public static void charcoalKiln(String itemName) {
        switch (itemName) {
            case "coal":
                break;
            default:
                view.showMessage("please enter a correct item!");
                break;
        }
    }

    public static void loom(String itemName) {
        switch (itemName) {
            case "cloth":
                break;
            default:
                view.showMessage("please enter a correct item!");
                break;
        }
    }

    public static void mayonnaiseMachine(String itemName) {
        switch (itemName) {
            case "mayonnaise":
                break;
            case "duck mayonnaise":
                break;
            case "dinosaur mayonnaise":
                break;
            default:
                view.showMessage("please enter a correct item!");
                break;
        }
    }

    public static void oilMaker(String itemName) {
        switch (itemName) {
            case "truffle oil":
                break;
            case "oil":
                break;
            default:
                view.showMessage("please enter a correct item!");
                break;
        }
    }

    public static void preservesJar(String itemName) {
        switch (itemName) {
            case "pickles":
                break;
            case "jelly":
                break;
            default:
                view.showMessage("please enter a correct item!");
                break;
        }
    }

    public static void fishSmoker(String itemName) {
        switch (itemName) {
            case "smoked fish":
                break;
            default:
                view.showMessage("please enter a correct item!");
                break;
        }
    }

    public static void furnace(String itemName) {

    }
}
