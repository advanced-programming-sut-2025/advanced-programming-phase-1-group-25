package org.example.Controllers.UpdateMap;

import org.example.Models.Game;

public class UpdateByHour {
    private Game game;

    public UpdateByHour(Game game) {
        this.game = game;
    }

    public void execute(boolean isCheat) {
        if (game.getDateTime().getHour() == 22) {
            game.updateByDay();
        }
        if (!isCheat) {
            game.getDateTime().updateTimeByHour(1);
        }
//        ArtisanUpdate.artisanWithHour(1);
    }
}
