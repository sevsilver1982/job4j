package store;

import java.util.Date;

public class StoreShopCondition extends AbstractStoreCondition {

    @Override
    public boolean testCondition(IProduct product, Date toDate) {
        int expirationValue = getExpirationValue(product, toDate);
        if (expirationValue > 75 && expirationValue <= 100) {
            product.setDiscount(50);
        }
        return expirationValue >= 25 && expirationValue <= 100;
    }

}