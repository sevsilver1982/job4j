package tree;

import java.util.*;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> tmpParent = findBy(parent);
        Optional<Node<E>> tmpChild = findBy(child);
        if (tmpParent.isPresent() && tmpChild.isEmpty()) {
            Node<E> node = new Node<E>(child);
            List<Node<E>> listNode = new ArrayList<>();
            listNode.add(node);
            tmpParent.get().children.addAll(listNode);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

}
