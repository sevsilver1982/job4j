package tracker;

import org.junit.Test;
import tracker.items.ItemSortByNameASC;
import tracker.items.ItemSortByNameDESC;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ItemTest {

    @Test
    public void comparatorItemSortByNameASC() {
        assertThat(
                List.of(
                        new Item("Task1"),
                        new Item("Task3"),
                        new Item("Task5"),
                        new Item("Task2"),
                        new Item("Task4")
                ).stream().sorted(new ItemSortByNameASC()).collect(Collectors.toList()),
                is(List.of(
                        new Item("Task1"),
                        new Item("Task2"),
                        new Item("Task3"),
                        new Item("Task4"),
                        new Item("Task5")
                        )
                )
        );
    }

    @Test
    public void comparatorItemSortByNameDESC() {
        assertThat(
                List.of(
                        new Item("Task1"),
                        new Item("Task3"),
                        new Item("Task5"),
                        new Item("Task2"),
                        new Item("Task4")
                ).stream().sorted(new ItemSortByNameDESC()).collect(Collectors.toList()),
                is(List.of(
                        new Item("Task5"),
                        new Item("Task4"),
                        new Item("Task3"),
                        new Item("Task2"),
                        new Item("Task1")
                        )
                )
        );
    }

}