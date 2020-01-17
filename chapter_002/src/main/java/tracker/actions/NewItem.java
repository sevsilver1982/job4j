package tracker.actions;

import tracker.Item;
import tracker.Tracker;
import tracker.input.Input;

public class NewItem extends ActionTracker {

    public NewItem() {
        super("Add new item");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        Item item = new Item(input.askString("Enter name: "));
        tracker.add(item);
        System.out.println(String.format("%s deleted", item.toString()));
        return true;
    }

}