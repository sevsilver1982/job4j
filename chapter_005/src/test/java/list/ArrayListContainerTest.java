package list;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListContainerTest {

    @Test
    public void add() {
        ArrayListContainer<Integer> list = new ArrayListContainer<>();
        for (int i = 0; i < 250; i++) {
            list.add(i);
        }
        assertEquals(
                250,
                list.getSize()
        );
    }

    @Test
    public void get() {
        ArrayListContainer<Integer> list = new ArrayListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
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
        assertEquals(
                4,
                list.get(3)
        );
    }

    @Test
    public void getIndexOutOfBoundsException() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> {
                    ArrayListContainer<Integer> list = new ArrayListContainer<>();
                    list.get(0);
                    list.add(1);
                    assertEquals(
                            1,
                            list.get(0)
                    );
                    list.get(0);
                });
    }

    @Test
    public void iterator() {
        ArrayListContainer<Integer> list = new ArrayListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
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
                    ArrayListContainer<Integer> list = new ArrayListContainer<>();
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
                    ArrayListContainer<Integer> list = new ArrayListContainer<>();
                    list.add(1);
                    list.add(2);
                    list.add(3);
                    Iterator<Integer> it = list.iterator();
                    list.add(4);
                    it.next();
                });
    }

}