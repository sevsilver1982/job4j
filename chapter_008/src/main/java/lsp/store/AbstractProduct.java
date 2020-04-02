package lsp.store;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Objects;

public abstract class AbstractProduct implements IProduct {
    private String name;
    private Calendar creationDate;
    private Calendar expirationDate;
    private double price;
    private double discount;

    @NotNull
    public AbstractProduct(String name, Calendar creationDate, Calendar expirationDate, double price) {
        this.name = name;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractProduct that = (AbstractProduct) o;
        return name.equals(that.name) &&
                creationDate.equals(that.creationDate) &&
                expirationDate.equals(that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, creationDate, expirationDate);
    }

    @Override
    public String toString() {
        return String.format(
                "name='%s', creationDate=%s, expirationDate=%s, price=%s, discount=%s",
                name, creationDate, expirationDate, price, discount
        );
    }

}