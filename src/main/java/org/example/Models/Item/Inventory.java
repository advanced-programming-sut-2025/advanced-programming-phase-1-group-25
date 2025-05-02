package org.example.Models.Item;

import org.example.Enums.ItemConsts.ItemLevels;

import java.util.*;

/*
    This is the inventory, and each player has his (or her!) own inventory.
 */
public class Inventory {
    private ItemLevels.BackPackLevels level;
    private Map<ItemInstance, Integer> items;

    public Inventory() {
        this.level = ItemLevels.BackPackLevels.BASIC;
        this.items = new LinkedHashMap<>();
    }

    public void addItem(String itemName, int amount) {
        ItemInstance target = findItemByName(itemName);
        if (target == null) return;
        int newAmount = items.get(target) + amount;
        this.items.put(target, newAmount);
    }

    public void addNewItem(ItemInstance instance) {
        this.items.put(instance, 1);
    }

    public void dropItem(String id, int amount) {
        ItemInstance target = findItemById(id);
        if (target == null) return;
        int newAmount = Math.max(items.get(target) - amount, 0);
        if (newAmount == 0) {
            items.remove(target);
        } else {
            items.put(target, newAmount);
        }
    }

    public String dropItemByName(String name, int amount) {
        ItemInstance target = findItemByName(name);
        if (target == null) return "You don't have " + name + " in your backpack.";
        int newAmount = Math.max(items.get(target) - amount, 0);
        if (newAmount == 0) {
            items.remove(target);
        } else {
            items.put(target, newAmount);
        }
        return "You dropped " + amount + " of " + name + ".";
    }

    public int getItemAmount(String id) {
        ItemInstance target = findItemById(id);
        if (target == null) return 0;
        return items.get(target);
    }

    public void upgrade(ItemLevels.BackPackLevels upgradedLevel) {
        this.level = upgradedLevel;
    }

    private ItemInstance findItemById(String id) {
        ItemInstance target = null;
        for (Map.Entry<ItemInstance, Integer> entry : this.items.entrySet()) {
            ItemInstance item = entry.getKey();
            if (item.getDefinition().getId().name().equals(id)) {
                target = item;
            }
        }
        return target;
    }

    public ItemInstance findItemByName(String name) {
        ItemInstance target = null;
        for (Map.Entry<ItemInstance, Integer> entry : this.items.entrySet()) {
            ItemInstance item = entry.getKey();
            if (item.getDefinition().getDisplayName().equals(name)) {
                target = item;
            }
        }
        return target;
    }

    public Map<ItemInstance, Integer> getItems() {
        return items;
    }

    public int getCapacity() {
        if (this.level.equals(ItemLevels.BackPackLevels.BASIC)) {
            return 12;
        } else if (this.level.equals(ItemLevels.BackPackLevels.DELUXE)) {
            return 24;
        } else {
            return 1000000000;
        }

    }
}
