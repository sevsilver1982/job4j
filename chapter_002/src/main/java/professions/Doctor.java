package professions;

public class Doctor extends Professions {
    /**
     * Специалист - терапевт
     */
    public boolean isTherapist;

    public Doctor() {
    }

    public Doctor(String profession, String name, String surname) {
        super(profession, name, surname);
    }

    /**
     * Поставить диагноз
     * @return
     */
    public void setDiagnosis() {
    }

    /**
     * Лечить
     */
    public void doTreat() {
    }
}
