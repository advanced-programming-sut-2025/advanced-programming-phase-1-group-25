package org.example.Models.MapElements;

import org.example.Enums.MapConsts.MapSizes;

public class GameMap {
    private Tile[][] map;

    public GameMap() {
        this.map = new Tile[MapSizes.MAP_ROWS.getSize()][MapSizes.MAP_COLS.getSize()];
    }

    public void getMap() {

    }

    public void addTile(Tile tile) {
        map[tile.getPosition().getY()][tile.getPosition().getX()] = tile;
    }
    public Tile getTile(int y, int x) {
        return map[y][x];
    }

}
