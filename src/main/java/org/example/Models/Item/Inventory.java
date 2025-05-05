package org.example.Models.Item;

import org.example.Enums.ItemConsts.*;
import org.example.Models.Player.Player;

import java.util.*;
import java.util.prefs.BackingStoreException;
import java.util.regex.Matcher;

/*
    This is the inventory, and each player has his (or her!) own inventory.
 */
public class Inventory {
    private ItemLevels.BackPackLevels level;
    private Map<ItemIDs, ArrayList<ItemInstance>> items;

    public Inventory() {
        this.level = ItemLevels.BackPackLevels.BASIC;
        this.items = new LinkedHashMap<>();
    }


    public void addItem(ItemInstance item) {
        ItemIDs id = item.getDefinition().getId();
        if (id == null) {
            return;
        }
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                ArrayList<ItemInstance> itemInstances = entry.getValue();
                itemInstances.add(item);
                return;
            }
        }
        ArrayList<ItemInstance> newItemsList = new ArrayList<>();
        newItemsList.add(item);
        this.items.put(id, newItemsList);
    }

    public void trashItem(ItemIDs id, int amount) {
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                ArrayList<ItemInstance> itemList = this.items.get(id);
                for (int i = 0; i < Math.min(amount, itemList.size()); i++) {
                    itemList.remove(itemList.size() - 1);
                }
                return;
            }
        }
    }

    public void trashItemAll(ItemIDs id) {
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                this.items.remove(id);
            }
        }
    }

    public boolean hasItem(ItemIDs id) {
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                return true;
            }
        }
        return false;
    }
    public int getItemAmount(ItemIDs id) {
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                return this.items.get(id).size();
            }
        }
        return 0;
    }

    public void upgrade(ItemLevels.BackPackLevels upgradedLevel) {
        this.level = upgradedLevel;
    }

    public ItemLevels.BackPackLevels getLevel() {
        return level;
    }

    public ItemInstance getItem(ItemIDs id) {
        ItemInstance target = null;
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                ArrayList<ItemInstance> itemList = this.items.get(id);
                target = itemList.get(itemList.size() - 1);
                itemList.remove(itemList.size() - 1);
            }
        }
        return target;
    }

    public int getCapacity() {
        return this.level.getLevel();
    }

    public Map<ItemIDs, ArrayList<ItemInstance>> getItems() {
        return items;
    }
}
