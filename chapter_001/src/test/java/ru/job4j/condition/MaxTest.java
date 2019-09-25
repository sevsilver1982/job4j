package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MaxTest {

    @Test
    public void whenMaxLeft() {
        int result = Max.max(5, 1);
        assertThat(result, is(5));
    }

    @Test
    public void whenMaxRight() {
        int result = Max.max(3, 5);
        assertThat(result, is(5));
    }

    @Test
    public void whenEquals() {
        int result = Max.max(2, 2);
        assertThat(result, is(2));
    }

}
