package lsp.parking;

import java.util.Objects;

public class Car implements ICar {
    private String number;
    private int size;
    private String name;

    public Car(String name, String number, int size) {
        this.name = name;
        this.number = number;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return size == car.size
                && Objects.equals(number, car.number)
                && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, size, name);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", size=" + size +
                '}';
    }
}