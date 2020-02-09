package simple;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleListTest {

    @Test
    public void getSize() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void add() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test
    public void getFirst() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertThat(list.getFirst().getData(), is(1));
    }

    @Test
    public void getLast() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertThat(list.getLast().getData(), is(5));
    }

    @Test
    public void get() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.get(3), is(4));
        assertThat(list.get(4), is(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void delete() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.delete(0);
        assertThat(list.getSize(), is(4));
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(3));
        assertThat(list.get(2), is(4));
        assertThat(list.get(3), is(5));

        list.delete(1);
        assertThat(list.getSize(), is(3));
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(4));
        assertThat(list.get(2), is(5));

        list.delete(2);
        assertThat(list.getSize(), is(2));
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(4));
        list.delete(2);
    }

    @Test
    public void deleteLast() {
        SimpleList<Integer> list = new SimpleList<>();
        list.add(1);
        assertThat(list.getLast().getData(), is(1));
        list.add(2);
        assertThat(list.getLast().getData(), is(2));
        list.add(3);
        assertThat(list.getLast().getData(), is(3));

        assertThat(list.getSize(), is(3));
        assertThat(list.getLast().getData(), is(3));
        assertThat(list.deleteLast(), is(3));

        assertThat(list.getSize(), is(2));
        assertThat(list.getLast().getData(), is(2));
        assertThat(list.deleteLast(), is(2));

        assertThat(list.getSize(), is(1));
        assertThat(list.getLast().getData(), is(1));
        assertThat(list.deleteLast(), is(1));

        assertThat(list.getSize(), is(0));
    }
}