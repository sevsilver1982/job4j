package ru.job4j.tracker;

public class ActionShowAll extends ActionTracker {

    public ActionShowAll() {
        super("Show all items");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        Item[] items = tracker.findAll();
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println(String.format("id: %s; name: %s", item.getId(), item.getName()));
            }
        } else {
            System.out.println("Items not found");
        }
        return true;
    }

}