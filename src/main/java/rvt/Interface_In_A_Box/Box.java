package rvt.Interface_In_A_Box;

import java.util.HashSet;

public class Box implements Packable {
    private double maxCapacity;
    private HashSet<Packable> items;

    public Box(double maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.items = new HashSet<>();
    }

    public void add(Packable item) {
        if (this.weight() + item.weight() <= this.maxCapacity) {
            this.items.add(item);
        }
    }

    @Override
    public double weight() {
        double totalWeight = 0;
        for (Packable item : this.items) {
            totalWeight += item.weight();
        }
        return totalWeight;
    }

    @Override
    public String toString() {
        return "Box: " + this.items.size() + " items, total weight " + this.weight() + " kg";
    }
}