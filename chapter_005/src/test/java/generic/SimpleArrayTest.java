package generic;

import org.junit.jupiter.api.Test;
import simple.SimpleArray;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleArrayTest {

    @Test
    public void add() {
        SimpleArray<Integer> objects = new SimpleArray<>(5);
        objects.add(1);
        assertEquals(
                1,
                objects.get(0)
        );
    }

    @Test
    public void set() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            SimpleArray<Integer> objects = new SimpleArray<>(5);
            objects.add(1);
            assertEquals(
                    1,
                    objects.get(0)
            );
            objects.set(0, 2);
            assertEquals(
                    2,
                    objects.get(0)
            );
            objects.set(1, 2);
        });
    }

    @Test
    public void remove() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> {
                    SimpleArray<Integer> objects = new SimpleArray<>(5);
                    objects.add(0);
                    objects.add(1);
                    objects.add(2);
                    objects.add(3);
                    objects.add(4);
                    objects.remove(1);
                    objects.remove(1);
                    objects.remove(1);
                    objects.add(9);
                    assertEquals(
                            0,
                            objects.get(0)
                    );
                    assertEquals(
                            4,
                            objects.get(1)
                    );
                    assertEquals(
                            9,
                            objects.get(2)
                    );
                    objects.get(3);
                });
    }

    @Test
    public void get() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> {
                    SimpleArray<Integer> objects = new SimpleArray<>(5);
                    objects.add(1);
                    objects.add(2);
                    objects.add(3);
                    assertEquals(
                            1,
                            objects.get(0)
                    );
                    assertEquals(
                            2,
                            objects.get(1)
                    );
                    assertEquals(
                            3,
                            objects.get(2)
                    );
                    objects.get(3);
                });
    }

    @Test
    public void iterator() {
        SimpleArray<Integer> objects = new SimpleArray<>(5);
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.add(4);
        objects.add(5);
        Iterator<Integer> it = objects.iterator();
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
        assertFalse(it.hasNext());
    }

    @Test
    public void iteratorNoSuchElementException() {
        assertThrows(
                NoSuchElementException.class,
                () -> {
                    SimpleArray<Integer> objects = new SimpleArray<>(5);
                    objects.add(1);
                    objects.add(2);
                    objects.add(3);
                    Iterator<Integer> it = objects.iterator();
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
                    assertFalse(it.hasNext());
                    it.next();
                });
    }

}