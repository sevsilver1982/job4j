package lsp.store;

import java.util.Date;

public abstract class AbstractStoreCondition implements IStoreCondition {

    private int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public int getExpirationValue(IProduct product, Date toDate) {
        int period = daysBetween(product.getCreationDate().getTime(), product.getExpirationDate().getTime());
        int now = daysBetween(product.getCreationDate().getTime(), toDate);
        return now * 100 / period;
    }

}