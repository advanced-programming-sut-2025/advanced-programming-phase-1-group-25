package org.example.Controllers.InGameMenuController;

import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.Player.Player;

import java.util.Map;

public class CraftingMenuController {
    public String craftingCraft(String itemName, Game game) {
        if (game.getCurrentPlayer().getInventory().getCapacity() <= game.getCurrentPlayer().getInventory().getItems().size()) {
            return "Your backpack is full.";
        } else if (itemName.equalsIgnoreCase("Cherry Bomb")) {
            ItemDefinition definition = App.getItemDefinition("cherry_bomb");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int miningLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object mining = sourceMap.get("mining");
                if (mining instanceof Integer) {
                    miningLevel = (int) mining;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getMiningAbility()) < miningLevel) {
                return "Your mining level isn't enough.";
            }
            return createItem(game, definition, "cherry_bomb");
        } else if (itemName.equalsIgnoreCase("Bomb")) {
            ItemDefinition definition = App.getItemDefinition("bomb");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int miningLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object mining = sourceMap.get("mining");
                if (mining instanceof Integer) {
                    miningLevel = (int) mining;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getMiningAbility()) < miningLevel) {
                return "Your mining level isn't enough.";
            }
            return createItem(game, definition, "bomb");
        } else if (itemName.equalsIgnoreCase("Mega Bomb")) {
            ItemDefinition definition = App.getItemDefinition("mega_bomb");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int miningLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object mining = sourceMap.get("mining");
                if (mining instanceof Integer) {
                    miningLevel = (int) mining;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getMiningAbility()) < miningLevel) {
                return "Your mining level isn't enough.";
            }
            return createItem(game, definition, "mega_bomb");
        } else if (itemName.equalsIgnoreCase("Sprinkler")) {
            ItemDefinition definition = App.getItemDefinition("sprinkler");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int farmingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object farming = sourceMap.get("farming");
                if (farming instanceof Integer) {
                    farmingLevel = (int) farming;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getFarmingAbility()) < farmingLevel) {
                return "Your farming level isn't enough.";
            }
            return createItem(game, definition, "sprinkler");
        } else if (itemName.equalsIgnoreCase("Quality Sprinkler")) {
            ItemDefinition definition = App.getItemDefinition("quality_sprinkler");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int farmingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object farming = sourceMap.get("farming");
                if (farming instanceof Integer) {
                    farmingLevel = (int) farming;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getFarmingAbility()) < farmingLevel) {
                return "Your farming level isn't enough.";
            }
            return createItem(game, definition, "quality_sprinkler");
        } else if (itemName.equalsIgnoreCase("Iridium Sprinkler")) {
            ItemDefinition definition = App.getItemDefinition("iridium_sprinkler");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int farmingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object farming = sourceMap.get("farming");
                if (farming instanceof Integer) {
                    farmingLevel = (int) farming;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getFarmingAbility()) < farmingLevel) {
                return "Your farming level isn't enough.";
            }
            return createItem(game, definition, "iridium_sprinkler");
        } else if (itemName.equalsIgnoreCase("Charcoal Klin")) {
            ItemDefinition definition = App.getItemDefinition("charcoal_klin");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int foragingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object foraging = sourceMap.get("foraging");
                if (foraging instanceof Integer) {
                    foragingLevel = (int) foraging;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getNatureAbility()) < foragingLevel) {
                return "Your foraging level isn't enough.";
            }
            return createItem(game, definition, "charcoal_klin");
        } else if (itemName.equalsIgnoreCase("Furnace")) {
            ItemDefinition definition = App.getItemDefinition("furnace");
            return createItem(game, definition, "furnace");
        } else if (itemName.equalsIgnoreCase("Scarecrow")) {
            ItemDefinition definition = App.getItemDefinition("scarecrow");
            return createItem(game, definition, "scarecrow");
        } else if (itemName.equalsIgnoreCase("Deluxe Scarecrow")) {
            ItemDefinition definition = App.getItemDefinition("deluxe_scarecrow");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int farmingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object farming = sourceMap.get("farming");
                if (farming instanceof Integer) {
                    farmingLevel = (int) farming;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getFarmingAbility()) < farmingLevel) {
                return "Your farming level isn't enough.";
            }
            return createItem(game, definition, "deluxe_scarecrow");
        } else if (itemName.equalsIgnoreCase("Bee House")) {
            ItemDefinition definition = App.getItemDefinition("bee_house");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int farmingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object farming = sourceMap.get("farming");
                if (farming instanceof Integer) {
                    farmingLevel = (int) farming;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getFarmingAbility()) < farmingLevel) {
                return "Your farming level isn't enough.";
            }
            return createItem(game, definition, "bee_house");
        } else if (itemName.equalsIgnoreCase("Cheese Press")) {
            ItemDefinition definition = App.getItemDefinition("cheese_press");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int farmingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object farming = sourceMap.get("farming");
                if (farming instanceof Integer) {
                    farmingLevel = (int) farming;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getFarmingAbility()) < farmingLevel) {
                return "Your farming level isn't enough.";
            }
            return createItem(game, definition, "cheese_press");
        } else if (itemName.equalsIgnoreCase("Keg")) {
            ItemDefinition definition = App.getItemDefinition("keg");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int farmingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object farming = sourceMap.get("farming");
                if (farming instanceof Integer) {
                    farmingLevel = (int) farming;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getFarmingAbility()) < farmingLevel) {
                return "Your farming level isn't enough.";
            }
            return createItem(game, definition, "keg");
        } else if (itemName.equalsIgnoreCase("Loom")) {
            ItemDefinition definition = App.getItemDefinition("loom");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int farmingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object farming = sourceMap.get("farming");
                if (farming instanceof Integer) {
                    farmingLevel = (int) farming;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getFarmingAbility()) < farmingLevel) {
                return "Your farming level isn't enough.";
            }
            return createItem(game, definition, "loom");
        } else if (itemName.equalsIgnoreCase("Oil Maker")) {
            ItemDefinition definition = App.getItemDefinition("oil_maker");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int farmingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object farming = sourceMap.get("farming");
                if (farming instanceof Integer) {
                    farmingLevel = (int) farming;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getFarmingAbility()) < farmingLevel) {
                return "Your farming level isn't enough.";
            }
            return createItem(game, definition, "oil_maker");
        } else if (itemName.equalsIgnoreCase("Preserves Jar")) {
            ItemDefinition definition = App.getItemDefinition("preserves_jar");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int farmingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object farming = sourceMap.get("farming");
                if (farming instanceof Integer) {
                    farmingLevel = (int) farming;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getFarmingAbility()) < farmingLevel) {
                return "Your farming level isn't enough.";
            }
            return createItem(game, definition, "preserves_jar");
        } else if (itemName.equalsIgnoreCase("Mayonnaise Machine")) {
            ItemDefinition definition = App.getItemDefinition("mayonnaise_machine");
            return createItem(game, definition, "mayonnaise_machine");
        } else if (itemName.equalsIgnoreCase("Dehydrator")) {
            ItemDefinition definition = App.getItemDefinition("dehydrator");
            /// shopLogic todo
            return createItem(game, definition, "dehydrator");
        } else if (itemName.equalsIgnoreCase("Grass Starter")) {
            ItemDefinition definition = App.getItemDefinition("grass_starter");
            /// shopLogic todo
            return createItem(game, definition, "grass_starter");
        } else if (itemName.equalsIgnoreCase("Fish Smoker")) {
            ItemDefinition definition = App.getItemDefinition("fish_smoker");
            /// shopLogic todo
            return createItem(game, definition, "fish_smoker");
        } else if (itemName.equalsIgnoreCase("Mystic Tree Seed")) {
            ItemDefinition definition = App.getItemDefinition("mystic_tree_seed");
            Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
            Object sourceObj = baseAttributes.get(ItemAttributes.source);
            int foragingLevel = 0;
            if (sourceObj instanceof Map<?, ?> sourceMap) {
                Object foraging = sourceMap.get("foraging");
                if (foraging instanceof Integer) {
                    foragingLevel = (int) foraging;
                }
            }
            if (game.getCurrentPlayer().getAbilities().getAbilityLevel(game.getCurrentPlayer().getAbilities().getNatureAbility()) < foragingLevel) {
                return "Your foraging level isn't enough.";
            }
            return createItem(game, definition, "mystic_tree_seed");
        } else {
            return "";
        }
    }

    public String cheatAddItem(String itemName, int count, Game game) {
        Player player = game.getCurrentPlayer();
        if (player.getInventory().findItemByName(itemName) == null) {
            return "Item not found.";
        } else {
            if (player.getInventory().getCapacity() <= player.getInventory().getItems().size()) {
                return "Your backpack is full.";
            } else {
                player.getInventory().addItem(itemName, count);
                return count + "x" + itemName + "added to backpack.";
            }
        }
    }

    public String placeItem(String direction, Game game, String name, int amount) {// Example input
        if (!direction.matches("^(N|NE|E|SE|S|SW|W|NW)$")) {
            return ("Invalid direction");
        }
        String result = game.getCurrentPlayer().getInventory().dropItemByName(name, amount);
        if (result.startsWith("You don't")) {
            return result;
        } else {
            /// todo : placing the item
            return result;
        }
    }

    public String craftingShowRecipes() {
        ///  todo
        return "";
    }

    private String createItem(Game game, ItemDefinition definition, String id) {
        game.getCurrentPlayer().reduceEnergyWhenCrafting(2);
        Map<ItemAttributes, Object> baseAttributes = definition.getBaseAttributes();
        Object ingredientsObj = baseAttributes.get(ItemAttributes.ingredients);
        if (ingredientsObj instanceof Map<?, ?>) {
            Map<?, ?> ingredientsMap = (Map<?, ?>) ingredientsObj;

            // Now safely cast to Map<String, Integer> if you're sure
            for (Map.Entry<?, ?> entry : ingredientsMap.entrySet()) {
                String ingredientName = (String) entry.getKey();
                Integer quantity = (Integer) entry.getValue();
                Inventory inventory = game.getCurrentPlayer().getInventory();
                for (Map.Entry<ItemInstance, Integer> entry1 : inventory.getItems().entrySet()) {
                    ItemInstance item = entry1.getKey();
                    Integer value = entry1.getValue();
                    if (item.getDefinition().getDisplayName().equalsIgnoreCase(ingredientName)) {
                        if (value < quantity) {
                            return "You don't have enough " + item.getDefinition().getDisplayName() + ".";
                        }
                        inventory.dropItem(id, quantity);
                    }
                }
            }
        }
        ItemInstance item = new ItemInstance(App.getItemDefinition(id));
        game.getCurrentPlayer().getInventory().addNewItem(item);
        return id + " crafted successfully.";
    }
}

