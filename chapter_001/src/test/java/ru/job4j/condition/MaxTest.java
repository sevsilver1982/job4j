package ru.job4j.condition;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MaxTest {

    @Test
    public void whenMaxFirst2param() {
        int result = Max.max(5, 1);
        assertThat(result, is(5));
    }

    @Test
    public void whenMaxFirst3param() {
        int result = Max.max(15, 5, 10);
        assertThat(result, is(15));
    }

    @Test
    public void whenMaxFirst4param() {
        int result = Max.max(20, 5, 10, 11);
        assertThat(result, is(20));
    }

    @Test
    public void whenMaxSecond2param() {
        int result = Max.max(3, 5);
        assertThat(result, is(5));
    }

    @Test
    public void whenMaxSecond3param() {
        int result = Max.max(3, 15, 2);
        assertThat(result, is(15));
    }

    @Test
    public void whenMaxSecond4param() {
        int result = Max.max(3, 8, 2, 7);
        assertThat(result, is(8));
    }

    @Test
    public void whenMaxThird3param() {
        int result = Max.max(3, 5, 15);
        assertThat(result, is(15));
    }

    @Test
    public void whenMaxThird4param() {
        int result = Max.max(3, 5, 10, 4);
        assertThat(result, is(10));
    }

    @Test
    public void whenMaxFourth4param() {
        int result = Max.max(3, 5, 10, 40);
        assertThat(result, is(40));
    }

    @Test
    public void whenEquals() {
        int result = Max.max(2, 2);
        assertThat(result, is(2));
    }

}