package rvt.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Warehouse class manages products, their prices, and stock levels.
 * Uses HashMap to store prices and stocks for efficient lookups.
 */
public class Warehouse {
    private Map<String, Integer> prices;
    private Map<String, Integer> stocks;

    /**
     * Constructor initializes the warehouse with empty maps for prices and stocks.
     */
    public Warehouse() {
        this.prices = new HashMap<>();
        this.stocks = new HashMap<>();
    }

    /**
     * Adds a product to the warehouse with specified price and initial stock.
     * @param product the name of the product
     * @param price the price of the product
     * @param stock the initial stock quantity
     */
    public void addProduct(String product, int price, int stock) {
        this.prices.put(product, price);
        this.stocks.put(product, stock);
    }

    /**
     * Returns the price of the product. If product not found, returns -99.
     * @param product the name of the product
     * @return the price or -99 if not found
     */
    public int price(String product) {
        return this.prices.getOrDefault(product, -99);
    }

    /**
     * Returns the current stock of the product. If product not found, returns 0.
     * @param product the name of the product
     * @return the stock quantity or 0 if not found
     */
    public int stock(String product) {
        return this.stocks.getOrDefault(product, 0);
    }

    /**
     * Reduces the stock of the product by one if available.
     * @param product the name of the product
     * @return true if stock was reduced, false if out of stock or not found
     */
    public boolean take(String product) {
        int currentStock = this.stock(product);
        if (currentStock > 0) {
            this.stocks.put(product, currentStock - 1);
            return true;
        }
        return false;
    }

    /**
     * Returns the set of all product names in the warehouse.
     * @return set of product names
     */
    public Set<String> products() {
        return this.prices.keySet();
    }
}
