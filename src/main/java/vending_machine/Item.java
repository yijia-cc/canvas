package vending_machine;

public class Item {

    private final int id;
    private String name;


    Item (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId () {
        return id;
    }

    public String getName () {
        return name;
    }
}
