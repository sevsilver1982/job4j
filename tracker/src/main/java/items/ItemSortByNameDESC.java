package items;

import java.util.Comparator;

public class ItemSortByNameDESC implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
        return item2.getName().compareTo(item1.getName());
    }

}
