package ru.job4j.tracker;

public class ActionFindItemById extends ActionTracker {
    public ActionFindItemById() {
        super("Find item by id", true);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== " + super.getName() + " ====");
        String id = input.askString("Enter item id: ");
        Item item = tracker.findById(id);
        if (item.getId() != null) {
            System.out.println("id: " + item.getId() + "; name: " + item.getName());
        } else {
            System.out.println("Item not found");
        }
        return super.execute(input, tracker);
    }
}