package ru.job4j.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindLoopTest {

    @Test
    public void when5Result0() {
        assertEquals(
                0,
                new FindLoop().indexOf(
                        new int[] {5, 10, 3},
                        5
                )
        );
    }

    @Test
    public void when2NotFound() {
        assertEquals(
                -1,
                new FindLoop().indexOf(
                        new int[] {5, 10, 3},
                        2
                )
        );
    }

    @Test
    public void whenFind3() {
        assertEquals(
                3,
                FindLoop.indexOf(
                        new int[] {5, 2, 10, 2, 4},
                        2,
                        2,
                        4
                )
        );
    }

    @Test
    public void findMinTest1() {
        assertEquals(
                2,
                FindLoop.findMin(
                        new int[] {5, 2, 10, 2, 4}, 2, 4
                )
        );
    }

    @Test
    public void findMinTest2() {
        assertEquals(
                0,
                FindLoop.findMin(
                        new int[] {5, 2, 10, 1, 4, 1, 4, 3, 0, 1}, 0, 9
                )
        );
    }

    @Test
    public void findMinTest3() {
        int[] input = new int[] {5, 2, 10, 2, 4};
        assertEquals(
                2,
                FindLoop.findMin(
                        input, 1, input.length - 1
                )
        );
    }

    @Test
    public void whenSort() {
        assertArrayEquals(
                new int[] {1, 2, 3, 4, 5},
                FindLoop.sort(
                        new int[] {3, 4, 1, 2, 5}
                        )
                );
    }

}