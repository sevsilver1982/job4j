package lsp.store;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public abstract class AbstractProduct {
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

    private int daysBetween(Date d1, Date d2){
        return (int)((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public int getExpirationValue() {
        int period = daysBetween(creationDate.getTime(), expirationDate.getTime());
        int now = daysBetween(creationDate.getTime(), Calendar.getInstance().getTime());
        return now * 100 / period;
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
        return name.equals(that.name)
                && Objects.equals(creationDate, that.creationDate)
                && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, creationDate, expirationDate);
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

}