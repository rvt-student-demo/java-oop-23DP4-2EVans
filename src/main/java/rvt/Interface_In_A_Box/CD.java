package rvt.Interface_In_A_Box;

public class CD implements Packable {
    private String artist;
    private String name;
    private int year;

    public CD(String artist, String name, int year) {
        this.artist = artist;
        this.name = name;
        this.year = year;
    }

    @Override
    public double weight() {
        return 0.1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CD cd = (CD) obj;
        return year == cd.year &&
               artist.equals(cd.artist) &&
               name.equals(cd.name);
    }

    @Override
    public int hashCode() {
        return artist.hashCode() + name.hashCode() + year;
    }

    @Override
    public String toString() {
        return this.artist + ": " + this.name + " (" + this.year + ")";
    }
}