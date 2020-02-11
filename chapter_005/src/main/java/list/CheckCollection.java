package list;

import simple.Node;

public class CheckCollection<T> {

    public boolean hasCycle(Node<T> first) {
        Node<T> turtle = first;
        Node<T> hare = first;
        while (hare != null && hare.getNext() != null) {
            turtle = turtle.getNext();
            hare = hare.getNext().getNext();
            if (turtle == hare) {
                return true;
            }
        }
        return false;
    }

}