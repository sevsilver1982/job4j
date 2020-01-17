package school;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SchoolTest {

    List<Student> students = Arrays.asList(
            new Student(100),
            new Student(90),
            new Student(80),
            new Student(70),
            new Student(60),
            new Student(50),
            new Student(40),
            new Student(30),
            new Student(20),
            new Student(10)
    );

    private static void accept(Student student) {
        System.out.println();
    }

    @Test
    public void getStudentListA() {
        assertThat(
                new School().collect(students, student -> (
                        student.getScore() >= 70 && student.getScore() <= 100)
                ),
                is(Arrays.asList(
                        new Student(100),
                        new Student(90),
                        new Student(80),
                        new Student(70)))
        );
    }

    @Test
    public void getStudentListB() {
        assertThat(
                new School().collect(students, student -> (
                        student.getScore() >= 50 && student.getScore() < 70)
                ),
                is(Arrays.asList(
                        new Student(60),
                        new Student(50)))
        );
    }

    @Test
    public void getStudentListC() {
        assertThat(
                new School().collect(students, student -> (
                        student.getScore() >= 0 && student.getScore() < 50)
                ),
                is(Arrays.asList(
                        new Student(40),
                        new Student(30),
                        new Student(20),
                        new Student(10)))
        );
    }

    @Test
    public void levelOf() {
        assertThat(
                new School().levelOf(
                        Arrays.asList(
                                new Student("name1", "surname1", 100),
                                new Student("name2", "surname2", 70),
                                new Student("name3", "surname3", 80),
                                null,
                                new Student("name5", "surname5", 40),
                                new Student("name6", "surname6", 50),
                                new Student("name7", "surname7", 30),
                                null,
                                new Student("name9", "surname9", 20),
                                new Student("name10", "surname10", 90)
                        ),
                        50
                ),
                is(List.of(
                        new Student("name1", "surname1", 100),
                        new Student("name2", "surname2", 90),
                        new Student("name3", "surname3", 80),
                        new Student("name5", "surname5", 70)
                ))
        );
    }

}