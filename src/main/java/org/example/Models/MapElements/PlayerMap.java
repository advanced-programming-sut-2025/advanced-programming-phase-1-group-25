package org.example.Models.MapElements;

public class PlayerMap {
    Tile[][] map;

    public PlayerMap(Tile[][] map) {
        this.map = map;
    }

    public Tile getTile(int y, int x) {
        for (Tile[] row : map) {
            for (Tile tile : row) {
                if (tile.getPosition().x == x &&
                        tile.getPosition().y == y) {
                    return tile;
                }
            }
        }
        return null;
    }
}
