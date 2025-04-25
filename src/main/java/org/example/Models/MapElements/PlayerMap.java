package org.example.Models.MapElements;

public class PlayerMap {
    Tile[][] map;

    public PlayerMap(Tile[][] map) {
        this.map = map;
    }

    public Tile getTile(int y, int x) {
        return map[y][x];
    }
}
