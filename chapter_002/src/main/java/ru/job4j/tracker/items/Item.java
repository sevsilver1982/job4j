package ru.job4j.tracker.items;

public class Item {
    private String id;
    private String name;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("id: %s; name: %s", this.getId(), this.getName());
    }
}
