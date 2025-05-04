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
    private ItemLevels.BackPackLevels level;
    private Map<ItemDefinition, Integer> items;

    public Inventory() {
        this.level = ItemLevels.BackPackLevels.BASIC;
        this.items = new LinkedHashMap<>();
    }


    public void addItem(ItemDefinition item, int amount) {
        ItemDefinition target = findItem(item.getId().name());
        if (target == null) {
            this.items.put(item, amount);
        }
        int newAmount = items.get(target) + amount;
        this.items.put(target, newAmount);

    }

    public void dropItem(String id, int amount) {
        ItemDefinition target = findItem(id);
        if (target == null) return;
        int newAmount = Math.max(items.get(target) - amount, 0);
        if (newAmount == 0) {
            items.remove(target);
        } else {
            items.put(target, newAmount);
        }
    }

    public int getItemAmount(String id) {
        ItemDefinition target = findItem(id);
        if (target == null) return 0;
        return items.get(target);
    }

    public void upgrade(ItemLevels.BackPackLevels upgradedLevel) {
        this.level = upgradedLevel;
    }

    public ItemLevels.BackPackLevels getLevel() {
        return level;
    }

    private ItemDefinition findItem(String id) {
        ItemDefinition target = null;
        for (Map.Entry<ItemDefinition, Integer> entry : this.items.entrySet()) {
            ItemDefinition item = entry.getKey();
            if (item.getId().name().equals(id)) {
                target = item;
            }
        }
        return target;
    }

    public Map<ItemDefinition, Integer> getItems() {
        return items;
    }

    public int getCapacity() {
        return this.level.getLevel();
    }
}
