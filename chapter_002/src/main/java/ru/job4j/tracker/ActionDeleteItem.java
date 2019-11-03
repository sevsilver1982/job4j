package ru.job4j.tracker;

public class ActionDeleteItem extends ActionTracker {
    public ActionDeleteItem() {
        super("Delete item", true);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== " + super.getName() + " ====");
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (tracker.delete(item.getId())) {
            System.out.println("id: " + item.getId() + "; name: " + item.getName() + " deleted");
        } else {
            System.out.println("Item not found");
        }
        return super.execute(input, tracker);
    }
}
