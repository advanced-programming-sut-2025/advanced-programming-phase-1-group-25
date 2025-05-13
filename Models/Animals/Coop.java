package org.example.Models.Animals;

import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.Position;

import java.util.ArrayList;

public class Coop extends ItemInstance {
    private ArrayList<Animal> animals;
    private Position position;
    public Coop(ItemDefinition definition, Position position) {
        super(definition);
        this.position = position;
        animals = new ArrayList<>();
    }
    public int getX(){
        return position.getX();
    }
    public int getY(){
        return position.getY();
    }
    public Position getPosition(){
        return position;
    }
    public void setAnimal(Animal animal){
        animals.add(animal);
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}
