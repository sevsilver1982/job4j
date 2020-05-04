package grabber;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Predicate;

public interface Store {
    Timestamp getLastOfferDate();
    boolean save(Post post);
    List<Post> get(Predicate<Post> filter);
}