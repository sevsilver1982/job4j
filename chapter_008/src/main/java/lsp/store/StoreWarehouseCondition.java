package lsp.store;

import java.util.Date;

public class StoreWarehouseCondition extends AbstractStoreCondition {

    @Override
    public boolean testCondition(IProduct product, Date toDate) {
        return getExpirationValue(product, toDate) < 25;
    }

}