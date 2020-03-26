package lsp.store;

import java.util.function.Predicate;

public class Warehouse<T extends AbstractProduct> extends AbstractStoreHouse<T> {

    public Warehouse(String name, Predicate<T> condition) {
        super(name, condition);
    }

}