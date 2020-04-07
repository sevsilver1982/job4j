package sort;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void whenComparePertVSIvan() {
        assertTrue(
                new User("Petr", 32)
                        .compareTo(
                                new User("Ivan", 31)
                        ) > 0
        );
    }

    @Test
    public void userAscByName() {
        Iterator<User> it = List.of(
                new User("User1", 34),
                new User("User2", 33),
                new User("User3", 32),
                new User("User4", 31)
        ).stream()
                .sorted(new UserAscByName())
                .collect(Collectors.toList())
                .iterator();
        assertEquals(
                new User("User1", 34),
                it.next()
        );
        assertEquals(
                new User("User2", 33),
                it.next()
        );
        assertEquals(
                new User("User3", 32),
                it.next()
        );
        assertEquals(
                new User("User4", 31),
                it.next()
        );
    }

    @Test
    public void userDescByName() {
        Iterator<User> it = List.of(
                new User("User1", 34),
                new User("User2", 33),
                new User("User3", 32),
                new User("User4", 31)
        ).stream()
                .sorted(new UserDescByName())
                .collect(Collectors.toList())
                .iterator();
        assertEquals(
                new User("User4", 31),
                it.next()
        );
        assertEquals(
                new User("User3", 32),
                it.next()
        );
        assertEquals(
                new User("User2", 33),
                it.next()
        );
        assertEquals(
                new User("User1", 34),
                it.next()
        );
    }

    @Test
    public void userAscByAge() {
        Iterator<User> it = List.of(
                new User("User1", 34),
                new User("User2", 33),
                new User("User3", 32),
                new User("User4", 31)
        ).stream()
                .sorted(new UserAscByAge())
                .collect(Collectors.toList())
                .iterator();
        assertEquals(
                new User("User4", 31),
                it.next()
        );
        assertEquals(
                new User("User3", 32),
                it.next()
        );
        assertEquals(
                new User("User2", 33),
                it.next()
        );
        assertEquals(
                new User("User1", 34),
                it.next()
        );
    }

    @Test
    public void userDescByAge() {
        Iterator<User> it = List.of(
                new User("User1", 34),
                new User("User2", 33),
                new User("User3", 32),
                new User("User4", 31)
        ).stream()
                .sorted(new UserDescByAge())
                .collect(Collectors.toList())
                .iterator();
        assertEquals(
                new User("User1", 34),
                it.next()
        );
        assertEquals(
                new User("User2", 33),
                it.next()
        );
        assertEquals(
                new User("User3", 32),
                it.next()
        );
        assertEquals(
                new User("User4", 31),
                it.next()
        );
    }

    @Test
    public void combineComparators() {
        Iterator<User> it = List.of(
                new User("User1", 34),
                new User("User2", 33),
                new User("User3", 32),
                new User("User4", 31)
        ).stream()
                .sorted(new UserAscByAge().thenComparing(new UserAscByName()))
                .collect(Collectors.toList())
                .iterator();
        assertEquals(
                new User("User4", 31),
                it.next()
        );
        assertEquals(
                new User("User3", 32),
                it.next()
        );
        assertEquals(
                new User("User2", 33),
                it.next()
        );
        assertEquals(
                new User("User1", 34),
                it.next()
        );
    }

}