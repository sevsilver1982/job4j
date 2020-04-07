package list;

import org.junit.jupiter.api.Test;
import simple.Node;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(checkList.hasCycle(first));

        third.setNext(two);
        assertTrue(checkList.hasCycle(first));
    }

    @Test
    public void hasCycleFalse() {
        CheckCollection<Integer> checkList = new CheckCollection<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);

        first.setNext(two);
        two.setNext(third);

        assertFalse(checkList.hasCycle(first));
    }

}