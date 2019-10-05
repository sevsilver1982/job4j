package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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