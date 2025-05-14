package org.example.Controllers.UpdateMap;

import org.example.Controllers.InGameMenuController.AnimalController;
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
        game.getWeather().setCurrentWeather(game.getTomorrowWeather().getCurrentWeather());
        game.setTomorrowWeather();

        emptyShippingBin();
        UpdateForaging.deleteForaging();
        UpdateForaging.updateForaging();
        setPlayersPositionAndEnergy();
        UpdateFarming.updateAllCrops();
        RandomEvents.crowAttack();
        RandomEvents.strikeLightning();
        UpdateShops.updateShops();
//        ArtisanUpdate.artisanWithDay(1);
        AnimalController.addProductToAnimal(game);
        game.getDateTime().updateTimeByDay(1);
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
