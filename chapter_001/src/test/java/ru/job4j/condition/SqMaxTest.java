package ru.job4j.condition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqMaxTest {

    @Test
    public void whenMaxFirst() {
        assertEquals(
                40,
                SqMax.max(40, 30, 20, 10)
        );
    }

    @Test
    public void whenMaxSecond() {
        assertEquals(
                40,
                SqMax.max(30, 40, 20, 10)
        );
    }

    @Test
    public void whenMaxThird() {
        assertEquals(
                50,
                SqMax.max(30, 40, 50, 10)
        );
    }

    @Test
    public void whenMaxFourth() {
        assertEquals(
                100,
                SqMax.max(30, 40, 50, 100)
        );
    }

    @Test
    public void whenEquals() {
        assertEquals(
                100,
                SqMax.max(100, 100, 100, 100)
        );
    }

}