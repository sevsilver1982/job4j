package ru.job4j.tracker;

public class ActionDeleteItem extends ActionTracker {
    public ActionDeleteItem() {
        super("Delete item", true);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println(String.format("=== %s ====", super.getName()));
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (tracker.delete(item.getId())) {
            System.out.println(String.format("id: %s; name: %s deleted", item.getId(), item.getName()));
        } else {
            System.out.println("Item not found");
        }
        return super.execute(input, tracker);
    }
}
