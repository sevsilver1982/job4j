package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class FindItemById extends ActionTracker {

    public FindItemById() {
        super("Find item by id");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        String id = input.askString("Enter item id: ");
        Item item = tracker.findById(id);
        if (item.getId() != null) {
            System.out.println(String.format("%s found by id", item.toString()));
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}