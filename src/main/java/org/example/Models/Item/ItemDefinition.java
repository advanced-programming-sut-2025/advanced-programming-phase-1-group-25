package org.example.Models.Item;

import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemIDs;
import org.example.Enums.ItemConsts.ItemType;

import java.util.HashMap;
import java.util.Map;

/*
    Like template for each item.
 */
public class ItemDefinition {
    private final ItemType type;
    private final ItemIDs id;
    private final String displayName;
    private final Map<ItemAttributes, Object> baseAttributes;

    public ItemDefinition(ItemType type, ItemIDs id, String displayName, Map<ItemAttributes, Object> baseAttributes) {
        this.type = type;
        this.id = id;
        this.displayName = displayName;
        this.baseAttributes = baseAttributes;
    }

    public ItemIDs getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public Map<ItemAttributes, Object> getBaseAttributes() {
        return baseAttributes;
    }

    public String getDisplayName() {
        return displayName;
    }
    public int getEnergyCost() {
        return (int)baseAttributes.get(ItemAttributes.energyCost);
    }
    public int decreaseDurability() {
        int x = (int)baseAttributes.get(ItemAttributes.durability);
        baseAttributes.put(ItemAttributes.durability, x - 1);
        return x;

    }
}
