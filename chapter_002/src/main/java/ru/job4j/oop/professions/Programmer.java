package ru.job4j.oop.professions;

public class Programmer extends Engineer {
    /**
     * Специалист по Java
     */
    private boolean isJava;
    /**
     * Специалист по Pascal
     */
    private boolean isPascal;
    /**
     * Специалист по SQL
     */
    private boolean isSQL;

    public Programmer() {
    }

    public Programmer(String name, String surname) {
        super("Программист", name, surname);
    }

    public void doProgram() {
    }
}
