package generic.store;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StoreTest {

    @Test
    public void add() {
        UserStore userStore = new UserStore(5);
        userStore.add(new User("user_1"));
        userStore.add(new User("user_2"));
        assertThat(userStore.toList().equals(List.of(
                new User("user_1"),
                new User("user_2"))
        ), is(true));
    }

    @Test
    public void replace() {
        UserStore userStore = new UserStore(5);
        userStore.add(new User("user_1"));
        userStore.add(new User("user_2"));
        userStore.add(new User("user_3"));
        User user4 = new User("user_4");
        userStore.replace("user_2", user4);
        assertThat(userStore.toList().equals(List.of(
                new User("user_1"),
                new User("user_4"),
                new User("user_3"))
        ), is(true));
    }

    @Test
    public void delete() {
        UserStore userStore = new UserStore(5);
        userStore.add(new User("user_1"));
        userStore.add(new User("user_2"));
        userStore.add(new User("user_3"));
        assertThat(userStore.delete("user_2"), is(true));
        assertThat(userStore.delete("user_22"), is(false));
        assertThat(userStore.toList().equals(List.of(
                new User("user_1"),
                new User("user_3"))
        ), is(true));
    }

    @Test
    public void findById() {
        UserStore userStore = new UserStore(5);
        userStore.add(new User("user_1"));
        User user2 = new User("user_2");
        userStore.add(user2);
        userStore.add(new User("user_3"));
        assertThat(
                userStore.findById("user_2").equals(user2), is(true)
        );
        assertThat(
                userStore.findById("user_12") == null, is(true)
        );
    }

}