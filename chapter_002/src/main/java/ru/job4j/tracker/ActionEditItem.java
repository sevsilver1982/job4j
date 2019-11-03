package ru.job4j.tracker;

public class ActionEditItem extends ActionTracker {
    public ActionEditItem() {
        super("Edit item", true);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== " + super.getName() + " ====");
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (item.getId() != null && item.getName() != null) {
            Item itemNew = new Item(input.askString("Enter new item name: "));
            itemNew.setId(item.getId());
            if (tracker.replace(item.getId(), itemNew)) {
                System.out.println("id: " + item.getId() + "; name: " + item.getName() + " replaced to id: " + itemNew.getId() + "; name: " + itemNew.getName());
            }
        } else {
            System.out.println("Item not found");
        }
        return super.execute(input, tracker);
    }
}
