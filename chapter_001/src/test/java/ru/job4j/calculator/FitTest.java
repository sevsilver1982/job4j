package ru.job4j.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FitTest {

    @Test
    public void manWeight() {
        double height = 184;
        double expected = 96.6;
        double actual = Fit.manWeight(height);
        assertEquals(expected, actual, 0);
    }

    @Test
    public void womanWeight() {
        double height = 180;
        double expected = 80.5;
        double actual = Fit.womanWeight(height);
        assertEquals(expected, actual, 0);
    }

}
