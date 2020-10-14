package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;
import tracker.items.Item;

import java.io.OutputStream;

public class DeleteItem extends AbstractAction {

    public DeleteItem() {
        super("Delete item");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        try {
            OutputStream outputStream = tracker.getOutput();
            Item item = tracker.findById(input.askString("Enter item id: "));
            if (!item.isEmpty()) {
                if (tracker.delete(item.getId())) {
                    outputStream.write((item.toString() + "\n").getBytes());
                }
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