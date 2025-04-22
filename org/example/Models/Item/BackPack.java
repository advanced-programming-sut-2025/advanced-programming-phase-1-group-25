package org.example.Models.Item;

import org.example.Enums.ItemConsts.ItemLevels;

import java.util.ArrayList;

/*
    This is the inventory, and each player has his (or her!) own inventory.
 */
public class BackPack {
    ItemLevels level;
    ArrayList<InventoryItem> items;
}
