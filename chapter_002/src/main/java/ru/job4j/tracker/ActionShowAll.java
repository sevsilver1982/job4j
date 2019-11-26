package ru.job4j.tracker;

public class ActionShowAll extends ActionTracker {
    public ActionShowAll() {
        super("Show all items", true);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println(String.format("=== %s ====", super.getName()));
        Item[] items = tracker.findAll();
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println(String.format("id: %s; name: %s", item.getId(), item.getName()));
            }
        } else {
            System.out.println("Items not found");
        }
        return super.execute(input, tracker);
    }
}
