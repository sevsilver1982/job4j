package ru.job4j.condition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {

    @Test
    public void whenExist() {
        assertEquals(
                100,
                new Triangle(
                        new Point(0, 0),
                        new Point(10, 10),
                        new Point(20, 0)
                ).area()
        );
    }

    @Test
    public void whenNotExist() {
        assertEquals(
                37.5,
                new Triangle(
                        new Point(0, 0),
                        new Point(10, 10),
                        new Point(10, 2.5)
                ).area()
        );
    }

}