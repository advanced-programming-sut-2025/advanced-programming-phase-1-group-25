package org.example.Models.MapElements;

import org.example.Enums.MapConsts.AnsiColors;
import org.example.Models.Item.ItemInstance;

import java.util.ArrayList;

/*
    Game map is consisted of many tiles, each of which has a position and an item to represent.
 */
public class Tile {
    private final Position position;
    private ItemInstance item;
    private AnsiColors forGroundColor;
    private AnsiColors backGroundColor;
    private boolean isPlowed;
    private int dayPassedFromPlant;
    private boolean isFertilized;
    private boolean isWatered;
    private ArrayList<ItemInstance> fish;
    public Tile(Position position, ItemInstance item) {
        this.position = position;
        this.item = item;
        this.isPlowed = false;
        this.isWatered = false;
        fish = new ArrayList<>();
    }

    public boolean getPlowed() {
        return isPlowed;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
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

    public boolean isEmpty() {
        return this.item.getDefinition().getId().name().equals("VOID");
    }

    public void strikeLightning() {
        //TODO: implement Lightning logic
    }

    public void setDayPassedFromPlant(int dayPassedFromPlant) {
        this.dayPassedFromPlant = dayPassedFromPlant;
    }

    public int getDayPassedFromPlant() {
        return dayPassedFromPlant;
    }

    public void fertilize() {
        this.isFertilized = true;
    }

    public void setFertilized(boolean fertilized) {
        isFertilized = fertilized;
    }

    public boolean isWatered() {
        return isWatered;
    }

    public void setWatered(boolean watered) {
        this.isWatered = watered;
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public boolean isFertilized() {
        return isFertilized;
    }

}
