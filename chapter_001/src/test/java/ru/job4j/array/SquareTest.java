package ru.job4j.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SquareTest {

    @Test
    public void whenBound3Then149() {
        assertArrayEquals(
                new int[] {1, 4, 9},
                new Square().calculate(3)
        );
    }

}