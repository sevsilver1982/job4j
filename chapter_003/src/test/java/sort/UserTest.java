package sort;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class UserTest {

    @Test
    public void whenComparePertVSIvan() {
        assertThat(
                new User("Petr", 32).compareTo(new User("Ivan", 31)),
                greaterThan(0)
        );
    }

    @Test
    public void userAscByName() {
        Iterator<User> it = List.of(
                new User("User1", 34),
                new User("User2", 33),
                new User("User3", 32),
                new User("User4", 31)
        )
                .stream()
                .sorted(new UserAscByName())
                .collect(Collectors.toList())
                .iterator();
        assertThat(it.next(), is(new User("User1", 34)));
        assertThat(it.next(), is(new User("User2", 33)));
        assertThat(it.next(), is(new User("User3", 32)));
        assertThat(it.next(), is(new User("User4", 31)));
    }

    @Test
    public void userDescByName() {
        Iterator<User> it = List.of(
                new User("User1", 34),
                new User("User2", 33),
                new User("User3", 32),
                new User("User4", 31)
        )
                .stream()
                .sorted(new UserDescByName())
                .collect(Collectors.toList())
                .iterator();
        assertThat(it.next(), is(new User("User4", 31)));
        assertThat(it.next(), is(new User("User3", 32)));
        assertThat(it.next(), is(new User("User2", 33)));
        assertThat(it.next(), is(new User("User1", 34)));
    }

    @Test
    public void userAscByAge() {
        Iterator<User> it = List.of(
                new User("User1", 34),
                new User("User2", 33),
                new User("User3", 32),
                new User("User4", 31)
        )
                .stream()
                .sorted(new UserAscByAge())
                .collect(Collectors.toList())
                .iterator();
        assertThat(it.next(), is(new User("User4", 31)));
        assertThat(it.next(), is(new User("User3", 32)));
        assertThat(it.next(), is(new User("User2", 33)));
        assertThat(it.next(), is(new User("User1", 34)));
    }

    @Test
    public void userDescByAge() {
        Iterator<User> it = List.of(
                new User("User1", 34),
                new User("User2", 33),
                new User("User3", 32),
                new User("User4", 31)
        )
                .stream()
                .sorted(new UserDescByAge())
                .collect(Collectors.toList())
                .iterator();
        assertThat(it.next(), is(new User("User1", 34)));
        assertThat(it.next(), is(new User("User2", 33)));
        assertThat(it.next(), is(new User("User3", 32)));
        assertThat(it.next(), is(new User("User4", 31)));
    }

    @Test
    public void combineComparators() {
        Iterator<User> it = List.of(
                new User("User1", 34),
                new User("User2", 33),
                new User("User3", 32),
                new User("User4", 31)
        )
                .stream()
                .sorted(new UserAscByAge().thenComparing(new UserAscByName()))
                .collect(Collectors.toList())
                .iterator();
        assertThat(it.next(), is(new User("User4", 31)));
        assertThat(it.next(), is(new User("User3", 32)));
        assertThat(it.next(), is(new User("User2", 33)));
        assertThat(it.next(), is(new User("User1", 34)));
    }

}