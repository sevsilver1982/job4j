package generic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {

    @Test
    public void add() {
        UserStore userStore = new UserStore(5);
        User user1 = new User("user_1");
        User user2 = new User("user_2");
        userStore.add(user1);
        userStore.add(user2);
        assertTrue(
                userStore.findById("user_1").isPresent()
        );
        assertTrue(
                userStore.findById("user_2").isPresent()
        );
        assertEquals(
                List.of(user1, user2),
                List.of(
                        (Object[]) userStore.getItems().getObjects()
                )
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
        assertFalse(
                userStore.findById("user_2").isPresent()
        );
        assertEquals(
                List.of(user1, user4, user3),
                List.of(
                        (Object[]) userStore.getItems().getObjects()
                )
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
        assertTrue(
                userStore.delete("user_2")
        );
        assertFalse(
                userStore.findById("user_2").isPresent()
        );
        assertEquals(
                List.of(user1, user3),
                List.of(
                        (Object[]) userStore.getItems().getObjects()
                )
        );
        assertFalse(
                userStore.delete("user_9999999999")
        );
    }

    @Test
    public void findById() {
        UserStore userStore = new UserStore(5);
        userStore.add(new User("user_1"));
        userStore.add(new User("user_2"));
        userStore.add(new User("user_3"));
        assertTrue(
                userStore.findById("user_1").isPresent()
        );
        assertTrue(
                userStore.findById("user_2").isPresent()
        );
        assertTrue(
                userStore.findById("user_3").isPresent()
        );
        assertFalse(
                userStore.findById("user_12").isPresent()
        );
    }

}