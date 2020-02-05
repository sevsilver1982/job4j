package list.container;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleContainerTest {

    @Test
    public void add() {
        SimpleContainer<Integer> list = new SimpleContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        SimpleContainer<Integer> list = new SimpleContainer<>();
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

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        SimpleContainer<Integer> list = new SimpleContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.getSize(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void delete() {
        SimpleContainer<Integer> list = new SimpleContainer<>();
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
    public void get() {
        SimpleContainer<Integer> list = new SimpleContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(1), is(2));
    }

}