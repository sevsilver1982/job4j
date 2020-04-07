package store;

import java.util.Calendar;

public interface IProduct {
    String getName();
    void setName(String name);
    Calendar getCreationDate();
    void setCreationDate(Calendar creationDate);
    Calendar getExpirationDate();
    void setExpirationDate(Calendar expirationDate);
    double getPrice();
    void setPrice(double price);
    double getDiscount();
    void setDiscount(double discount);
}