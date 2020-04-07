package store;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ControlQuality {
    private List<IStore> storeList;

    public ControlQuality(List<IStore> storeHouseList) {
        this.storeList = storeHouseList;
    }

    public Optional<List<IStore>> getStoreListByProductCondition(Predicate<IStore> condition) {
        return Optional.of(
                storeList.stream()
                        .filter(condition)
                        .collect(Collectors.toList())
        );
    }

    public void resort(Date toDate) {
        storeList.forEach(store ->
            store.getProductList().forEach(product -> {
                if (!store.getCondition().testCondition(product, toDate)) {
                    getStoreListByProductCondition(s -> s.getCondition().testCondition(product, toDate))
                            .ifPresent(stores -> {
                                stores.get(0).add(product);
                                store.remove(product);
                            });
                }
            })
        );
    }

}