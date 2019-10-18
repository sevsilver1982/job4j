package ru.job4j.tracker;

public class Item {

    public Item() {
        super();
        System.out.println("load Item()");
    }

    public Item(String name) {
        System.out.println("load Item(String name)");
    }

    public static void main(String[] args) {
        Item item = new Item();
        Bug bug = new Bug("1");
    }

}
