package org.example.Models.Item;

import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemIDs;
import org.example.Enums.ItemConsts.ItemLevels;
import org.example.Enums.ItemConsts.Level;

import java.util.*;
import java.util.prefs.BackingStoreException;
import java.util.regex.Matcher;

/*
    This is the inventory, and each player has his (or her!) own inventory.
 */
public class Inventory {
    private Level level;
    private Map<ItemInstance, Integer> items;
    public Inventory() {
        this.level = ItemLevels.BackPackLevels.BASIC;
        this.items = new LinkedHashMap<>();
    }

    public void addItem(String id, int amount) {
        ItemInstance target = findItem(id);
        if (target == null) return;
        int newAmount = items.get(target) + amount;
        this.items.put(target, newAmount);
    }

    public void dropItem(String id, int amount) {
        ItemInstance target = findItem(id);
        if (target == null) return;
        int newAmount = Math.max(items.get(target) - amount, 0);
        if (newAmount == 0) {
            items.remove(target);
        } else {
            items.put(target, newAmount);
        }
    }

    public int getItemAmount(String id) {
        ItemInstance target = findItem(id);
        if (target == null) return 0;
        return items.get(target);
    }

    public void upgrade(Level upgradedLevel) {
        this.level = upgradedLevel;
    }

    private ItemInstance findItem(String id) {
        ItemInstance target = null;
        for (Map.Entry<ItemInstance, Integer> entry : this.items.entrySet()) {
            ItemInstance item = entry.getKey();
            if (item.getDefinition().getId().name().equals(id)) {
                target = item;
            }
        }
        return target;
    }
}
