package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class NewItem extends ActionTracker {

    public NewItem() {
        super("Add new item");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        Item item = new Item(input.askString("Enter name: "));
        tracker.add(item);
        System.out.println(String.format("%s deleted", item.toString()));
        return true;
    }

}