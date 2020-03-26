package lsp.store;

import java.util.function.Predicate;

public class Trash<T extends AbstractProduct> extends AbstractStoreHouse<T> {

    public Trash(String name, Predicate<T> condition) {
        super(name, condition);
    }

}