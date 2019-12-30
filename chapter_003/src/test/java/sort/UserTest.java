package sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserTest {

    @Test
    public void whenComparePertVSIvan() {
        int rsl = new User("Petr", 32)
                .compareTo(
                        new User("Ivan", 31)
                );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void userAscByName() {
        List<User> users = new ArrayList<>();
        users.add(new User("User1", 34));
        users.add(new User("User2", 33));
        users.add(new User("User3", 32));
        users.add(new User("User4", 31));
        Collections.sort(users, new UserAscByName());
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("User1", 34)));
        assertThat(it.next(), is(new User("User2", 33)));
        assertThat(it.next(), is(new User("User3", 32)));
        assertThat(it.next(), is(new User("User4", 31)));
    }

    @Test
    public void userDescByName() {
        List<User> users = new ArrayList<>();
        users.add(new User("User1", 34));
        users.add(new User("User2", 33));
        users.add(new User("User3", 32));
        users.add(new User("User4", 31));
        Collections.sort(users, new UserDescByName());
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("User4", 31)));
        assertThat(it.next(), is(new User("User3", 32)));
        assertThat(it.next(), is(new User("User2", 33)));
        assertThat(it.next(), is(new User("User1", 34)));
    }

    @Test
    public void userAscByAge() {
        List<User> users = new ArrayList<>();
        users.add(new User("User1", 34));
        users.add(new User("User2", 33));
        users.add(new User("User3", 32));
        users.add(new User("User4", 31));
        Collections.sort(users, new UserAscByAge());
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("User4", 31)));
        assertThat(it.next(), is(new User("User3", 32)));
        assertThat(it.next(), is(new User("User2", 33)));
        assertThat(it.next(), is(new User("User1", 34)));
    }

    @Test
    public void userDescByAge() {
        List<User> users = new ArrayList<>();
        users.add(new User("User1", 34));
        users.add(new User("User2", 33));
        users.add(new User("User3", 32));
        users.add(new User("User4", 31));
        Collections.sort(users, new UserDescByAge());
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("User1", 34)));
        assertThat(it.next(), is(new User("User2", 33)));
        assertThat(it.next(), is(new User("User3", 32)));
        assertThat(it.next(), is(new User("User4", 31)));
    }

    @Test
    public void combineComparators() {
        List<User> users = new ArrayList<>();
        users.add(new User("User1", 34));
        users.add(new User("User2", 33));
        users.add(new User("User3", 32));
        users.add(new User("User4", 31));
        Collections.sort(users, new UserAscByAge().thenComparing(new UserAscByName()));
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("User4", 31)));
        assertThat(it.next(), is(new User("User3", 32)));
        assertThat(it.next(), is(new User("User2", 33)));
        assertThat(it.next(), is(new User("User1", 34)));
    }

}