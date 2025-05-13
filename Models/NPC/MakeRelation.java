package org.example.Models.NPC;

import org.example.Models.Game;
import org.example.Models.Player.Player;

import java.util.ArrayList;

public class MakeRelation {
    public static void makeRelations(Game game) {
        ArrayList<Player> players = game.getPlayers();
        ArrayList<NPC> NPCs = game.getNPCs();
        ArrayList<Relation> relations = new ArrayList<>();

        for (Player player : players) {
            for (NPC npc : NPCs) {
                Relation relation = new Relation(player, npc);
                relations.add(relation);
            }
        }

        game.setRelations(relations);
    }
}
