package org.example.Models;

import org.example.Controllers.InGameMenuController.GenerateRandomNumber;
import org.example.Controllers.UpdateMap.UpdateByDay;
import org.example.Controllers.UpdateMap.UpdateByHour;
import org.example.Enums.GameConsts.Seasons;
import org.example.Enums.GameConsts.WeatherStates;
import org.example.Models.Item.ShippingBin;
import org.example.Models.MapElements.GameMap;
import org.example.Models.MapElements.PlayerMap;
import org.example.Models.NPC.NPC;
import org.example.Models.NPC.Relation;
import org.example.Models.Player.Player;
import org.example.Models.Player.PlayerRelation;
import org.example.Models.MapElements.Shop;
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
    private ArrayList<NPC> NPCs;
    private ArrayList<Shop> shops;
    private ArrayList<Relation> relations;
    private ArrayList<PlayerRelation> playerRelations;
    private ShippingBin shippingBin;
    private UpdateByHour updaterByHour;
    private UpdateByDay updaterByDay;
    private Weather tomorrowWeather;

    public Game(ArrayList<Player> gamePlayers, Map<Player, PlayerMap> playerMaps, Player currentPlayer, GameMap gameMap) {
        this.gamePlayers = gamePlayers;
        this.playerMaps = playerMaps;
        this.currentPlayer = currentPlayer;
        this.dateTime = new DateTime();
        this.weather = new Weather();
        this.tomorrowWeather = new Weather();
        setTomorrowWeather();
        this.gameMap = gameMap;
        this.NPCs = new ArrayList<>();
        this.shops = new ArrayList<>();
        this.shippingBin = new ShippingBin();
        this.updaterByDay = new UpdateByDay(this);
        this.updaterByHour = new UpdateByHour(this);
    }

    public Weather getTomorrowWeather() {
        return tomorrowWeather;
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

    public Player getPlayerByUsername(String username) {
        for (Player player : this.gamePlayers) {
            if (player.getUser().getUsername().equals(username)) {
                return player;
            }
        }
        return null;
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

    public void addShop(Shop shop) {
        this.shops.add(shop);
    }

    public void addNPC(NPC npc) {
        this.NPCs.add(npc);
    }

    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public NPC getNPC(String name) {
        for (NPC npc : this.NPCs) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }

    public Relation getRelation(Player player, NPC npc) {
        for (Relation relation : this.relations) {
            if (relation.getPlayer() == player
                    && relation.getNpc() == npc) {
                return relation;
            }
        }
        return null;
    }

    public void addRelation(Relation relation) {
        this.relations.add(relation);
    }

    public void setRelations(ArrayList<Relation> relations) {
        this.relations = relations;
    }

    public void setPlayerRelations(ArrayList<PlayerRelation> playerRelations) {
        this.playerRelations = playerRelations;
    }

    public PlayerRelation getPlayerRelation(Player player1, Player player2) {
        for (PlayerRelation playerRelation : this.playerRelations) {
            Player p1 = playerRelation.getPlayer1();
            Player p2 = playerRelation.getPlayer2();

            if ((p1 == player1 && p2 == player2)
                    || (p2 == player1 && p1 == player2)) {
                return playerRelation;
            }
        }

        return null;
    }

    public Shop getShop(String targetShop) {
        for (Shop shop : this.shops) {
            if (shop.getShopName().equalsIgnoreCase(targetShop)) {
                return shop;
            }
        }
        return null;
    }

    public ShippingBin getShippingBin() {
        return shippingBin;
    }

    public void setTurn(Player player) {
        this.setCurrentPlayer(player);
    }

    public boolean isPlayerActive(Player player) {
        return player.getEnergyPerTurn() > 0;
    }

    public void updateByHour() {
        this.updaterByHour.execute();
    }

    public void updateByDay() {
        this.updaterByDay.execute();
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
    public void setTomorrowWeather() {
        Seasons seasons = this.getDateTime().getSeason();
        int weatherRandomVariable = GenerateRandomNumber.generateRandomNumber(1, 10);
        WeatherStates weatherStates = null;
        switch (seasons.name().toLowerCase()) {
            case "spring":
                if (1 <= weatherRandomVariable && weatherRandomVariable <= 5) weatherStates = WeatherStates.SUNNY;

                else if (6 <= weatherRandomVariable && weatherRandomVariable <= 8) weatherStates = WeatherStates.RAIN;

                else weatherStates = WeatherStates.STORM;
                break;
            case "summer":
                if (1 <= weatherRandomVariable && weatherRandomVariable <= 8) weatherStates = WeatherStates.SUNNY;

                else if (weatherRandomVariable == 9) weatherStates = WeatherStates.RAIN;

                else weatherStates = WeatherStates.STORM;
                break;
            case "fall":
                if (1 <= weatherRandomVariable && weatherRandomVariable <= 2) weatherStates = WeatherStates.SUNNY;

                else if (3 <= weatherRandomVariable && weatherRandomVariable <= 7) weatherStates = WeatherStates.RAIN;

                else weatherStates = WeatherStates.STORM;
                break;
            case "winter":
                if (1 <= weatherRandomVariable && weatherRandomVariable <= 3) weatherStates = WeatherStates.SUNNY;
                else weatherStates = WeatherStates.SNOWY;
                break;
        }
        this.tomorrowWeather.setCurrentWeather(weatherStates);
    }
}
