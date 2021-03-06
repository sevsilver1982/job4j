package multithreading.synch.userstore;

import java.util.List;

public interface IStore {
    List<User> getUsers();
    User findById(int id);
    boolean add(User user);
    boolean delete(User user);
    boolean transfer(int fromId, int toId, int amount);
}