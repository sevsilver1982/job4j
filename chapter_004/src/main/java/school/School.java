package school;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class School {

    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable)
                .sorted(Comparator.reverseOrder())
                .takeWhile(student -> student.getScore() > bound)
                .collect(Collectors.toList());
    };

    static class Holder {
        String key, value;

        Holder(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /*Function<Student, Holder> function1 = i -> {
        for (String unit : i.getUnits()) {
            new Holder();
        }
        return ;
    };*/

    public Map<String, Set<String>> sections(List<Student> students) {

       /* return students.stream().flatMap(
                student -> Stream.of()
                // собираем объект Holder с unit и name
        ).collect( // собираем карту
                Collectors.groupingBy((Holder) t -> t.key, // определяем группировку
                        Collector.of(
                                HashSet::new, // аккумулятор.
                                (set, el) -> // как добавлять данные.
                                (left, right) -> {                                    left.addAll(right);
                                    return left;
                                } // для агрегации.
                        )
                )
        );*/

        return null;
    }

}