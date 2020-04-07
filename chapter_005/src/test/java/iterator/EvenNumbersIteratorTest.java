package iterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class EvenNumbersIteratorTest {
    private Iterator<Integer> it = new EvenNumbersIterator(
            new int[]{1, 2, 3, 4, 5, 6, 7}
            );

    @Test
    public void shouldReturnEvenNumbersSequentially() {
        assertThrows(
                NoSuchElementException.class,
                () -> {
                    assertTrue(it.hasNext());
                    assertEquals(
                            2,
                            it.next()
                    );
                    assertTrue(it.hasNext());
                    assertEquals(
                            4,
                            it.next()
                    );
                    assertTrue(it.hasNext());
                    assertEquals(
                            6,
                            it.next()
                    );
                    assertFalse(it.hasNext());
                    it.next();
                });
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertEquals(
                2,
                it.next()
        );
        assertEquals(
                4,
                it.next()
        );
        assertEquals(
                6,
                it.next()
        );
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[] {1});
        assertFalse(
                it.hasNext()
        );
    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenNumbersIterator(new int[] {2, 4, 6, 8});
        assertTrue(it.hasNext());
        assertEquals(
                2,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                4,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                6,
                it.next()
        );
        assertTrue(it.hasNext());
        assertEquals(
                8,
                it.next()
        );
    }

}