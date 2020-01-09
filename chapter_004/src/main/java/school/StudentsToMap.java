package school;

import java.util.*;
import java.util.stream.Collectors;

public class StudentsToMap {

    public static void main(String[] args) {
        Map<String, Student> students =
                Arrays.asList(
                        new Student("name1", "surname1"),
                        new Student("name2", "surname2"),
                        new Student("name3", "surname3"),
                        new Student("name4", "surname4")
                ).stream().distinct().collect(
                        Collectors.toMap(
                                Student::getSurname,
                                student -> student
                        )
                );
        students.forEach((s, student) -> System.out.println(student));
    }

}