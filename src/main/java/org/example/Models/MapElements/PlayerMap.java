package org.example.Models.MapElements;

import com.fasterxml.jackson.databind.type.ClassKey;

import javax.management.Query;
import java.awt.image.AreaAveragingScaleFilter;

public class PlayerMap {
    Tile[][] map;
    GreenHouse greenHouse;
    Cottage cottage;
    Quarry[] quarries;
    Lake[] lakes;

    public PlayerMap(Tile[][] map
                     , Tile greenHouseTile
                     , Tile cottageTile
                     , Tile[] lakeTiles
                     , Tile[] quarryTiles) {
            this.map = map;
            // make farm greenhouse
            this.greenHouse = new GreenHouse(greenHouseTile);
            // make farm cottage
            this.cottage = new Cottage(cottageTile);
            // make farm quarries
            try {
                this.quarries = new Quarry[2];
                this.quarries[0] = new Quarry(quarryTiles[0]);
                this.quarries[1] = new Quarry(quarryTiles[1]);
            } catch (Exception e) {

            }
            // make farm lakes
            try {
                this.lakes = new Lake[2];
                this.lakes[0] = new Lake(lakeTiles[0]);
                this.lakes[1] = new Lake(lakeTiles[1]);
            } catch (Exception e) {

            }
    }

    public Tile getTile(int y, int x) {
        return map[y][x];
    }

    public Tile[][] getMap() {
        return map;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public GreenHouse getGreenHouse() {
        return greenHouse;
    }

    public Lake[] getLakes() {
        return lakes;
    }

    public Quarry[] getQuarries() {
        return quarries;
    }
    public boolean hasTile(Tile tile){
        for (Tile[] rows : this.map) {
            for (Tile rowTile : rows) {
                if (rowTile == tile) {
                    return true;
                }
            }
        }
        return false;
    }
}
