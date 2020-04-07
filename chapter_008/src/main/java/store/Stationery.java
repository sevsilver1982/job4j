package store;

import java.util.Calendar;

public class Stationery extends AbstractProduct {

    public Stationery(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }

}