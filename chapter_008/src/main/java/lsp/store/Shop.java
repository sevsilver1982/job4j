package lsp.store;

import java.util.function.Predicate;

public class Shop<T extends AbstractProduct> extends AbstractStoreHouse<T> {

    public Shop(String name, Predicate<T> condition) {
        super(name, condition);
    }

}