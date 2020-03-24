package generic;

import org.junit.jupiter.api.Test;

import java.util.List;

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
        assertThat(userStore.findById("user_1").isPresent(), is(true));
        assertThat(userStore.findById("user_2").isPresent(), is(true));
        assertThat(
                List.of(
                        (Object[]) userStore.getItems().getObjects()
                ).containsAll(
                        List.of(user1, user2)
                ),
                is(true)
        );
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
        assertThat(userStore.findById("user_2").isPresent(), is(false));
        assertThat(
                List.of(
                        (Object[]) userStore.getItems().getObjects()
                ).containsAll(
                        List.of(user1, user4, user3)
                ),
                is(true)
        );
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
        assertThat(userStore.delete("user_2"), is(true));
        assertThat(userStore.findById("user_2").isPresent(), is(false));
        assertThat(
                List.of(
                        (Object[]) userStore.getItems().getObjects()
                ).containsAll(
                        List.of(user1, user3)
                ),
                is(true)
        );
        assertThat(userStore.delete("user_9999999999"), is(false));
    }

    @Test
    public void findById() {
        UserStore userStore = new UserStore(5);
        userStore.add(new User("user_1"));
        userStore.add(new User("user_2"));
        userStore.add(new User("user_3"));
        assertThat(userStore.findById("user_1").isPresent(), is(true));
        assertThat(userStore.findById("user_2").isPresent(), is(true));
        assertThat(userStore.findById("user_3").isPresent(), is(true));
        assertThat(userStore.findById("user_12").isPresent(), is(false));
    }

}