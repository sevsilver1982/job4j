import items.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.Store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrackerSQLTest {
    private static final String APP_PROPERTIES = "app.tracker.properties";
    private static Properties properties;

    @BeforeAll
    static void beforeAll() throws IOException {
        properties = new Properties();
        properties.load(TrackerSQLTest.class.getClassLoader().getResourceAsStream(APP_PROPERTIES));
        Store store = new Store(properties);
        assertTrue(store.init(true));
    }

    @Test
    public void add() throws SQLException {
        Store store = new Store(properties);
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            Item item = new Item("name");
            tracker.add(item);
            assertEquals(
                    List.of(item),
                    tracker.findByName("name")
            );
        }
    }

    @Test
    void replace() throws SQLException {
        Store store = new Store(properties);
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            Item item1 = new Item("name1");
            Item item2 = new Item("name2");
            Item item3 = new Item("name3");
            Item newItem = new Item("new_name");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            tracker.replace(item2.getId(), newItem);
            assertEquals(
                    "new_name",
                    tracker.findById(newItem.getId()).getName()
            );
        }
    }

    @Test
    void delete() throws SQLException {
        Store store = new Store(properties);
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            Item item1 = new Item("name1");
            Item item2 = new Item("name2");
            Item item3 = new Item("name3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertTrue(tracker.delete(item1.getId()));
        }
    }

    @Test
    void findAll() throws SQLException {
        Store store = new Store(properties);
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            Item item1 = new Item("name1");
            Item item2 = new Item("name2");
            Item item3 = new Item("name3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertTrue(
                    tracker.findAll().containsAll(
                            List.of(item1, item2, item3)
                    )
            );
        }
    }

    @Test
    void findByName() throws SQLException {
        Store store = new Store(properties);
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            Item item1 = new Item("name1");
            Item item2 = new Item("name2");
            Item item3 = new Item("name3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertEquals(item2, tracker.findByName("name2").get(0));
        }
    }

    @Test
    void findById() throws SQLException {
        Store store = new Store(properties);
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            Item item1 = new Item("name1");
            Item item2 = new Item("name2");
            Item item3 = new Item("name3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertEquals(item2, tracker.findById(item2.getId()));
        }
    }

}