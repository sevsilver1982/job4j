package ru.job4j.professions;

public class Surgeon extends Doctor {
    /**
     * Специалист по нейрохирургии
     */
    private boolean isNeurosurgery;
    /**
     * Специалист по пластиковой хирургии
     */
    private boolean isPlasticSurgery;
    /**
     * Специалист по ортопедии
     */
    private boolean isOrthopedist;

    public Surgeon() {
    }

    public Surgeon(String name, String surname) {
        super("Хирург", name, surname);
    }

    /**
     * Начать операцию
     */
    public void startOperation() {

    }
}
