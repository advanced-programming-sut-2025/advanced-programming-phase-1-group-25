package org.example.Models;

import org.example.Models.MapElements.PlayerMap;
import org.example.Models.Player.Player;

import java.util.ArrayList;
import java.util.Map;

public class Game {
    private ArrayList<Player> players;
    private Map<Player, PlayerMap> playerMaps;
    private Map map;
    public Game(ArrayList<Player> players, Map<Player, PlayerMap> playerMaps) {
        this.players = players;
        this.playerMaps = playerMaps;
    }
}
