package school;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchoolTest {
    private static List<Student> students = Arrays.asList(
            new Student("name1", "surname1", 100),
            new Student("name2", "surname2", 90),
            new Student("name3", "surname3", 80),
            new Student("name4", "surname4", 70),
            new Student("name5", "surname5", 60),
            new Student("name6", "surname6", 50),
            new Student("name7", "surname7", 40),
            new Student("name8", "surname8", 30),
            new Student("name9", "surname9", 20),
            new Student("name10", "surname10", 10)
    );

    @Test
    public void getStudentListA() {
        assertEquals(
                Arrays.asList(
                        new Student("name1", "surname1", 100),
                        new Student("name2", "surname2", 90),
                        new Student("name3", "surname3", 80),
                        new Student("name4", "surname4", 70)
                ),
                new School().collect(students, student -> (
                        student.getScore() >= 70 && student.getScore() <= 100)
                )
        );
    }

    @Test
    public void getStudentListB() {
        assertEquals(
                Arrays.asList(
                        new Student("name5", "surname5", 60),
                        new Student("name6", "surname6", 50)
                ),
                new School().collect(
                        students,
                        student -> student.getScore() >= 50 && student.getScore() < 70
                )
        );
    }

    @Test
    public void getStudentListC() {
        assertEquals(
                Arrays.asList(
                        new Student("name7", "surname7", 40),
                        new Student("name8", "surname8", 30),
                        new Student("name9", "surname9", 20),
                        new Student("name10", "surname10", 10)
                ),
                new School().collect(
                        students,
                        student -> student.getScore() >= 0 && student.getScore() < 50
                )
        );
    }

    @Test
    public void levelOf() {
        assertEquals(
                List.of(
                        new Student("name1", "surname1", 100),
                        new Student("name10", "surname10", 90),
                        new Student("name3", "surname3", 80),
                        new Student("name2", "surname2", 70)
                ),
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
                )
        );
    }

    @Test
    public void sections() {
        List<Student> students = Arrays.asList(
                new Student("name1", "surname1", Set.of("unit1", "unit2", "unit3")),
                new Student("name2", "surname2", Set.of("unit1", "unit2")),
                new Student("name3", "surname3", Set.of("unit1")),
                new Student("name4", "surname4", Set.of("unit1", "unit2")),
                new Student("name5", "surname5", Set.of("unit1", "unit2", "unit3")),
                new Student("name6", "surname6", Set.of("unit1", "unit2")),
                new Student("name7", "surname7", Set.of("unit1")),
                new Student("name8", "surname8", Set.of("unit1", "unit2")),
                new Student("name9", "surname9", Set.of("unit1", "unit2", "unit3")),
                new Student("name10", "surname10", Set.of("unit1", "unit2"))
        );
        new School().sections(students);
    }

}