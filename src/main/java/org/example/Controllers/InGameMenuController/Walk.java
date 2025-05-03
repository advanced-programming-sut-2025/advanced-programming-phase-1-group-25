package org.example.Controllers.InGameMenuController;

import org.example.Enums.ItemConsts.ItemIDs;
import org.example.Enums.MapConsts.MapSizes;
import org.example.Models.App;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.MapElements.Position;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;

import java.util.*;

public class Walk {
    public static boolean isWalkable(Tile tile) {
        ItemIDs tileItemId = tile.getItem().getDefinition().getId();
        return ((tileItemId == ItemIDs.VOID) || (tileItemId == ItemIDs.fiber));
    }

    public static int heuristic(Tile a, Tile b) {
        int aX = a.getPosition().getX();
        int bX = b.getPosition().getX();
        int aY = a.getPosition().getY();
        int bY = b.getPosition().getY();

        return Math.abs(aY - bY) + Math.abs(aX - bX);
    }


    public static List<Tile> getNeighbors(Tile tile, GameMap map) {
        List<Tile> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] d : directions) {
            int nY = tile.getPosition().getY() + d[0];
            int nX = tile.getPosition().getX() + d[1];

            if (nY >= 0 && nY < MapSizes.MAP_ROWS.getSize() && nX >= 0 && nX < MapSizes.MAP_COLS.getSize()) {
                Tile neighbor = map.getTile(nY, nX);
                if (isWalkable((neighbor))) {
                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    public static List<Tile> findPath(Tile start, Tile goal, GameMap map) {
        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        Set<Tile> closed = new HashSet<>();

        Node startNode = new Node(start, null, 0, heuristic(start, goal));
        open.add(startNode);

        while (!open.isEmpty()) {
            Node current = open.poll();
            if (current.tile.equals(goal)) {
                List<Tile> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current.tile);
                    current = current.parent;
                }
                return path;
            }

            closed.add(current.tile);

            for (Tile neighbor : getNeighbors(current.tile, map)) {
                if (closed.contains(neighbor)) continue;

                int gCost = current.g + 1;
                Node neighborNode = new Node(neighbor, current, gCost, heuristic(neighbor, goal));

                boolean betterPath = true;
                for (Node n : open) {
                    if (n.tile.equals(neighbor) && n.getF() <= neighborNode.getF()) {
                        betterPath = false;
                        break;
                    }
                }
                if (betterPath) open.add(neighborNode);
            }
        }
        return null;
    }

    public static int calculateWalkEnergyCost(List<Tile> path) {
        int numberOfTurns = 0;
        int tileCost = path.size();

        for (int i = 0; i < path.size() - 2; i++) {
            Tile a = path.get(i);
            Tile b = path.get(i + 1);
            Tile c = path.get(i + 2);
            if (!((a.getPosition().getY() == b.getPosition().getY() && b.getPosition().getY() == c.getPosition().getY())
                || (a.getPosition().getX() == b.getPosition().getX() && b.getPosition().getX() == c.getPosition().getX()))) {
                numberOfTurns++;
            }
        }

        return (tileCost + 10 * numberOfTurns) / 20;
    }

    public static void walkToDestination(int energyCost, int goalY, int goalX) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        currentPlayer.setPosition(new Position(goalY, goalX));
        currentPlayer.setEnergy(currentPlayer.getEnergy() - energyCost);
        // checkEnergy(); a method that we can call every time we decrease the player's energy
    }

    public static Position walkUntilEnergyRunsOut(List<Tile> path) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        int playerEnergy = currentPlayer.getEnergy();
        for (Tile tile : path) {
            currentPlayer.setPosition(tile.getPosition());
            playerEnergy--;
            if (playerEnergy == 0) {
                return currentPlayer.getPosition();
            }
        }
        // checkEnergy(); a method that we can call every time we decrease the player's energy
        return currentPlayer.getPosition();
    }
}
