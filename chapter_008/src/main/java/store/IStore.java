package store;

import java.util.List;

public interface IStore {
    String getName();
    void setName(String name);
    List<IProduct> getProductList();
    IStoreCondition getCondition();
    void setCondition(IStoreCondition condition);
    boolean add(IProduct product);
    boolean remove(IProduct product);
}