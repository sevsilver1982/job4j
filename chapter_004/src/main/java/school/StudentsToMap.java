package school;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentsToMap {

    public static void main(String[] args) {
        Map<String, Student> students =
                Stream.of(
                        new Student("name1", "surname1"),
                        new Student("name2", "surname2"),
                        new Student("name3", "surname3"),
                        new Student("name4", "surname4")
                ).distinct().collect(
                        Collectors.toMap(
                                Student::getSurname,
                                student -> student
                        )
                );
        students.forEach((surname, student) -> System.out.println(student));
    }

}