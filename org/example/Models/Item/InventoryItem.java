package org.example.Models.Item;

/*
    Inventory items, are the items that are collected or in the inventory from the beginning.
 */
public class InventoryItem extends ItemInstance {
    int amountInInventory;

    public InventoryItem(ItemDefinition definition, int amountInInventory) {
        super(definition);
        this.amountInInventory = amountInInventory;
    }
}
