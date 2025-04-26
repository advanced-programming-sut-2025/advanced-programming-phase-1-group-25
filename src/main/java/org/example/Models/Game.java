package org.example.Models;

import org.example.Models.MapElements.PlayerMap;
import org.example.Models.Player.Player;

import java.util.ArrayList;
import java.util.Map;

public class Game {
    private ArrayList<Player> gamePlayers;
    private Map<Player, PlayerMap> playerMaps;
    private Player currentPlayer;
    public Game(ArrayList<Player> gamePlayers, Map<Player, PlayerMap> playerMaps, Player currentPlayer) {
        this.gamePlayers = gamePlayers;
        this.playerMaps = playerMaps;
        this.currentPlayer = currentPlayer;
    }

    public PlayerMap getPlayerMap(Player player) {
        return playerMaps.get(player);
    }
    public boolean gameHasPlayer(Player player) {
        return gamePlayers.contains(player);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
