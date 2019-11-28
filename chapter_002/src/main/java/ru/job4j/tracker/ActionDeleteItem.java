package ru.job4j.tracker;

public class ActionDeleteItem extends ActionTracker {

    public ActionDeleteItem() {
        super("Delete item");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (tracker.delete(item.getId())) {
            System.out.println(String.format("id: %s; name: %s deleted", item.getId(), item.getName()));
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}