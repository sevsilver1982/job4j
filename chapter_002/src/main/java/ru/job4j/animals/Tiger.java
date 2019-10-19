package ru.job4j.animals;

public class Tiger extends Predator {
    public Tiger(String name) {
        super(name);
        System.out.println("load " + name);
    }
}
