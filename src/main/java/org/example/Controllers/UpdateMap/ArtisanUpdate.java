package org.example.Controllers.UpdateMap;

import org.example.Enums.ItemConsts.ItemType;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.States.DateTime;

import java.util.ArrayList;

public class ArtisanUpdate {
    public static void handleArtisanUpdate(Game game) {
        DateTime dateTime = game.getDateTime();

    }

    public static void artisanWithHour() {
        ArrayList<ItemDefinition> artisans = new ArrayList<>();
        for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
            if(itemDefinition.getType().equals(ItemType.artisan)) {
                artisans.add(itemDefinition);
            }
        }

    }

    public static void artisanWithDay() {
        ArrayList<ItemDefinition> artisans = new ArrayList<>();
        for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
            if(itemDefinition.getType().equals(ItemType.artisan)) {
                artisans.add(itemDefinition);
            }
        }
    }

    public static void artisanNextMorning() {
        ArrayList<ItemDefinition> artisans = new ArrayList<>();
        for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
            if(itemDefinition.getType().equals(ItemType.artisan)) {
                artisans.add(itemDefinition);
            }
        }
    }
}
