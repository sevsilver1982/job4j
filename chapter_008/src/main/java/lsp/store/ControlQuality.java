package lsp.store;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ControlQuality {
    private List<IStore> storeList;

    public ControlQuality(List<IStore> storeHouseList) {
        this.storeList = storeHouseList;
    }

    private List<IStore> getStoreListByProductCondition(IProduct product, Date toDate) {
        return storeList.stream()
                .filter(store -> store.getCondition().testCondition(product, toDate))
                .collect(Collectors.toList());
    }

    public void fullControl(Date toDate) {
        storeList.forEach(store -> {
            System.out.println(store.toString());
            store.getProductList().forEach(product -> {
                if (!store.getCondition().testCondition(product, toDate)) {
                    getStoreListByProductCondition(product, toDate).get(0).add(product);
                    store.remove(product);
                }
            });
        });
    }

}