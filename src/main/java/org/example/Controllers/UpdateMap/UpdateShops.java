package org.example.Controllers.UpdateMap;

import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.MapElements.Shop;

import java.util.ArrayList;
import java.util.Map;

public class UpdateShops {
    public static void updateShops() {
        Game game = App.getCurrentGame();
        ArrayList<Shop> shops = game.getShops();
        for (Shop shop : shops) {
            Map<ItemDefinition, Integer> shopsItems = shop.getShopItems();
            for (Map.Entry<ItemDefinition, Integer> entry : shopsItems.entrySet()) {
                ItemDefinition item = entry.getKey();
                int dailyLimit = (int) item.getAttribute(ItemAttributes.dailyLimit);
                shopsItems.put(item, dailyLimit);
            }
        }
    }
}
