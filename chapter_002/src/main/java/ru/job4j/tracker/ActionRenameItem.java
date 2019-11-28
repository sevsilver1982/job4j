package ru.job4j.tracker;

public class ActionRenameItem extends ActionTracker {

    public ActionRenameItem() {
        super("Rename item");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (item.getId() != null && item.getName() != null) {
            Item itemNew = new Item(input.askString("Enter new item name: "));
            itemNew.setId(item.getId());
            if (tracker.rename(item.getId(), itemNew)) {
                System.out.println(String.format("id: %s; name: %s renamed to id: %s; name: %s", item.getId(), item.getName(), itemNew.getId(), itemNew.getName()));
            }
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}