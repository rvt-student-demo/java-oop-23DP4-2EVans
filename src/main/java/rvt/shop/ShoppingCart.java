package rvt.shop;

import java.util.HashMap;
import java.util.Map;

/**
 * ShoppingCart manages items added by the customer.
 * Ensures one Item object per product, increasing quantity if product already in cart.
 */
public class ShoppingCart {
    private Map<String, Item> items;

    /**
     * Constructor initializes an empty shopping cart.
     */
    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    /**
     * Adds a product to the cart. If product already exists, increases its quantity.
     * @param product the name of the product
     * @param price the unit price of the product
     */
    public void add(String product, int price) {
        if (this.items.containsKey(product)) {
            this.items.get(product).increaseQuantity();
        } else {
            this.items.put(product, new Item(product, 1, price));
        }
    }

    /**
     * Calculates the total price of all items in the cart.
     * @return the total price
     */
    public int price() {
        int total = 0;
        for (Item item : this.items.values()) {
            total += item.price();
        }
        return total;
    }

    /**
     * Prints all items in the cart to the console.
     */
    public void print() {
        for (Item item : this.items.values()) {
            System.out.println(item);
        }
    }
}