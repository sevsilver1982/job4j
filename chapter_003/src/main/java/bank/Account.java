package bank;

import java.util.Objects;

public class Account {
    private double value;
    private String requisites;

    public Account() {
    }

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Double.compare(account.value, value) == 0
                && Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, requisites);
    }

    public boolean transfer(Account account, double amount) {
        if (account == null || this.value < amount) {
            return false;
        }
        this.value = this.value - amount;
        account.setValue(account.getValue() + amount);
        return true;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }
}