package grabber;

import java.util.List;

/**
 * Описание операций извлечения данных с сайтов.
 */
public interface Parser {
    Store getStore();
    List<Post> list(String link);
}