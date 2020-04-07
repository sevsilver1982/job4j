package list;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListContainerTest {

    @Test
    public void add() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(
                1,
                list.get(0)
        );
        assertEquals(
                2,
                list.get(1)
        );
        assertEquals(
                3,
                list.get(2)
        );
    }

    @Test
    public void get() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
        list.add(1);
        assertEquals(
                1,
                list.get(0)
        );
        list.add(2);
        assertEquals(
                2,
                list.get(1)
        );
        list.add(3);
        assertEquals(
                3,
                list.get(2)
        );
    }

    @Test
    public void iterator() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator it = list.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
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
        assertFalse(it.hasNext());
    }

    @Test
    public void iteratorNoSuchElementException() {
        assertThrows(
                NoSuchElementException.class,
                () -> {
                    LinkedListContainer<Integer> list = new LinkedListContainer<>();
                    list.add(1);
                    list.add(2);
                    list.add(3);
                    Iterator<Integer> it = list.iterator();
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

    @Test
    public void iteratorConcurrentModificationException() {
        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    LinkedListContainer<Integer> list = new LinkedListContainer<>();
                    list.add(1);
                    list.add(2);
                    list.add(3);
                    Iterator<Integer> it = list.iterator();
                    list.add(4);
                    it.next();
                });
    }

}