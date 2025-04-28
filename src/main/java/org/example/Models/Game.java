package org.example.Models;

import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.Player.Player;
import org.example.Models.States.DateTime;
import org.example.Models.States.Weather;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Game {
    private ArrayList<Player> gamePlayers;
    private Map<Player, PlayerMap> playerMaps;
    private Player currentPlayer;
    private DateTime dateTime;
    private Weather weather;
    private GameMap gameMap;
    public Game(ArrayList<Player> gamePlayers, Map<Player, PlayerMap> playerMaps, Player currentPlayer, GameMap gameMap) {
        this.gamePlayers = gamePlayers;
        this.playerMaps = playerMaps;
        this.currentPlayer = currentPlayer;
        this.dateTime = new DateTime();
        this.weather = new Weather();
        this.gameMap = gameMap;

    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Weather getWeather() {
        return weather;
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
