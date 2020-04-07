package store;

import java.util.Calendar;

public class Food extends AbstractProduct {

    public Food(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }

}