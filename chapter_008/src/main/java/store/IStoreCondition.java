package store;

import java.util.Date;

public interface IStoreCondition {
    boolean testCondition(IProduct product, Date toDate);
    int getExpirationValue(IProduct product, Date toDate);
}