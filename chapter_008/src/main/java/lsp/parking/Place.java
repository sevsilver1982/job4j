package lsp.parking;

import java.util.Optional;

public class Place {
    private String name;
    private int size;
    private ICar car;

    public Place(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public Optional<ICar> getCar() {
        return Optional.ofNullable(car);
    }

    public void setCar(ICar car) {
        this.car = car;
    }

}