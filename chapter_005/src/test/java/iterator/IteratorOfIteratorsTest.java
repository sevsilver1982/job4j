package iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorOfIteratorsTest {
    Iterator<Integer> it = new Converter(
            List.of(
                    Arrays.asList(1, 2, 3).iterator(),
                    Arrays.asList(4, 5, 6).iterator(),
                    Arrays.asList(7, 8, 9).iterator()
            ).iterator()
    ).convert();

    @Test
    public void hasNextNextSequentialInvocation() {
        assertTrue(it.hasNext());
        assertEquals(
                1,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                2,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                3,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                4,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                5,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                6,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                7,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                8,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                9,
                it.next()
        );
        assertFalse(it.hasNext());
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertEquals(
                1,
                it.next()
        );
        assertEquals(
                2,
                it.next()
        );
        assertEquals(
                3,
                it.next()
        );
        assertEquals(
                4,
                it.next()
        );
        assertEquals(
                5,
                it.next()
        );
        assertEquals(
                6,
                it.next()
        );
        assertEquals(
                7,
                it.next()
        );
        assertEquals(
                8,
                it.next()
        );
        assertEquals(
                9,
                it.next()
        );
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertEquals(
                1,
                it.next()
        );
        assertEquals(
                2,
                it.next()
        );
        assertEquals(
                3,
                it.next()
        );
        assertEquals(
                4,
                it.next()
        );
        assertEquals(
                5,
                it.next()
        );
        assertEquals(
                6,
                it.next()
        );
        assertEquals(
                7,
                it.next()
        );
        assertEquals(
                8,
                it.next()
        );
        assertEquals(
                9,
                it.next()
        );
    }

    @Test
    public void nextShouldReturn123456InCaseOfEmptyIterators() {
        Converter converter = new Converter(
                List.of(
                        new ArrayList<Integer>().iterator(),
                        Arrays.asList(1, 2, 3).iterator(),
                        new ArrayList<Integer>().iterator(),
                        Arrays.asList(4, 5, 6).iterator()
                ).iterator()
        );
        it = converter.convert();
        assertEquals(
                1,
                it.next()
        );
        assertEquals(
                2,
                it.next()
        );
        assertEquals(
                3,
                it.next()
        );
        assertEquals(
                4,
                it.next()
        );
        assertEquals(
                5,
                it.next()
        );
        assertEquals(
                6,
                it.next()
        );
    }

    @Test
    public void hasNextShouldReturnFalseInCaseOfEmptyIterators() {
        Converter converter = new Converter(
                List.of(
                        new ArrayList<Integer>().iterator(),
                        new ArrayList<Integer>().iterator(),
                        new ArrayList<Integer>().iterator()
                ).iterator()
        );
        it = converter.convert();
        assertFalse(it.hasNext());
    }

    @Test
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> {
                    Converter converter = new Converter(
                            List.of(
                                    List.of(1, 2, 3).iterator()
                            ).iterator()
                    );
                    it = converter.convert();
                    assertEquals(
                            1,
                            it.next()
                    );
                    assertEquals(
                            2,
                            it.next()
                    );
                    assertEquals(
                            3,
                            it.next()
                    );
                    it.next();
                });
    }

}