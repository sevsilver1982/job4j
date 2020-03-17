package tracker.actions;

import tracker.ITracker;
import tracker.Item;
import tracker.input.Input;

public class DeleteItem extends ActionTracker {

    public DeleteItem() {
        super("Delete item");
    }

    @Override
    public boolean action(Input input, ITracker tracker) {
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (tracker.delete(item.getId())) {
            System.out.println(String.format("%s deleted", item.toString()));
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}