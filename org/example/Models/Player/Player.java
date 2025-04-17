package advanced.org.example.Models.Player;

import advanced.org.example.Enums.GameConsts.Gender;
import advanced.org.example.Models.Item.BackPack;
import advanced.org.example.Models.MapElements.Position;
import advanced.org.example.Models.User;

/*
    When a game is made, it has players; and when users enter the game, they become players of that game.
 */
public class Player {
   private User user;
   private String name;
   private Gender gender;
   private int energy;
   private int coin;
   private BackPack inventory;
   private PlayerAbilities abilities;
   private Position position;

    public Player(User user, String name, Gender gender, Position position) {
        this.user = user;
        this.name = name;
        this.gender = gender;
        this.energy = 100; // initial energy
        this.coin = 0; // initial coin
        this.inventory = new BackPack();
        this.abilities = new PlayerAbilities();
        this.position = position; // initial position
    }
}
