package ru.job4j.tracker;

public class ActionNewItem extends ActionTracker {
    public ActionNewItem() {
        super("Add new item", true);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println(String.format("=== %s ====", super.getName()));
        Item item = new Item(input.askString("Enter name: "));
        tracker.add(item);
        return super.execute(input, tracker);
    }

}
