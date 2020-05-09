package multithreading.userstore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStore implements IStore {

    @GuardedBy("this")
    private List<User> users = new ArrayList<>();

    @Override
    public synchronized List<User> getUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public synchronized User findById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    @Override
    public synchronized boolean add(User user) {
        return users.add(user);
    }

    @Override
    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = findById(fromId);
        User to = findById(toId);
        if (from != null && to != null) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            return true;
        }
        return false;
    }

}