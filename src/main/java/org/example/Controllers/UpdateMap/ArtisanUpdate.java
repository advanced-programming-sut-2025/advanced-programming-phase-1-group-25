package org.example.Controllers.UpdateMap;

import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.States.DateTime;
import org.example.Views.InGameMenus.ActionMenuView;

import java.util.ArrayList;

public class ArtisanUpdate {
    static ActionMenuView view = new ActionMenuView();

    public static void artisanWithHour(int hourPassed) {
        Inventory inventory = App.getCurrentGame().getCurrentPlayer().getInventory();
        ArrayList<ItemInstance> artisans = inventory.getArtisan();
        for (ItemInstance artisan : artisans) {
            int hour = (int) artisan.getAttribute(ItemAttributes.hour);
            artisan.setAttribute(ItemAttributes.hour, hour - hourPassed);
            if (hour - hourPassed <= 0) {
                view.showMessage(artisan.getDefinition().getId() + " is now ready! ");
                artisan.setAttribute(ItemAttributes.isReady, true);
            }
        }


    }

    public static void artisanWithDay(int dayPassed) {
        Inventory inventory = App.getCurrentGame().getCurrentPlayer().getInventory();
        ArrayList<ItemInstance> artisans = inventory.getArtisan();
        for (ItemInstance artisan : artisans) {
            int day = (int) artisan.getAttribute(ItemAttributes.day);
            artisan.setAttribute(ItemAttributes.day, day - dayPassed);
            System.out.println(day + " " + dayPassed);
            if (day - dayPassed <= 0
            && !((boolean) artisan.getAttribute(ItemAttributes.isReady))) {
                view.showMessage(artisan.getDefinition().getId() + " is now ready! ");
                artisan.setAttribute(ItemAttributes.isReady, true);
                inventory.addItem(artisan);
            }
        }
    }

    public static void artisanNextMorning() {

    }
}
