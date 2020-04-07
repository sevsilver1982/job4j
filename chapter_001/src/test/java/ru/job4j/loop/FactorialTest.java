package ru.job4j.loop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {

    @Test
    public void whenCalculateFactorialForFiveThenOneHundredTwenty() {
        assertEquals(
                120,
                new Factorial().calc(5)
        );
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        assertEquals(
                1,
                new Factorial().calc(0)
        );
    }

}