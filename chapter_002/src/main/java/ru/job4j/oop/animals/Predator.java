package ru.job4j.oop.animals;

public class Predator extends Animal {
    public Predator(String name) {
        super(name);
        System.out.println("load " + name);
    }
}
