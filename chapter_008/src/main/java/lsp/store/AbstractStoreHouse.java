package lsp.store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStoreHouse<T extends AbstractProduct> {
    private String name;
    private List<T> productList = new ArrayList<>();
    private Predicate<T> condition;

    public AbstractStoreHouse(String name, Predicate<T> condition) {
        this.name = name;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getProductList() {
        return productList;
    }

    public Predicate<T> getCondition() {
        return condition;
    }

    public void setCondition(Predicate<T> condition) {
        this.condition = condition;
    }

    public boolean add(T product) {
        return productList.add(product);
    }

    @Override
    public String toString() {
        return String.format("Name: %s; Product count: %s", name, productList.size());
    }

}