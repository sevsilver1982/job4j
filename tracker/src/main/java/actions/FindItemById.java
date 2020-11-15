package actions;

import input.IInput;
import items.Item;

import java.io.OutputStream;
import java.util.UUID;

public class FindItemById extends AbstractAction {

    public FindItemById() {
        super("Find item by id");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        try {
            OutputStream outputStream = tracker.getOutput();
            Item item = tracker.findById(
                    UUID.fromString(input.askString("Enter item id: "))
            );
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