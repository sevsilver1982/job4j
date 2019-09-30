package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SqMaxTest {

    @Test
    public void whenMaxFirst() {
        int result = SqMax.max(40, 30, 20, 10);
        assertThat(result, is(40));
    }

    @Test
    public void whenMaxSecond() {
        int result = SqMax.max(30, 40, 20, 10);
        assertThat(result, is(40));
    }

    @Test
    public void whenMaxThird() {
        int result = SqMax.max(30, 40, 50, 10);
        assertThat(result, is(50));
    }

    @Test
    public void whenMaxFourth() {
        int result = SqMax.max(30, 40, 50, 100);
        assertThat(result, is(100));
    }

    @Test
    public void whenEquals() {
        int result = SqMax.max(100, 100, 100, 100);
        assertThat(result, is(100));
    }

}
