package tracker;

import org.junit.jupiter.api.Test;
import tracker.items.Item;
import tracker.items.ItemSortByNameASC;
import tracker.items.ItemSortByNameDESC;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ItemTest {
    private Item task1 = new Item("Task1");
    private Item task2 = new Item("Task2");
    private Item task3 = new Item("Task3");
    private Item task4 = new Item("Task4");
    private Item task5 = new Item("Task5");
    private List<Item> actual = Arrays.asList(task2, task3, task1, task5, task4);

    @Test
    public void comparatorItemSortByNameASC() {
        actual.sort(new ItemSortByNameASC());
        List<Item> expect = Arrays.asList(task1, task2, task3, task4, task5);
        assertThat(actual, is(expect));
    }

    @Test
    public void comparatorItemSortByNameDESC() {
        actual.sort(new ItemSortByNameDESC());
        List<Item> expect = Arrays.asList(task5, task4, task3, task2, task1);
        assertThat(actual, is(expect));
    }

}