package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.condition.Point;

public class PointTest {

    @Test
    public void distance() {
        double x1 = 0;
        double y1 = 0;
        double x2 = 2;
        double y2 = 0;
        double expected = 2.0;
        double actual = Point.distance(x1, y1, x2, y2);
        Assert.assertEquals(expected, actual, 0);
    }

}
