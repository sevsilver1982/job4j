package actions;

import input.IInput;
import items.Item;

import java.io.OutputStream;
import java.util.UUID;

public class ReplaceItem extends AbstractAction {

    public ReplaceItem() {
        super("Replace item");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        try {
            OutputStream outputStream = tracker.getOutput();
            Item item = tracker.findById(
                    UUID.fromString(input.askString("Enter item id: "))
            );
            if (item.getId() != null && item.getName() != null) {
                tracker.replace(
                        item.getId(),
                        new Item(
                                item.getId(),
                                input.askString("Enter new item name: ")
                        )
                );
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