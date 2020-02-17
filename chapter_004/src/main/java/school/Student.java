package school;

import java.util.Objects;
import java.util.Set;

public class Student implements Comparable<Student> {
    private String name;
    private String surname;
    private Set<String> units;
    private int score;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Student(String name, String surname, int score) {
        this.name = name;
        this.surname = surname;
        this.score = score;
    }

    public Student(String name, String surname, Set<String> units) {
        this.name = name;
        this.surname = surname;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getScore() {
        return score;
    }

    public Set<String> getUnits() {
        return units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(name, student.name)
                && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", units=" + units
                + ", score=" + score
                + '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.score - o.getScore();
    }

}