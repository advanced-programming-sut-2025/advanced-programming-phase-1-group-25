package org.example.Models.MapElements;


import org.example.Models.App;
import org.example.Models.Item.ItemDefinition;

public class Cottage {
    private Tile tile;

    public Cottage(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }
}
