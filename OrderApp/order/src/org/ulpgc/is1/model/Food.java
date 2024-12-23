package org.ulpgc.is1.model;

public class Food {
    private final int number;
    private final String name;
    private final String description;
    private final int price;

    public Food(int number, String name, String description, int price) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + " - " + description + " ($" + price + ")";
    }
}
