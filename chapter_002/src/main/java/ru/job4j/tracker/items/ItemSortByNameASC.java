package ru.job4j.tracker.items;

import ru.job4j.tracker.Item;

import java.util.Comparator;

public class ItemSortByNameASC implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
        return item1.getName().compareTo(item2.getName());
    }

}