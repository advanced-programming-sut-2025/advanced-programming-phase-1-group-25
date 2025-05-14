package org.example.Controllers.InGameMenuController;

import org.example.Models.App;
import org.example.Models.Game;
import org.example.Models.Item.Inventory;
import org.example.Models.Item.ItemDefinition;
import org.example.Models.Item.ItemInstance;
import org.example.Models.Player.Player;
import org.example.Models.Player.Wallet;
import org.example.Views.InGameMenus.TradeMenuView;

import java.util.ArrayList;

public class TradeController {
    TradeMenuView view;

    public TradeController(TradeMenuView view) {
        this.view = view;
    }

    public void trade(String username) {
        Game game = App.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player targetPlayer = game.getPlayerByUsername(username);

        if (targetPlayer == null) {
            this.view.showMessage("Player not found.");
            return;
        }

        String type = this.view.prompt("Choose the trade type: (request / offer)");

        if (type.equalsIgnoreCase("offer")) {
            String itemId = this.view.prompt("Enter the item id you want to offer:");
            ItemDefinition itemDefinition = App.getItemDefinition(itemId);
            if (itemDefinition == null) {
                this.view.showMessage("Item doesn't exist.");
                return;
            }
            String itemAmountStr = this.view.prompt("Enter the amount:");
            int itemAmount;
            try {
                itemAmount = Integer.parseInt(itemAmountStr);
            } catch (NumberFormatException e) {
                this.view.showMessage("Please enter a valid number.");
                return;
            }

            String option = this.view.prompt("1. Enter price\n2. Enter item\n3. Cancel");
            int number;
            try {
                number = Integer.parseInt(option);
            } catch (NumberFormatException e) {
                this.view.showMessage("Please enter a number");
                return;
            }

            switch (number) {
                case 1:
                    String priceStr = this.view.prompt("Enter the price");
                    int price;
                    try {
                        price = Integer.parseInt(priceStr);
                    } catch (NumberFormatException e) {
                        this.view.showMessage("Please enter a valid price.");
                        return;
                    }
                    // call func
                    break;
                case 2:
                    String item = this.view.prompt("Enter the item:");
                    String amountStr = this.view.prompt("Enter the amount:");
                    int amount;
                    try {
                        amount = Integer.parseInt(amountStr);
                    } catch (NumberFormatException e) {
                        this.view.showMessage("Please enter a valid amount.");
                        return;
                    }
                    // call func
                    break;
                case 3:
                    break;
                default:
            }


        } else if (type.equalsIgnoreCase("request")) {
            String itemId = this.view.prompt("Please enter the item id you want to request:");
            ItemDefinition itemDefinition = App.getItemDefinition(itemId);
            if (itemDefinition == null) {
                this.view.showMessage("Item doesn't exist.");
                return;
            }
            String itemAmountStr= this.view.prompt("Enter the amount:");
            int itemAmount;
            try {
                itemAmount = Integer.parseInt(itemAmountStr);
            } catch (NumberFormatException e) {
                this.view.showMessage("Please enter a valid number.");
                return;
            }

            String option = this.view.prompt("1. Enter price\n2. Enter item\n3. Cancel");
            int number;
            try {
                number = Integer.parseInt(option);
            } catch (NumberFormatException e) {
                this.view.showMessage("Please enter a number");
                return;
            }

            switch (number) {
                case 1:
                    String priceStr = this.view.prompt("Enter the price");
                    int price;
                    try {
                        price = Integer.parseInt(priceStr);
                    } catch (NumberFormatException e) {
                        this.view.showMessage("Please enter a valid price.");
                        return;
                    }
                    // call func
                    break;
                case 2:
                    String item = this.view.prompt("Enter the item:");
                    String amountStr = this.view.prompt("Enter the amount:");
                    int amount;
                    try {
                        amount = Integer.parseInt(amountStr);
                    } catch (NumberFormatException e) {
                        this.view.showMessage("Please enter a valid amount.");
                        return;
                    }
                    // call func
                    break;
                case 3:
                    break;
                default:
            }


        } else {
            this.view.showMessage("Please enter a valid type.");
        }
    }


    private void handleOffer(Player currentPlayer, Player targetPlayer, ItemDefinition itemOffered, int amountOffered,
                            ItemDefinition itemRequested, int amountRequested) {
            Inventory currentPlayerInventory = currentPlayer.getInventory();
            Inventory targetPlayerInventory = targetPlayer.getInventory();

            if (amountOffered > currentPlayerInventory.getItemAmount(itemOffered.getId())) {
                this.view.showMessage(String.format("You don't have enough numbers of %s.", itemOffered));
                return;
            }

            if (!currentPlayerInventory.hasCapacity() && !currentPlayerInventory.hasItem(itemRequested.getId())) {
                this.view.showMessage("Your backpack is full.");
                return;
            }

            String message = String.format("%s has offered you %d %s. cost: %d %s (accept / reject)", currentPlayer.getName(),
                    amountOffered, itemOffered.getDisplayName(), amountRequested, itemRequested.getDisplayName());
            // get respond and do the trade
            String respond = this.view.prompt(message);
            if (respond.equalsIgnoreCase("accept")) {
                if (targetPlayerInventory.getItemAmount(itemRequested.getId()) < amountRequested) {
                    this.view.showMessage(String.format("%s can't afford this item.", targetPlayer.getName()));
                    return;
                }
                if (!targetPlayerInventory.hasCapacity() && !targetPlayerInventory.hasItem(itemOffered.getId())) {
                    this.view.showMessage(String.format("%s's backpack is full.", targetPlayer.getName()));
                    return;
                }
                ArrayList<ItemInstance> requestedItems = targetPlayerInventory.getItem(itemRequested.getId(), amountRequested);
                ArrayList<ItemInstance> offeredItems = currentPlayerInventory.getItem(itemOffered.getId(), amountOffered);
                for (ItemInstance requestedItem : requestedItems) currentPlayerInventory.addItem(requestedItem);
                for (ItemInstance offeredItem : offeredItems) targetPlayerInventory.addItem(offeredItem);
                this.view.showMessage("Trade has completed successfully.");

            } else if (respond.equalsIgnoreCase("reject")) {
                this.view.showMessage(String.format("%s has rejected.", targetPlayer.getName()));
            } else {
                this.view.showMessage("Invalid command.");
            }
    }

    private void handleOffer(Player currentPlayer, Player targetPlayer, ItemDefinition itemOffered, int amountOffered,
                             int priceRequested) {
        Inventory currentPlayerInventory = currentPlayer.getInventory();
        Inventory targetPlayerInventory = targetPlayer.getInventory();
        if (amountOffered > currentPlayerInventory.getItemAmount(itemOffered.getId())) {
            this.view.showMessage(String.format("You don't have enough numbers of %s.", itemOffered.getDisplayName()));
            return;
        }

        String message = String.format("%s  has offered you %d %s. price: %d (accept / reject)", currentPlayer.getName(),
                amountOffered, itemOffered.getDisplayName(), priceRequested);

        String respond = this.view.prompt(message);
        if (respond.equalsIgnoreCase("accept")) {
            Wallet targetPlayerWallet = targetPlayer.getWallet();
            Wallet currentPlayerWallet = currentPlayer.getWallet();

            if (targetPlayerWallet.getCoin() < priceRequested) {
                this.view.showMessage(String.format("%s can't afford this item.", targetPlayer.getName()));
                return;
            }
            if (!targetPlayerInventory.hasCapacity() && !targetPlayerInventory.hasItem(itemOffered.getId())) {
                this.view.showMessage(String.format("%s's backpack is full.", targetPlayer.getName()));
                return;
            }
            currentPlayerWallet.setCoin(currentPlayerWallet.getCoin() + priceRequested);
            targetPlayerWallet.setCoin(targetPlayerWallet.getCoin() - priceRequested);
            ArrayList<ItemInstance> offeredItems = currentPlayerInventory.getItem(itemOffered.getId(), amountOffered);
            for (ItemInstance offeredItem : offeredItems) targetPlayerInventory.addItem(offeredItem);
            this.view.showMessage("Trade has completed successfully.");
        } else if (respond.equalsIgnoreCase("reject")) {
            this.view.showMessage(String.format("%s has rejected.", targetPlayer.getName()));
        } else {
            this.view.showMessage("Invalid command.");
        }
    }
    private void handleRequest(Player currentPlayer, Player targetPlayer, ItemDefinition itemRequested, int amountRequested,
                               ItemDefinition itemOffered, int amountOffered) {
        Inventory currentPlayerInventory = currentPlayer.getInventory();
        Inventory targetPlayerInventory = targetPlayer.getInventory();
        if (amountOffered > currentPlayerInventory.getItemAmount(itemOffered.getId())) {
            this.view.showMessage(String.format("You don't have enough numbers of %s", itemOffered.getDisplayName()));
            return;
        }
        if (!currentPlayerInventory.hasCapacity() && !currentPlayerInventory.hasItem(itemRequested.getId())) {
            this.view.showMessage("Your backpack is full.");
            return;
        }
        String message = String.format("%s has requested %d %s. Offered: %d %s (accept / reject)", currentPlayer.getName(),
                amountRequested, itemRequested.getDisplayName(), amountOffered, itemOffered.getDisplayName());
        // get respond and do the trade
        String respond = this.view.prompt(message);
        if (respond.equalsIgnoreCase("accept")) {
            if (targetPlayerInventory.getItemAmount(itemRequested.getId()) < amountRequested) {
                this.view.showMessage(String.format("%s doesn't have enough %s.",
                        targetPlayer.getName(), itemRequested.getDisplayName()));
                return;
            }
            if (!targetPlayerInventory.hasCapacity() && !targetPlayerInventory.hasItem(itemOffered.getId())) {
                this.view.showMessage(String.format("%s's backpack is full.", targetPlayer.getName()));
                return;
            }
            ArrayList<ItemInstance> requestedItems = targetPlayerInventory.getItem(itemRequested.getId(), amountRequested);
            ArrayList<ItemInstance> offeredItems = currentPlayerInventory.getItem(itemOffered.getId(), amountOffered);
            for (ItemInstance requestedItem : requestedItems) currentPlayerInventory.addItem(requestedItem);
            for (ItemInstance offeredItem : offeredItems) targetPlayerInventory.addItem(offeredItem);
            this.view.showMessage("Trade has completed successfully.");

        } else if (respond.equalsIgnoreCase("reject")) {
            this.view.showMessage(String.format("%s has rejected.", targetPlayer.getName()));
        } else {
            this.view.showMessage("Invalid command.");
        }
    }
    private void handleRequest(Player currentPlayer, Player targetPlayer, ItemDefinition itemRequested, int amountRequested,
                               int priceOffered) {
        Inventory currentPlayerInventory = currentPlayer.getInventory();
        Inventory targetPlayerInventory = targetPlayer.getInventory();
        if (priceOffered > currentPlayer.getWallet().getCoin()) {
            this.view.showMessage("You don't have enough money.");
            return;
        }
        if (!currentPlayerInventory.hasCapacity() && !currentPlayerInventory.hasItem(itemRequested.getId())) {
            this.view.showMessage(String.format("Your backpack is full.", targetPlayer.getName()));
            return;
        }
        String message = String.format("%s has requested %d %s. Offered: %dg (accept / reject)", currentPlayer.getName(),
                amountRequested, itemRequested.getDisplayName(), priceOffered);
        String respond = this.view.prompt(message);
        if (respond.equalsIgnoreCase("accept")) {
            Wallet currentPlayerWallet = currentPlayer.getWallet();
            Wallet targetPlayerWallet = targetPlayer.getWallet();

            currentPlayerWallet.setCoin(currentPlayerWallet.getCoin() - priceOffered);
            targetPlayerWallet.setCoin(targetPlayerWallet.getCoin() + priceOffered);
            ArrayList<ItemInstance> requestedItems = targetPlayerInventory.getItem(itemRequested.getId(), amountRequested);
            for (ItemInstance requestedItem : requestedItems) currentPlayerInventory.addItem(requestedItem);
            this.view.showMessage("Trade has completed successfully.");
        } else if (respond.equalsIgnoreCase("reject")) {
            this.view.showMessage(String.format("%s has rejected.", targetPlayer.getName()));
        } else {
            this.view.showMessage("Invalid command.");
        }
    }
}
