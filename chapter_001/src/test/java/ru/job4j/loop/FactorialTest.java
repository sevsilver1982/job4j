package ru.job4j.loop;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FactorialTest {

    @Test
    public void whenCalculateFactorialForFiveThenOneHundredTwenty() {
        assertThat(new Factorial().calc(5), is(120));
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        assertThat(new Factorial().calc(0), is(1));
    }

}