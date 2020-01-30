package generic.store;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StoreTest {

    @Test
    public void add() {
        UserStore userStore = new UserStore(5);
        User user1 = new User("user_1");
        User user2 = new User("user_2");
        userStore.add(user1);
        userStore.add(user2);
        assertThat(userStore.findById("user_1").equals(user1), is(true));
        assertThat(userStore.findById("user_2").equals(user2), is(true));
    }

    @Test
    public void replace() {
        UserStore userStore = new UserStore(5);
        User user1 = new User("user_1");
        User user2 = new User("user_2");
        User user3 = new User("user_3");
        User user4 = new User("user_4");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        userStore.replace("user_2", user4);
        assertThat(userStore.findById("user_1"), is(user1));
        assertThat(userStore.findById("user_4"), is(user4));
        assertThat(userStore.findById("user_3"), is(user3));
        assertThat(userStore.findById("user_2") == null, is(true));
    }

    @Test
    public void delete() {
        UserStore userStore = new UserStore(5);
        User user1 = new User("user_1");
        User user2 = new User("user_2");
        User user3 = new User("user_3");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        assertThat(userStore.findById("user_2").equals(user2), is(true));
        assertThat(userStore.delete("user_2"), is(true));
        assertThat(userStore.findById("user_2") == null, is(true));
        assertThat(userStore.delete("user_22"), is(false));
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