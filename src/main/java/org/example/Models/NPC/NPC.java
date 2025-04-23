package org.example.Models.NPC;

import org.example.Enums.GameConsts.Gender;
import org.example.Enums.GameConsts.Professions;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.House;
import org.example.Models.MapElements.Position;

import java.util.ArrayList;

/*
    Game has some NPC and each has its own properties, we store them in a class.
 */
public class NPC {
    private String name;
    private Gender gender;
    private Professions profession;
    private Position position;
    private House house;
    private ArrayList<ItemInstance> favorites;
    private ArrayList<Quest> quests;

    public NPC(String name, Gender gender,
               Professions profession,
               ArrayList<ItemInstance> favorites,
               ArrayList<Quest> quests,
               House house) {
        this.name = name;
        this.gender = gender;
        this.profession = profession;
        this.house = house;
        this.favorites = favorites;
        this.quests = quests;
    }



    // setter and getters ...

}
