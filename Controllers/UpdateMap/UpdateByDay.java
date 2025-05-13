package org.example.Controllers.UpdateMap;

import org.example.Models.Game;
import org.example.Models.Item.ShippingBin;
import org.example.Models.Player.Player;

import java.util.ArrayList;
import java.util.Map;

public class UpdateByDay {
    private Game game;

    public UpdateByDay(Game game) {
        this.game = game;
    }

    public void execute() {
//        System.out.println(game.getTomorrowWeather().getCurrentWeather().name());
//        game.setWeather(game.getTomorrowWeather());
//        game.setTomorrowWeather();

        emptyShippingBin();
        UpdateForaging.deleteForaging();
        UpdateForaging.updateForaging();
        setPlayersPositionAndEnergy();
    }


    private void emptyShippingBin() {
        ShippingBin shippingBin = game.getShippingBin();
        for (Map.Entry<Player, Integer> entry : shippingBin.getPlayerSoldItemsPrice().entrySet()) {
            Player player = entry.getKey();
            int price = shippingBin.getPriceAndSetBinEmpty(player);
            player.getWallet().increaseCoin(price);
        }
    }

    private void setPlayersPositionAndEnergy() {
        ArrayList<Player> players = game.getPlayers();
        for (Player player : players) {
            if (player.isFainted()) {
                player.setEnergy((int) (0.75 * player.getEnergyLimit()));
            } else {
                player.setPosition(player.getCottagePosition());
                player.setEnergy(player.getEnergyLimit());
            }

            player.setFainted(false);
        }
    }
}
