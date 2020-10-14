package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;
import tracker.items.Item;

import java.io.OutputStream;

public class FindItemById extends AbstractAction {

    public FindItemById() {
        super("Find item by id");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        try {
            OutputStream outputStream = tracker.getOutput();
            String id = input.askString("Enter item id: ");
            Item item = tracker.findById(id);
            if (item.getId() != null) {
                outputStream.write((item.toString() + "\n").getBytes());
            } else {
                outputStream.write("Item not found\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}