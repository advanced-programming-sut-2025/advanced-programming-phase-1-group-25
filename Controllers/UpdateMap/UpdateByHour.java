package org.example.Controllers.UpdateMap;

import org.example.Models.Game;

public class UpdateByHour {
    private Game game;

    public UpdateByHour(Game game) {
        this.game = game;
    }

    public void execute() {
        if (game.getDateTime().getHour() == 21) {
            game.updateByDay();
        }
        game.getDateTime().updateTimeByHour(1);

    }
}
