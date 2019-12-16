package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.items.Item;
import ru.job4j.tracker.Tracker;

public class RenameItem extends ActionTracker {

    public RenameItem() {
        super("Rename item");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (item.getId() != null && item.getName() != null) {
            tracker.rename(item.getId(), input.askString("Enter new item name: "));
            System.out.println(String.format("%s renamed", item.toString()));
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}