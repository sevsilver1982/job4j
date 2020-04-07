package ru.job4j.condition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxTest {

    @Test
    public void whenMaxFirst2param() {
        assertEquals(
                5,
                Max.max(5, 1)
        );
    }

    @Test
    public void whenMaxFirst3param() {
        assertEquals(
                15,
                Max.max(15, 5, 10)
        );
    }

    @Test
    public void whenMaxFirst4param() {
        assertEquals(
                20,
                Max.max(20, 5, 10, 11)
        );
    }

    @Test
    public void whenMaxSecond2param() {
        assertEquals(
                5,
                Max.max(3, 5)
        );
    }

    @Test
    public void whenMaxSecond3param() {
        assertEquals(
                15,
                Max.max(3, 15, 2)
        );
    }

    @Test
    public void whenMaxSecond4param() {
        assertEquals(
                8,
                Max.max(3, 8, 2, 7)
        );
    }

    @Test
    public void whenMaxThird3param() {
        assertEquals(
                15,
                Max.max(3, 5, 15)
        );
    }

    @Test
    public void whenMaxThird4param() {
        assertEquals(
                10,
                Max.max(3, 5, 10, 4)
        );
    }

    @Test
    public void whenMaxFourth4param() {
        assertEquals(
                40,
                Max.max(3, 5, 10, 40)
        );
    }

    @Test
    public void whenEquals() {
        assertEquals(
                2,
                Max.max(2, 2)
        );
    }

}