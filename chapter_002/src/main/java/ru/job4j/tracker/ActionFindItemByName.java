package ru.job4j.tracker;

public class ActionFindItemByName extends ActionTracker  {
    public ActionFindItemByName() {
        super("Find item by name", true);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println(String.format("=== %s ====", super.getName()));
        String name = input.askString("Enter item name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                System.out.println(String.format("id: %s; name: %s", items[i].getId(), items[i].getName()));
            }
        } else {
            System.out.println("Item not found");
        }
        return super.execute(input, tracker);
    }
}
