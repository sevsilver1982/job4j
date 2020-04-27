package grabber;

import java.util.List;

/**
 * Описание операций извлечения данных с сайтов.
 */
public interface IParser {
    IStore getStore();
    List<Post> list(String link);
}