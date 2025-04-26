package org.example.Models.MapElements;

import org.example.Enums.MapConsts.AnsiColors;
import org.example.Models.Item.ItemInstance;

/*
    Game map is consisted of many tiles, each of which has a position and an item to represent.
 */
public class Tile {
    private final Position position;
    private ItemInstance item;
    private AnsiColors forGroundColor;
    private AnsiColors backGroundColor;

    public Tile(Position position, ItemInstance item) {
        this.position = position;
        this.item = item;
    }

    public void setItem(ItemInstance item) {
        this.item = item;
    }

    public ItemInstance getItem() {
        return item;
    }
    public Position getPosition() {
        return position;
    }

    public void setBackGroundColor(AnsiColors backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public void setForGroundColor(AnsiColors forGroundColor) {
        this.forGroundColor = forGroundColor;
    }

    public AnsiColors getBackGroundColor() {
        return backGroundColor;
    }

    public AnsiColors getForGroundColor() {
        return forGroundColor;
    }
}
