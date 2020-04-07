package ru.job4j.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckTest {

    @Test
    public void whenDataEvenMonoByTrueThenTrue() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, true, true};
        assertTrue(
                check.mono(input)
        );
    }

    @Test
    public void whenDataEvenNotMonoByTrueThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, false, true};
        assertFalse(
                check.mono(input)
        );
    }

    @Test
    public void whenDataOddMonoByTrueThenTrue() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, true, true, true};
        assertTrue(
                check.mono(input)
        );
    }

    @Test
    public void whenDataOddNotMonoByTrueThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, false, true, false};
        assertFalse(
                check.mono(input)
        );
    }

}