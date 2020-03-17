package tracker.actions;

import tracker.ITracker;
import tracker.Item;
import tracker.input.Input;

public class ReplaceItem extends ActionTracker {

    public ReplaceItem() {
        super("Replace item");
    }

    @Override
    public boolean action(Input input, ITracker tracker) {
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (item.getId() != null && item.getName() != null) {
            tracker.replace(
                    item.getId(),
                    new Item(input.askString("Enter new item name: "))
            );
            System.out.println(String.format("%s renamed", item.toString()));
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}