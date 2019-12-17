package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.List;

public class ShowAll extends ActionTracker {

    public ShowAll() {
        super("Show all items");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        List<Item> items = tracker.findAllItems();
        if (items.size() > 0) {
            for (Item item : items) {
                System.out.println(item.toString());
            }
        } else {
            System.out.println("Items not found");
        }
        return true;
    }

}