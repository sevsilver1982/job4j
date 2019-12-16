package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.items.Item;
import ru.job4j.tracker.Tracker;

public class DeleteItem extends ActionTracker {

    public DeleteItem() {
        super("Delete item");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (tracker.delete(item)) {
            System.out.println(String.format("%s deleted", item.toString()));
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}