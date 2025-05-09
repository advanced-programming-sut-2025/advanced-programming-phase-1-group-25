package org.example.Controllers.UpdateMap;

import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.States.DateTime;

import java.util.ArrayList;

public class ArtisanUpdate {

    public static void artisanWithHour(int hourPassed) {
        Inventory inventory = App.getCurrentGame().getCurrentPlayer().getInventory();
        ArrayList<ItemInstance> artisans = inventory.getArtisan();
        for (ItemInstance artisan : artisans) {
            int hour = (int) artisan.getAttribute(ItemAttributes.hour);
            artisan.setAttribute(ItemAttributes.hour, hour - hourPassed);
            if (hour - hourPassed <= 0) {
                inventory.addItem(artisan);
                artisan.setAttribute(ItemAttributes.isReady, true);
                inventory.getArtisan().remove(artisan);
            }
        }


    }

    public static void artisanWithDay(int dayPassed) {
        Inventory inventory = App.getCurrentGame().getCurrentPlayer().getInventory();
        ArrayList<ItemInstance> artisans = inventory.getArtisan();
        for (ItemInstance artisan : artisans) {
            int day = (int) artisan.getAttribute(ItemAttributes.day);
            artisan.setAttribute(ItemAttributes.day, day - dayPassed);
            if (day - dayPassed <= 0) {
                inventory.addItem(artisan);
                artisan.setAttribute(ItemAttributes.isReady, true);
                inventory.getArtisan().remove(artisan);
            }
        }
    }

    public static void artisanNextMorning() {

    }
}
