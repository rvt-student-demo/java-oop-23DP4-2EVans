package rvt.shop;


public class Product_stock_balance {
    public int stock(String product) {
        return 0;
    }
    public boolean take(String product) {
        return false;
    }
    Warehouse warehouse = new Warehouse();

    public Product_stock_balance() {
        warehouse.addProduct("coffee", 5, 1);

        System.out.println("stock:");
        System.out.println("coffee: " + warehouse.stock("coffee"));
        System.out.println("sugar: " + warehouse.stock("sugar"));

        System.out.println("taking coffee " + warehouse.take("coffee"));
        System.out.println("taking coffee " + warehouse.take("coffee"));
        System.out.println("taking sugar " + warehouse.take("sugar"));

        System.out.println("stock:");
        System.out.println("coffee:  " + warehouse.stock("coffee"));
        System.out.println("sugar: " + warehouse.stock("sugar"));
    }
}
