package ru.job4j.tracker;

public class ActionFindItemById extends ActionTracker {

    public ActionFindItemById() {
        super("Find item by id");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        String id = input.askString("Enter item id: ");
        Item item = tracker.findById(id);
        if (item.getId() != null) {
            System.out.println(String.format("id: %s; name: %s", item.getId(), item.getName()));
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}