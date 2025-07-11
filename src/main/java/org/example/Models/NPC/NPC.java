package org.example.Models.NPC;

import org.example.Enums.GameConsts.Gender;
import org.example.Enums.NPCConsts.NPCConst;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.MapElements.Position;

import java.util.ArrayList;

/*
    Game has some NPC and each has its own properties, we store them in a class.
 */
public class NPC {
    private String name;
    private Gender gender;
    private NPCConst.Professions profession;
    private Position position;
    private Position placePosition;
    private ArrayList<ItemDefinition> favorites;
    private ArrayList<Quest> quests;

    public NPC(String name, Gender gender,
               NPCConst.Professions profession,
               ArrayList<ItemDefinition> favorites,
               ArrayList<Quest> quests,
               Position placePosition) {
        this.name = name;
        this.gender = gender;
        this.profession = profession;
        this.placePosition = placePosition;
        this.position = new Position(placePosition.getY(), placePosition.getX() + 1);
        this.favorites = favorites;
        this.quests = quests;
    }

    public Position getPosition() {
        return position;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ItemDefinition> getFavorites() {
        return favorites;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public Position getHomePosition() {
        return placePosition;
    }

    public NPCConst.Professions getProfession() {
        return profession;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFavorites(ArrayList<ItemDefinition> favorites) {
        this.favorites = favorites;
    }

    public void setHomePosition(Position homePosition) {
        this.placePosition = homePosition;
    }

    public void setProfession(NPCConst.Professions profession) {
        this.profession = profession;
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }
    public boolean hasFavorite(ItemDefinition itemDefinition) {
        return favorites.contains(itemDefinition);
    }
    public Quest getQuest(int number) {
        try {
            return this.quests.get(number - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    public void finishQuest(Quest quest) {
        this.quests.remove(quest);
    }
}
