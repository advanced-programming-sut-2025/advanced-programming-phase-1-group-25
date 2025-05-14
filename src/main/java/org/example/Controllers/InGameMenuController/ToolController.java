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
                view.showMessage("you don't have enough energy!");
                return;
            }
            player.reduceEnergy(player.getAbilities().getFarmingAbility(), tool, player, true, game);
            if (tile.getItem().getDefinition().getType().equals(ItemType.lake)) {
                view.showMessage("you can't use hoe in lake!");
                return;
            }
            if (!tile.isEmpty()) {
                view.showMessage("this tile is not empty!");
                return;
            }
            if (tile.getPlowed()) {
                view.showMessage("this tile has already been plowed!");
                return;
            }
            tile.setPlowed(true);
            view.showMessage("you've successfully plowed the tile!");
        } else if (name.contains("pickaxe")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, player.getAbilities().getAbilityLevel
                    (player.getAbilities().getMiningAbility()))) {
                view.showMessage("you don't have enough energy!");
                return;
            }
            player.reduceEnergy(player.getAbilities().getMiningAbility(), tool, player, true, game);
            if (tile.getItem().getDefinition().getType().equals(ItemType.lake)) {
                view.showMessage("you can't use pickaxe in lake!");
                return;
            }
            if (tile.isEmpty() && tile.getPlowed()) {
                tile.setPlowed(false);
                view.showMessage("this tile has been successfully unplowed!");
                return;
            }
            if (!tile.getItem().isDroppedByPlayer()) {
                view.showMessage("this item hasn't been dropped by player!");
                return;
            }
            tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
            player.getAbilities().increaseMiningAbility();
            view.showMessage("item has been successfully removed from the tile!");
        } else if (name.contains("axe")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, player.getAbilities().getAbilityLevel
                    (player.getAbilities().getNatureAbility()))) {
                view.showMessage("you don't have enough energy!");
                return;
            }
            player.reduceEnergy(player.getAbilities().getNatureAbility(), tool, player, true, game);
            if (tile.getItem().getDefinition().getType().equals(ItemType.lake)) {
                view.showMessage("you can't use axe in lake!");
                return;
            }
            if (tile.isEmpty()) {
                view.showMessage("this tile is empty!");
                return;
            }
            if (tile.getItem().getDefinition().getType().equals(ItemType.wood)) {//TODO
                player.getInventory().addItem(tile.getItem());
                tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
                view.showMessage("1 wood has been successfully added to the inventory!");
                return;
            }

            view.showMessage("use axe in a proper tile");
        } else if (name.contains("watering can")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, player.getAbilities().getAbilityLevel
                    (player.getAbilities().getFarmingAbility()))) {
                view.showMessage("you don't have enough energy!");
                return;
            }
            player.reduceEnergy(player.getAbilities().getFarmingAbility(), tool, player, false, game);
            if (tile.getItem().getDefinition().getType().equals(ItemType.lake)) {
                addWaterToWateringCan(tool);
                return;
            }
            if (tile.getItem().getDefinition().getType().equals(ItemType.all_crops)) {
                tile.setWatered(true);
                return;
            }
            view.showMessage("use the can for lake or plants!");
        } else if (name.contains("fishing pole")) {

        } else if (name.contains("scythe")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, 0)) {
                view.showMessage("you don't have enough energy!");
                return;
            }
            player.reduceEnergy(0, tool, player, false, game);
            if (tile.getItem().getDefinition().getType().equals(ItemType.fiber)) {
                cutFiber(tile, player);
                return;
            }
            if (tile.getItem().getDefinition().getType().equals(ItemType.all_crops)) {
                harvestCrop(tile, player);
                return;
            }
            view.showMessage("use scythe for crops or fibers!");
        } else if (name.contains("milk pale")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, 0)) {
                view.showMessage("you don't have enough energy!");
                return;
            }
            player.reduceEnergy(0, tool, player, false, game);
            if (tile.getItem().getDefinition().getType().equals(ItemType.barn_animal)) {
                Animal animal = (Animal) tile.getItem();
                milkAnimal(tool, player, animal);
                return;
            }
            view.showMessage("use milk pale on cows, sheep or goats!");

        } else if (name.contains("shear")) {
            if (!checkIfPlayerHasEnoughEnergy(player, tool, 0)) {
                view.showMessage("you don't have enough energy!");
                return;
            }
            player.reduceEnergy(0, tool, player, false, game);
            if (tile.getItem().getDefinition().getId().equals(ItemIDs.sheep)) {
                Animal animal = (Animal) tile.getItem();
                cutWool(player, animal);
            }
        } else {
            view.showMessage("please select a valid tool!");
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
        if (durability == capacity) {
            view.showMessage("your can is full!");
            return;
        }
        tool.increaseDurability(durability + 1);
        view.showMessage("your can has " + durability + 1 + " water");
    }

    public static void cutFiber(Tile tile, Player player) {
        player.getInventory().addItem(tile.getItem());
        tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
        view.showMessage("you've cute fiber!");
    }

    public static void harvestCrop(Tile tile, Player player) {
        player.getInventory().addItem(tile.getItem());
        tile.setItem(new ItemInstance(Objects.requireNonNull(App.getItemDefinition("VOID"))));
        player.getAbilities().increaseFarmingAbility();
        view.showMessage("you've harvested crop!");
    }

    public static void milkAnimal(ItemInstance tool, Player player, Animal animal) {
        tool.setAttribute(ItemAttributes.isFull, true);
        player.getInventory().addItem
                (new ItemInstance(Objects.requireNonNull(App.getItemDefinition("milk"))));
        player.getAbilities().increaseFarmingAbility();
        animal.increaseFriendShip(5);
        view.showMessage("you've milked the animal!");
    }

    public static void cutWool(Player player, Animal animal) {
        animal.setAttribute(ItemAttributes.isCut, true);
        player.getInventory().addItem
                (new ItemInstance(Objects.requireNonNull(App.getItemDefinition("wool"))));
        player.getAbilities().increaseFarmingAbility();
        animal.increaseFriendShip(5);
        view.showMessage("you've collected sheep wool!");
    }

    public static void removeItemFromInventory(ItemInstance trashCan, Inventory inventory, ItemInstance tool) {

    }
}
