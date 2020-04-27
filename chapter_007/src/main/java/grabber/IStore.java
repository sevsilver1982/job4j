package grabber;

import java.sql.Connection;
import java.util.List;
import java.util.function.Predicate;

public interface IStore {
    Connection getConnection();
    boolean save(Post post);
    List<Post> get(Predicate<Post> filter);
}