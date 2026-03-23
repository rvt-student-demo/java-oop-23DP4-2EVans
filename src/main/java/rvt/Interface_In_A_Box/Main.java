package rvt.Interface_In_A_Box;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Fyodor Dostoevsky", "Crime and Punishment", 2);
        Book book2 = new Book("Robert Martin", "Clean Code", 1);
        Book book3 = new Book("Kent Beck", "Test Driven Development", 0.5);

        CD cd1 = new CD("Pink Floyd", "Dark Side of the Moon", 1973);
        CD cd2 = new CD("Wigwam", "Nuclear Nightclub", 1975);
        CD cd3 = new CD("Rendezvous Park", "Closer to Being Here", 2012);

        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);
        System.out.println(cd1);
        System.out.println(cd2);
        System.out.println(cd3);

        Box box = new Box(10);

        Book duplicateBook = new Book("Fyodor Dostoevsky", "Crime and Punishment", 2);
        box.add(duplicateBook);
        box.add(duplicateBook); // Try to add the same book twice - should be prevented
        box.add(new Book("Robert Martin", "Clean Code", 1));
        box.add(new Book("Kent Beck", "Test Driven Development", 0.7));

        box.add(new CD("Pink Floyd", "Dark Side of the Moon", 1973));
        box.add(new CD("Wigwam", "Nuclear Nightclub", 1975));
        box.add(new CD("Rendezvous Park", "Closer to Being Here", 2012));

        System.out.println(box);

        // Test boxes in boxes
        Box smallBox = new Box(5);
        smallBox.add(new Book("Test Book", "Test", 1.0));
        smallBox.add(new CD("Test Artist", "Test CD", 2000));

        Box bigBox = new Box(20);
        bigBox.add(smallBox);
        bigBox.add(new Book("Another Book", "Another", 2.0));

        System.out.println("Small box: " + smallBox);
        System.out.println("Big box: " + bigBox);

        // Try adding box to itself
        Box selfBox = new Box(10);
        selfBox.add(new Book("Self Book", "Self", 1.0));
        System.out.println("Before adding to itself: " + selfBox);
        // selfBox.add(selfBox); // This would cause infinite recursion in weight()
        // System.out.println("After adding to itself: " + selfBox); // Stack overflow
    }
}