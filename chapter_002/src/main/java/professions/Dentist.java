package professions;

public class Dentist extends Doctor {
    /**
     * Специалист - пародонтолог
     */
    private boolean isPeriodontist;
    /**
     * Специалист - ортопед
     */
    private boolean isOrthopedist;

    public Dentist() {
    }

    public Dentist(String name, String surname) {
        super("Дантист", name, surname);
    }
}
