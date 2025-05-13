package org.example.Models.Item;

import org.example.Enums.ItemConsts.*;
import org.example.Models.App;
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
    private ArrayList<ItemInstance> artisan;

    public Inventory() {
        this.level = ItemLevels.BackPackLevels.BASIC;
        this.items = new LinkedHashMap<>();
        this.artisan = new ArrayList<>();
        bagaDadanInventoryPlus();
    }

    public ArrayList<ItemInstance> getArtisan() {
        return artisan;
    }

    public void setArtisan(ItemInstance artisan) {
        this.artisan.add(artisan);
    }

    public boolean addItem(ItemInstance item) {
        ItemIDs id = item.getDefinition().getId();
        if (id == null) {
            return false;
        }
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                ArrayList<ItemInstance> itemInstances = entry.getValue();
                itemInstances.add(item);
                return true;
            }
        }
//        if(!hasCapacity()) return false;
        ArrayList<ItemInstance> newItemsList = new ArrayList<>();
        newItemsList.add(item);
        this.items.put(id, newItemsList);
        return true;
    }

    public void trashItem(ItemIDs id, int amount) {
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                ArrayList<ItemInstance> itemList = this.items.get(id);
                for (int i = 0; i < Math.min(amount, itemList.size()); i++) {
                    itemList.remove(itemList.size() - 1);
                }
                if (itemList.isEmpty()) {
                    this.items.remove(entry.getKey());
                }
                return;
            }
        }
    }

    public void trashItemAll(ItemIDs id) {
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                this.items.remove(id);
                this.items.remove(entry.getKey());
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

    public boolean hasItem(ItemIDs id, int amount) {
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id && entry.getValue().size() >= amount) {
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
    public ItemInstance useItem(ItemIDs id) {
        ItemInstance target = null;
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                ArrayList<ItemInstance> itemList = this.items.get(id);
                target = itemList.get(itemList.size() - 1);
            }
        }
        return target;
    }

    public ArrayList<ItemInstance> getItem(ItemIDs id, int amount) {
        ArrayList<ItemInstance> items = new ArrayList<>();
        for (Map.Entry<ItemIDs, ArrayList<ItemInstance>> entry : this.items.entrySet()) {
            if (entry.getKey() == id) {
                ArrayList<ItemInstance> itemList = this.items.get(id);
                for (int i = 0; i < Math.min(itemList.size(), amount); i++) {
                    ItemInstance target = itemList.get(itemList.size() - 1);
                    itemList.remove(itemList.size() - 1);
                    itemList.add(target);
                }
            }
        }
        return items;
    }

    public int getCapacity() {
        return this.level.getLevel();
    }


    public Map<ItemIDs, ArrayList<ItemInstance>> getItems() {
        return items;
    }

    public boolean hasCapacity() {
        return switch (this.level) {
            case BASIC -> this.items.size() < 20;
            case BIG -> this.items.size() < 32;
            default -> true;
        };
    }

    public void setInventoryTools () {
        addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("base_hoe"))));
        addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("base_pickaxe"))));
        addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("base_axe"))));
        addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("base_watering_can"))));
        addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("training_fishing_pole"))));
        addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("scythe"))));
        addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("milk_pale"))));
        addItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("shear"))));
    }

        public void bagaDadanInventoryPlus () {
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
            for (ItemDefinition itemDefinition : App.getItemDefinitions()) {
                this.addItem(new ItemInstance(itemDefinition));
            }
        }
}
