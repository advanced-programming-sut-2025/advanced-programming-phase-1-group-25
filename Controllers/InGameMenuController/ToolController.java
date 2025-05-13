package org.example.Controllers.InGameMenuController;

import org.example.Enums.ItemConsts.ItemAttributes;
import org.example.Enums.ItemConsts.ItemIDs;
import org.example.Enums.ItemConsts.ItemLevels;
import org.example.Enums.ItemConsts.ItemType;
import org.example.Models.Animals.Animal;
import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemInstance;
import org.example.Models.MapElements.Tile;
import org.example.Models.Player.Player;
import org.example.Views.InGameMenus.ActionMenuView;

import java.util.Objects;

public class ToolController {
    static ActionMenuView view = new ActionMenuView();

    public static void applyTool(ItemInstance tool, Tile tile, Player player, Game game) {
        String name = tool.getDefinition().getDisplayName().toLowerCase();
        ActionMenuController controller = new ActionMenuController(view);
        if (name.contains("hoe")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, player.getAbilities().getAbilityLevel
                    (player.getAbilities().getFarmingAbility()))) {
                view.showMessage("You don't have enough energy!");
                return;
            }
            player.reduceEnergy(player.getAbilities().getFarmingAbility(), tool, player, true, game,
                    (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost));
            if (tile.getItem().getDefinition().getType().equals(ItemType.lake)) {
                view.showMessage("You can't use hoe in lake!");
                return;
            }
            if (!tile.isEmpty()) {
                view.showMessage("This tile is not empty!");
                return;
            }
            if (tile.getPlowed()) {
                view.showMessage("This tile has already been plowed!");
                return;
            }
            tile.setPlowed(true);
            view.showMessage("You've successfully plowed the tile!");
        } else if (name.contains("pickaxe")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, player.getAbilities().getAbilityLevel
                    (player.getAbilities().getMiningAbility()))) {
                view.showMessage("You don't have enough energy!");
                return;
            }
            player.reduceEnergy(player.getAbilities().getMiningAbility(), tool, player, true, game,
                    (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost));
            if (tile.getItem().getDefinition().getType().equals(ItemType.lake)) {
                view.showMessage("You can't use pickaxe in lake!");
                return;
            }
            if (tile.isEmpty() && tile.isPlowed()) {
                tile.setPlowed(false);
                view.showMessage("This tile has been successfully unplowed!");
                return;
            }
            if(tile.getItem().getDefinition().getType().equals(ItemType.rock)){
                tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
                view.showMessage("You've removed rock from this tile!");
                return;
            }
            if (!tile.getItem().isDroppedByPlayer()) {
                view.showMessage("This item hasn't been dropped by player!");
                return;
            }
            tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
            player.getAbilities().increaseMiningAbility();
            view.showMessage("Item has been successfully removed from the tile!");
        } else if (name.contains("axe")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, player.getAbilities().getAbilityLevel
                    (player.getAbilities().getNatureAbility()))) {
                view.showMessage("You don't have enough energy!");
                return;
            }
            player.reduceEnergy(player.getAbilities().getNatureAbility(), tool, player, true, game,
                    (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost));
            if (tile.getItem().getDefinition().getType().equals(ItemType.lake)) {
                view.showMessage("You can't use axe in lake!");
                return;
            }
            if (tile.isEmpty()) {
                view.showMessage("This tile is empty!");
                return;
            }
            if (tile.getItem().getDefinition().getType().equals(ItemType.wood)) {//TODO
                player.getInventory().addItem(tile.getItem());
                tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
                view.showMessage("1 wood has been successfully added to the inventory!");
                return;
            }

            view.showMessage("Use axe in a proper tile");
        } else if (name.contains("watering can")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, player.getAbilities().getAbilityLevel
                    (player.getAbilities().getFarmingAbility()))) {
                view.showMessage("You don't have enough energy!");
                return;
            }
            player.reduceEnergy(player.getAbilities().getFarmingAbility(), tool, player, false, game,
                    (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost));
            if (tile.getItem().getDefinition().getType().equals(ItemType.lake)) {
                addWaterToWateringCan(tool);
                return;
            }
            if (tile.getItem().getDefinition().getType().equals(ItemType.all_crops)) {
                tile.setWatered(true);
                return;
            }
            view.showMessage("Use the can for lake or plants!");
        } else if (name.contains("fishing pole")) {

        } else if (name.contains("scythe")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, 0)) {
                view.showMessage("You don't have enough energy!");
                return;
            }
            player.reduceEnergy(0, tool, player, false, game,
                    (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost));
            if (tile.getItem().getDefinition().getType().equals(ItemType.fiber)) {
                cutFiber(tile, player);
                return;
            }
            if (tile.getItem().getDefinition().getType().equals(ItemType.all_crops)) {
                harvestCrop(tile, player);
                return;
            }
            view.showMessage("Use scythe for crops or fibers!");
        } else if (name.contains("milk pale")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, 0)) {
                view.showMessage("You don't have enough energy!");
                return;
            }
            player.reduceEnergy(0, tool, player, false, game,
                    (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost));
            if (tile.getItem().getDefinition().getType().equals(ItemType.barn_animal)) {
                Animal animal = (Animal) tile.getItem();
                milkAnimal(tool, player, animal);
                return;
            }
            view.showMessage("Use milk pale on cows, sheep or goats!");

        } else if (name.contains("shear")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, 0)) {
                view.showMessage("You don't have enough energy!");
                return;
            }
            player.reduceEnergy(0, tool, player, false, game,
                    (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost));
            if (tile.getItem().getDefinition().getId().equals(ItemIDs.sheep)) {
                Animal animal = (Animal) tile.getItem();
                cutWool(player, animal);
            }
        } else {
            view.showMessage("Please select a valid tool!");
        }
    }

    public static boolean checkIfPlayerHasEnoughEnergy(Player player, ItemInstance tool, int ability) {
        if (ability == 4 && player.getEnergy() < (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost) - 1) {
            return false;
        }
        if (player.getEnergy() < (int) tool.getDefinition().getAttribute(ItemAttributes.energyCost)) {
            return false;
        }
        return true;
    }

    public static void addWaterToWateringCan(ItemInstance tool) {
        int capacity = (int) tool.getDefinition().getAttribute(ItemAttributes.capacity);
        int durability = (int) tool.getDefinition().getAttribute(ItemAttributes.durability);
        if (durability + 1 > capacity) {
            view.showMessage("your can is full!");
            return;
        }
        tool.increaseDurability(durability + 1);
        int finalDurability = durability + 1;
        view.showMessage("Your can has " + finalDurability + " water");
    }

    public static void cutFiber(Tile tile, Player player) {
        player.getInventory().addItem(tile.getItem());
        tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
        view.showMessage("You've cut fiber!");
    }

    public static void harvestCrop(Tile tile, Player player) {
        player.getInventory().addItem(tile.getItem());
        tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
        player.getAbilities().increaseFarmingAbility();
        view.showMessage("You've harvested crop!");
    }

    public static void milkAnimal(ItemInstance tool, Player player, Animal animal) {
        tool.setAttribute(ItemAttributes.isFull, true);
        player.getInventory().addItem
                (new ItemInstance(Objects.requireNonNull(App.getItemDefinition("milk"))));
        player.getAbilities().increaseFarmingAbility();
        animal.setFriendShip(5);
        view.showMessage("You've milked the animal!");
    }

    public static void cutWool(Player player, Animal animal) {
        animal.setAttribute(ItemAttributes.isCut, true);
        player.getInventory().addItem
                (new ItemInstance(Objects.requireNonNull(App.getItemDefinition("wool"))));
        player.getAbilities().increaseFarmingAbility();
        animal.setFriendShip(5);
        view.showMessage("You've collected sheep wool!");
    }

    public static void removeItemFromInventory(ItemInstance trashCan, Inventory inventory, ItemInstance tool) {

    }
}
