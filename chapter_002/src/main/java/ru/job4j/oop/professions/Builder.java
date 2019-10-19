package ru.job4j.oop.professions;

public class Builder extends Engineer {
    /**
     * Класс строителя
     */
    private int builderClass;

    public Builder() {
    }

    public Builder(String name, String surname) {
        super("Строитель", name, surname);
    }

    /**
     * Начать строить
     */
    public void startBuild() {
    }
}
