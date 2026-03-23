package rvt.Interface_In_A_Box;

public class Book implements Packable {
    private String author;
    private String name;
    private double weight;

    public Book(String author, String name, double weight) {
        this.author = author;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public double weight() {
        return this.weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Double.compare(book.weight, weight) == 0 &&
               author.equals(book.author) &&
               name.equals(book.name);
    }

    @Override
    public int hashCode() {
        return author.hashCode() + name.hashCode() + Double.hashCode(weight);
    }

    @Override
    public String toString() {
        return this.author + ": " + this.name;
    }
}