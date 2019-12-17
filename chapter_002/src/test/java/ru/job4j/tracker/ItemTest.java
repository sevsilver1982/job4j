package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.items.ItemSortByNameASC;
import ru.job4j.tracker.items.ItemSortByNameDESC;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ItemTest {

    @Test
    public void comparatorItemSortByNameASC() {
        List<Item> result = Arrays.asList(
                new Item("Task1"),
                new Item("Task3"),
                new Item("Task5"),
                new Item("Task2"),
                new Item("Task4")
        );
        result.sort(new ItemSortByNameASC());
        List<Item> expect = Arrays.asList(
                new Item("Task1"),
                new Item("Task2"),
                new Item("Task3"),
                new Item("Task4"),
                new Item("Task5")
        );
        assertThat(result, is(expect));
    }

    @Test
    public void comparatorItemSortByNameDESC() {
        List<Item> result = Arrays.asList(
                new Item("Task1"),
                new Item("Task3"),
                new Item("Task5"),
                new Item("Task2"),
                new Item("Task4")
        );
        result.sort(new ItemSortByNameDESC());
        List<Item> expect = Arrays.asList(
                new Item("Task5"),
                new Item("Task4"),
                new Item("Task3"),
                new Item("Task2"),
                new Item("Task1")
        );
        assertThat(result, is(expect));
    }

}