package list;

import org.junit.jupiter.api.Test;
import simple.Node;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckCollectionTest {

    @Test
    public void hasCycleTrue() {
        CheckCollection<Integer> checkList = new CheckCollection<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);

        first.setNext(two);
        two.setNext(third);
        third.setNext(first);
        assertThat(checkList.hasCycle(first), is(true));

        third.setNext(two);
        assertThat(checkList.hasCycle(first), is(true));
    }

    @Test
    public void hasCycleFalse() {
        CheckCollection<Integer> checkList = new CheckCollection<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);

        first.setNext(two);
        two.setNext(third);

        assertThat(checkList.hasCycle(first), is(false));
    }

}