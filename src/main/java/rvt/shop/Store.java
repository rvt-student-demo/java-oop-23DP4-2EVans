package rvt.shop;

import java.util.Scanner;

/**
 * Store class handles the shopping interface for customers.
 * Manages warehouse and processes customer purchases.
 */
public class Store {

    private Warehouse warehouse;
    private Scanner scanner;

    /**
     * Constructor initializes the store with a warehouse and scanner for input.
     * @param warehouse the warehouse containing products
     * @param scanner the scanner for user input
     */
    public Store(Warehouse warehouse, Scanner scanner) {
        this.warehouse = warehouse;
        this.scanner = scanner;
    }

    /**
     * Handles the customer's visit to the store: displays products,
     * takes orders, checks stock, and processes the cart.
     * @param customer the name of the customer
     */
    public void shop(String customer) {
        ShoppingCart cart = new ShoppingCart();
        System.out.println("Welcome to the store " + customer);
        System.out.println("our selection:");

        // Display all available products
        for (String product : this.warehouse.products()) {
            System.out.println(product);
        }

        // Loop to take customer input until they press enter
        while (true) {
            System.out.print("What to put in the cart (press enter to go to the register): ");
            String product = scanner.nextLine();
            if (product.isEmpty()) {
                break; // Exit loop when empty input
            }

            // Try to take the product from warehouse; if successful, add to cart
            if (this.warehouse.take(product)) {
                cart.add(product, this.warehouse.price(product));
            } else {
                System.out.println("Product not available or out of stock.");
            }
        }

        System.out.println("your shoppingcart contents:");
        cart.print();
        System.out.println("total: " + cart.price());
    }
}