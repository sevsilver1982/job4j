package lsp.store;

import java.util.Calendar;

public class HouseholdGoods extends AbstractProduct {

    public HouseholdGoods(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }

}