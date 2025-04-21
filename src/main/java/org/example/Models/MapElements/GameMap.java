package org.example.Models.MapElements;

import java.util.ArrayList;

public class GameMap {
    private Tile[][] map;

    public void getMap() {

    }

    public void addTile(Tile tile) {
        map[tile.getPosition().getY()][tile.getPosition().getX()] = tile;
    }
}
