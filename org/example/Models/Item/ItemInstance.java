package advanced.org.example.Models.Item;

import advanced.org.example.Enums.ItemConsts.ItemAttributes;

import java.util.HashMap;
import java.util.Map;

/*
    Every item in the game!
 */
public class ItemInstance {
    private final ItemDefinition definition;
    private final String uniqueId; // UUID is unique for each item in the game.
    private Map<ItemAttributes, Object> attributes; // like durability, level, ...

    public ItemInstance(ItemDefinition definition) {
        this.definition = definition;
        this.uniqueId = null; // a function will be implemented to generate UUID
        this.attributes = new HashMap<>();
    }


}
