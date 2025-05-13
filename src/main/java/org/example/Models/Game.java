package org.example.Models;

import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.Player.Player;
import org.example.Models.States.DateTime;
import org.example.Models.States.Weather;

import java.util.*;

public class Game {
    private ArrayList<Player> gamePlayers;
    private Map<Player, PlayerMap> playerMaps;
    private Player currentPlayer;
    private DateTime dateTime;
    private Weather weather;
    private GameMap gameMap;
    private Weather tomorrowWeather;
    public Game(ArrayList<Player> gamePlayers, Map<Player, PlayerMap> playerMaps, Player currentPlayer, GameMap gameMap) {
        this.gamePlayers = gamePlayers;
        this.playerMaps = playerMaps;
        this.currentPlayer = currentPlayer;
        this.dateTime = new DateTime();
        this.weather = new Weather();
        this.gameMap = gameMap;
    }

    public Weather getTomorrowWeather() {
        return tomorrowWeather;
    }

    public void setTomorrowWeather(Weather tomorrowWeather) {
        this.tomorrowWeather = tomorrowWeather;
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

    public Player getNextPlayer() {
        return gamePlayers.get((this.gamePlayers.indexOf(this.currentPlayer) + 1) % (this.gamePlayers.size()));
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<PlayerMap> getPlayerMaps() {
        return new ArrayList<>(playerMaps.values());
    }

    public ArrayList<Player> getPlayers() {
        return gamePlayers;
    }

    public boolean isPlayerActive(Player player) {
        return player.getEnergyPerTurn() > 0;
    }
}
