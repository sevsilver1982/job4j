package ru.job4j.collection;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConvertListTest {

    @Test
    public void convertTest() {
        ConvertList list = new ConvertList();
        List<Integer> result = list.convert(Arrays.asList(
                new int[]{1, 2},
                new int[]{3, 4, 5, 6},
                new int[]{7},
                new int[]{8, 9, 10, 11, 12, 13}
                )
        );
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        assertThat(result, is(expect));
    }

    @Test
    public void when7ElementsThen9() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }
}