package ru.job4j.condition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {

    @Test
    public void distance() {
        double x1 = 0;
        double y1 = 0;
        double x2 = 2;
        double y2 = 0;
        double expected = 2.0;
        double actual = Point.distance(x1, y1, x2, y2);
        assertEquals(expected, actual, 0);
    }

}
