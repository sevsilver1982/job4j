package simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleListTest {

    @Test
    public void getSize() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(
                3,
                list.getSize()
        );
    }

    @Test
    public void add() {
        SimpleList<Integer> list = new SimpleList<>();
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
    public void getFirst() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals(
                1,
                list.getFirst().getData()
        );
    }

    @Test
    public void getLast() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals(
                5,
                list.getLast().getData()
        );
    }

    @Test
    public void get() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
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
        assertEquals(
                5,
                list.get(4)
        );
    }

    @Test
    public void delete() {
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> {
                    SimpleList<Integer> list = new SimpleList<>();
                    list.add(1);
                    list.add(2);
                    list.add(3);
                    list.add(4);
                    list.add(5);

                    list.delete(0);
                    assertEquals(
                            4,
                            list.getSize()
                    );
                    assertEquals(
                            2,
                            list.get(0)
                    );
                    assertEquals(
                            3,
                            list.get(1)
                    );
                    assertEquals(
                            4,
                            list.get(2)
                    );
                    assertEquals(
                            5,
                            list.get(3)
                    );
                    list.delete(1);
                    assertEquals(
                            3,
                            list.getSize()
                    );
                    assertEquals(
                            2,
                            list.get(0)
                    );
                    assertEquals(
                            4,
                            list.get(1)
                    );
                    assertEquals(
                            5,
                            list.get(2)
                    );
                    list.delete(2);
                    assertEquals(
                            2,
                            list.getSize()
                    );
                    assertEquals(
                            2,
                            list.get(0)
                    );
                    assertEquals(
                            4,
                            list.get(1)
                    );
                    list.delete(2);
                });
    }

    @Test
    public void deleteLast() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        assertEquals(
                1,
                list.getLast().getData()
        );
        list.add(2);
        assertEquals(
                2,
                list.getLast().getData()
        );
        list.add(3);
        assertEquals(
                3,
                list.getLast().getData()
        );
        assertEquals(
                3,
                list.getSize()
        );
        assertEquals(
                3,
                list.getLast().getData()
        );
        assertEquals(
                3,
                list.deleteLast()
        );
        assertEquals(
                2,
                list.getSize()
        );
        assertEquals(
                2,
                list.getLast().getData()
        );
        assertEquals(
                2,
                list.deleteLast()
        );
        assertEquals(
                1,
                list.getSize()
        );
        assertEquals(
                1,
                list.getLast().getData()
        );
        assertEquals(
                1,
                list.deleteLast()
        );
        assertEquals(
                0,
                list.getSize()
        );
    }

}