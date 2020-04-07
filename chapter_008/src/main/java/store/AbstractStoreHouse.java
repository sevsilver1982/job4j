package store;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStoreHouse implements IStore {
    private String name;
    private List<IProduct> productList = new ArrayList<>();
    private IStoreCondition storeCondition;

    public AbstractStoreHouse(String name, IStoreCondition storeCondition) {
        this.name = name;
        this.storeCondition = storeCondition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IProduct> getProductList() {
        return new ArrayList<>(productList);
    }

    public IStoreCondition getCondition() {
        return storeCondition;
    }

    public void setCondition(IStoreCondition storeCondition) {
        this.storeCondition = storeCondition;
    }

    public boolean add(IProduct product) {
        return productList.add(product);
    }

    public boolean remove(IProduct product) {
        return productList.remove(product);
    }

    @Override
    public String toString() {
        return String.format("Name: %s; Product count: %s", name, productList.size());
    }

}