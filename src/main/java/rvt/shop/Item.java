package rvt.shop;

public class Item {
    private String product;
    private int qty;
    private int unitPrice;

    /**
     * Constructor creates an item with product name, quantity, and unit price.
     * @param product the name of the product
     * @param qty the quantity of the product
     * @param unitPrice the price per unit
     */
    public Item(String product, int qty, int unitPrice) {
        this.product = product;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the total price of the item (quantity * unit price).
     * @return the total price
     */
    public int price() {
        return this.qty * this.unitPrice;
    }

    /**
     * Increases the quantity of the item by one.
     */
    public void increaseQuantity() {
        this.qty++;
    }

    /**
     * Returns a string representation of the item in the format "product: qty".
     * @return string representation
     */
    public String toString() {
        return this.product + ": " + this.qty;
    }
}