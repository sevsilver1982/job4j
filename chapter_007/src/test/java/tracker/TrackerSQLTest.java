package tracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tracker.items.Item;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrackerSQLTest {

    @BeforeAll
    static void beforeAll() {
        Store store = new Store();
        assertTrue(store.init(true));
    }

    @Test
    public void add() throws SQLException {
        Store store = new Store();
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            tracker.add(new Item("1", "name"));
            assertEquals(
                    "1",
                    tracker.findByName("name").get(0).getId()
            );
        }
    }

    @Test
    void replace() throws SQLException {
        Store store = new Store();
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            tracker.add(new Item("1", "name"));
            tracker.replace("1", new Item("1", "new_name"));
            assertEquals(
                    "1",
                    tracker.findByName("new_name").get(0).getId()
            );
        }
    }

    @Test
    void delete() throws SQLException {
        Store store = new Store();
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            tracker.add(new Item("1", "name"));
            assertTrue(tracker.delete("1"));
        }
    }

    @Test
    void findAll() throws SQLException {
        Store store = new Store();
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            Item item1 = new Item("1", "name1");
            Item item2 = new Item("2", "name2");
            Item item3 = new Item("3", "name3");
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
        Store store = new Store();
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            Item item1 = new Item("1", "name1");
            Item item2 = new Item("2", "name2");
            Item item3 = new Item("3", "name3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertEquals(item2, tracker.findByName("name2").get(0));
        }
    }

    @Test
    void findById() throws SQLException {
        Store store = new Store();
        store.init(false);
        try (TrackerSQL tracker = new TrackerSQL(
                ConnectionRollback.create(store.getConnection()))
        ) {
            Item item1 = new Item("1", "name1");
            Item item2 = new Item("2", "name2");
            Item item3 = new Item("3", "name3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            assertEquals(item2, tracker.findById("2"));
        }
    }

}