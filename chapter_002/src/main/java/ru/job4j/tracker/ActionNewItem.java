package ru.job4j.tracker;

public class ActionNewItem extends ActionTracker {

    public ActionNewItem() {
        super("Add new item");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        Item item = new Item(input.askString("Enter name: "));
        tracker.addItem(item);
        return true;
    }

}