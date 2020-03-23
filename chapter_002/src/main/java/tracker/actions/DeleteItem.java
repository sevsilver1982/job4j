package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;
import tracker.items.Item;

public class DeleteItem extends AbstractAction {

    public DeleteItem() {
        super("Delete item");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (tracker.delete(item.getId())) {
            System.out.println(item.toString());
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}