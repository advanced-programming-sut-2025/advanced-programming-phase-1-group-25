package org.example.Controllers.InGameMenuController.Walk;

import org.example.Models.MapElements.Tile;

public class Node {
    public Tile tile;
    public Node parent;
    public int g, h;

    public Node(Tile tile, Node parent, int g, int h) {
        this.tile = tile;
        this.parent = parent;
        this.g = g;
        this.h = h;
    }

    public int getF() {
        return g + h;
    }

}
