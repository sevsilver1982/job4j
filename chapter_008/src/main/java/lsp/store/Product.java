package lsp.store;

import java.util.Calendar;

public class Product extends AbstractProduct {

    public Product(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }

}