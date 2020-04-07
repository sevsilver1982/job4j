package iterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixIteratorTest {

    private Iterator<Integer> it = new MatrixIterator(
            new int[][] {
                    {1},
                    {2, 3},
                    {4, 5, 6}
            });

    @Test
    public void testsThatNextMethodDoesNotDependsOnPriorHasNextInvocation() {
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
    }

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
        assertFalse(it.hasNext());
    }

}