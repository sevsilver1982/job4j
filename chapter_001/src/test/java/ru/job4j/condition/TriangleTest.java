package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TriangleTest {

    @Test
    public void whenExist() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point p3 = new Point(20, 0);
        double result = new Triangle(p1, p2, p3).area();
        assertThat(result, is(100.0));
    }

    @Test
    public void whenNotExist() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point p3 = new Point(10, 2.5);
        double result = new Triangle(p1, p2, p3).area();
        assertThat(result, is(37.5));
    }

}
