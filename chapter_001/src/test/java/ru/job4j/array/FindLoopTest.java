package ru.job4j.array;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FindLoopTest {

    @Test
    public void when5Result0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = 0;
        assertThat(result, is(expect));
    }

    @Test
    public void when2NotFound() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 2;
        int result = find.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }

    @Test
    public void whenFind3() {
        int[] input = new int[] {5, 2, 10, 2, 4};
        int value = 2;
        int start = 2;
        int finish = 4;
        int result = FindLoop.indexOf(input, value, start, finish);
        int expect = 3;
        assertThat(result, is(expect));
    }

    @Test
    public void findMinTest1() {
        int result = FindLoop.findMin(new int[] {5, 2, 10, 2, 4}, 2, 4);
        int expect = 2;
        assertThat(result, is(expect));
    }

    @Test
    public void findMinTest2() {
        int result = FindLoop.findMin(new int[] {5, 2, 10, 1, 4, 1, 4, 3, 0, 1}, 0, 9);
        int expect = 0;
        assertThat(result, is(expect));
    }

    @Test
    public void findMinTest3() {
        int[] input = new int[] {5, 2, 10, 2, 4};
        int result = FindLoop.findMin(input, 1, input.length - 1);
        int expect = 2;
        assertThat(result, is(expect));
    }

    @Test
    public void whenSort() {
        int[] input = new int[] {3, 4, 1, 2, 5};
        int[] result = FindLoop.sort(input);
        int[] expect = new int[] {1, 2, 3, 4, 5};
        assertThat(result, is(expect));
    }

}