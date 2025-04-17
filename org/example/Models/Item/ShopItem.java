package advanced.org.example.Models.Item;

import advanced.org.example.Models.Shops.Shop;

/*
    Items that are in the shops.
 */
public class ShopItem extends ItemInstance {
    private int amountInShop;
    private Shop shop;

    public ShopItem(ItemDefinition definition, int amountInShop, Shop shop) {
        super(definition);
        this.amountInShop = amountInShop;
        this.shop = shop;
    }
    // ...
}
