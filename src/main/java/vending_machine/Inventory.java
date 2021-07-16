package vending_machine;

import exceptions.InsufficientInventoryException;

import java.util.*;

public class Inventory {
    private final String id;
    private final Deque<Item> items = new ArrayDeque<>();
    private String label;
    private float price;

    public Inventory(String id, String label, float price) {
        this(id, label, price, new ArrayList<>());
    }

    public Inventory(String id, String label, float price, List<Item> itemList) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        if (itemList == null) {
            throw new IllegalArgumentException("items cannot be null");
        }

        this.id = id;
        this.label = label;
        this.price = price;
        for (Item item : itemList) {
            this.items.addFirst(item);
        }
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

    public Item removeItem() throws InsufficientInventoryException {
        if (items.isEmpty()) {
            throw new InsufficientInventoryException();
        }
        return items.pop();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory1 = (Inventory) o;
        return inventory1.price == price &&
                id.equals(inventory1.id) &&
                Arrays.equals(items.toArray(), inventory1.items.toArray()) &&
                Objects.equals(label, inventory1.label);
    }
}
