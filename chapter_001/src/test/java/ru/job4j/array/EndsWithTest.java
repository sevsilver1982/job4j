package ru.job4j.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndsWithTest {

    @Test
    public void whenStartWithPrefixThenTrue() {
        assertTrue(
                EndsWith.endsWith(
                        new char[] {'H', 'e', 'l', 'l', 'o'},
                        new char[] {'l', 'o'}
                        )
        );
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        assertFalse(
                EndsWith.endsWith(
                        new char[] {'H', 'e', 'l', 'l', 'o'},
                        new char[] {'l', 'a'}
                )
        );
    }

}