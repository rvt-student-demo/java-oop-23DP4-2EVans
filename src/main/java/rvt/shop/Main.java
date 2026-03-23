package rvt.shop;


import java.util.Scanner;

/**
 * Main class demonstrates the online shop system.
 * Stocks the warehouse and starts the shopping process for a customer.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize warehouse and add products with prices and stock
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct("coffee", 5, 10);
        warehouse.addProduct("milk", 3, 20);
        warehouse.addProduct("cream", 2, 55);
        warehouse.addProduct("bread", 7, 8);

        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Create store and start shopping for John
        Store store = new Store(warehouse, scanner);
        store.shop("John");
    }
}
