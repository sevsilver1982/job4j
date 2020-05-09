package multithreading.userstore;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserStoreTest {

    @Test
    void list() {
        UserStore store = new UserStore();
        new Thread(() -> store.add(new User(1, 100))).start();
        new Thread(() -> store.add(new User(2, 200))).start();
        new Thread(() -> System.out.println(String.format("1 %s", store.getUsers()))).start();
        new Thread(() -> System.out.println(String.format("2 %s", store.getUsers()))).start();
        new Thread(() -> System.out.println(String.format("3 %s", store.getUsers()))).start();
        new Thread(() -> System.out.println(String.format("4 %s", store.getUsers()))).start();
        new Thread(() -> System.out.println(String.format("5 %s", store.getUsers()))).start();
        new Thread(() -> System.out.println(String.format("6 %s", store.getUsers()))).start();
        new Thread(() -> System.out.println(String.format("7 %s", store.getUsers()))).start();
        new Thread(() -> System.out.println(String.format("8 %s", store.getUsers()))).start();
        new Thread(() -> System.out.println(String.format("9 %s", store.getUsers()))).start();
    }

    @Test
    void add() throws InterruptedException {
        UserStore store = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        Thread thread1 = new Thread(() -> store.add(user1));
        Thread thread2 = new Thread(() -> store.add(user2));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertTrue(
                store.getUsers().containsAll(List.of(user1, user2))
                        && List.of(user1, user2).containsAll(store.getUsers())
        );
    }

    @Test
    void delete() throws InterruptedException {
        UserStore store = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        Thread thread1 = new Thread(() -> store.add(user1));
        Thread thread2 = new Thread(() -> store.add(user2));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        store.delete(user2);
        assertEquals(List.of(user1), store.getUsers());
    }

    @Test
    void transfer() throws InterruptedException {
        UserStore store = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        Thread thread1 = new Thread(() -> store.add(user1));
        Thread thread2 = new Thread(() -> store.add(user2));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        store.transfer(1, 2, 50);
        assertEquals(50, user1.getAmount());
        assertEquals(250, user2.getAmount());
    }

}