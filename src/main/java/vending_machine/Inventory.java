package vending_machine;

import java.util.ArrayDeque;
import java.util.Deque;

public class Inventory {
    private final String id;
    private Deque<Item> inventory = new ArrayDeque<>();
    private String label;
    private float price;

    public Inventory(String id, String label,float price) {
        this.id = id;
        this.label = label;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addItem(Item item) {
        throw new UnsupportedOperationException();
    }


    public void removeItem() {
        throw new UnsupportedOperationException();
    }
}
