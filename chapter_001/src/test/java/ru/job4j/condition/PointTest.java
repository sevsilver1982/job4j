package ru.job4j.condition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {

    @Test
    public void distance() {
        assertEquals(
                2.0,
                Point.distance(0, 0, 2, 0)
        );
    }

}