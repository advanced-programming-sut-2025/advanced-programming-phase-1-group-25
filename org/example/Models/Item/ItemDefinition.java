package advanced.org.example.Models.Item;

import advanced.org.example.Enums.ItemConsts.ItemAttributes;
import advanced.org.example.Enums.ItemConsts.ItemIDs;
import advanced.org.example.Enums.ItemConsts.ItemType;

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

    public ItemDefinition(ItemType type, ItemIDs id, String displayName) {
        this.type = type;
        this.id = id;
        this.displayName = displayName;
        this.baseAttributes = new HashMap<>();
        // ...
    }
}
