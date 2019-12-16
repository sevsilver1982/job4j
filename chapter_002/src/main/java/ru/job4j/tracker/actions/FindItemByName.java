package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.items.Item;
import ru.job4j.tracker.Tracker;

import java.util.List;

public class FindItemByName extends ActionTracker {

    public FindItemByName() {
        super("Find item by name");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        String name = input.askString("Enter item name: ");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                System.out.println(String.format("%s found by name", item.toString()));
            }
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}