package store;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ControlQuality<T extends IStore> {
    private List<T> storeList;

    public ControlQuality(List<T> storeHouseList) {
        this.storeList = storeHouseList;
    }

    public Optional<List<T>> getStoreListByProductCondition(Predicate<T> condition) {
        return Optional.of(
                storeList.stream()
                        .filter(condition)
                        .collect(Collectors.toList())
        );
    }

    public boolean distribute(IProduct product, Date toDate) {
        Optional<List<T>> list = getStoreListByProductCondition(
                store -> store.getCondition().testCondition(product, toDate)
        );
        if (list.isPresent()) {
            list.get().get(0).add(product);
            return true;
        }
        return false;
    }

    public void resort(Date toDate) {
        storeList.forEach(store ->
            store.getProductList().forEach(product -> {
                if (!store.getCondition().testCondition(product, toDate)) {
                    getStoreListByProductCondition(
                            s -> s.getCondition().testCondition(product, toDate)
                    ).ifPresent(stores -> {
                        stores.get(0).add(product);
                        store.remove(product);
                    });
                }
            })
        );
    }

}