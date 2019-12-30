package professions;

import java.util.Date;

public class Professions {
    /**
     * Профессия
     */
    private String profession;
    /**
     * Имя
     */
    private String name;
    /**
     * Фамилия
     */
    private String surname;
    /**
     * Образование
     */
    private String education;
    /**
     * Дата рождения
     */
    private Date birthday;

    public Professions() {
    }

    public Professions(String profession, String name, String surname) {
        this.profession = profession;
        this.name = name;
        this.surname = surname;
    }

    public Professions(String name, String surname, String education, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.education = education;
        this.birthday = birthday;
    }

    public String getName() {
        return this.profession + ": " + this.name + " " + this.surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEducation() {
        return this.education;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public static void main(String[] args) {
        Professions professions = new Professions();

        Doctor doctor = new Doctor();
        Surgeon surgeon = new Surgeon("Максим", "Викторов");
        System.out.println(surgeon.getName());
        Dentist dentist = new Dentist("Василий", "Степанов");
        System.out.println(dentist.getName());

        Engineer engineer = new Engineer();
        Programmer programmer = new Programmer("Андрей", "Петров");
        System.out.println(programmer.getName());
        Builder builder = new Builder("Кирилл", "Сергеев");
        System.out.println(builder.getName());
    }

}
