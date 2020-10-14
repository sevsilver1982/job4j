package tracker;

import org.junit.jupiter.api.Test;
import tracker.actions.StubAbstractAction;
import tracker.input.StubInput;
import tracker.items.Item;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrackerTest {

    @Test
    public void initTest() {
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        tracker.setActionList(
                Arrays.asList(new StubAbstractAction())
        );
        new StartUI(
                new StubInput(
                        new String[] {"1"}
                ),
                tracker
        ).init();
        assertEquals(
                "Menu:\n"
                        + "1. Stub action\n"
                        + "==== Stub action ====\n",
                tracker.getOutput().toString()
        );
    }

    @Test
    public void addTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertEquals(item1, tracker.findById(item1.getId()));
        assertEquals(item2, tracker.findById(item2.getId()));
        assertEquals(item3, tracker.findById(item3.getId()));
    }

    @Test
    public void replaceTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        Item item4 = new Item("test4");
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertTrue(tracker.replace(item2.getId(), item4));
        assertEquals(
                List.of(item1, item4, item3),
                tracker.findAll()
        );
        assertFalse(tracker.replace(item2.getId(), item4));
        assertEquals(
                List.of(item1, item4, item3),
                tracker.findAll()
        );
    }

    @Test
    public void deleteTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertNotNull(tracker.findById(item1.getId()));
        assertNotNull(tracker.findById(item2.getId()));
        assertTrue(tracker.delete(item2.getId()));
        assertEquals(
                new Item(),
                tracker.findById(item2.getId())
        );
    }

    @Test
    public void findByIdTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        Item item4 = new Item("item4");
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertEquals(item1, tracker.findById(item1.getId()));
        assertEquals(item2, tracker.findById(item2.getId()));
        assertEquals(item3, tracker.findById(item3.getId()));
        assertEquals(
                new Item(),
                tracker.findById(item4.getId())
        );
    }

    @Test
    public void findByNameTest() {
        Tracker tracker = new Tracker();
        Item item11 = new Item("item1");
        Item item12 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        Item item4 = new Item("item4");
        assertEquals(item11, tracker.add(item11));
        assertEquals(item12, tracker.add(item12));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertEquals(
                List.of(item11, item12),
                tracker.findByName("item1")
        );
        assertEquals(
                List.of(item2),
                tracker.findByName(item2.getName())
        );
        assertEquals(
                List.of(item3),
                tracker.findByName(item3.getName())
        );
        assertEquals(
                List.of(),
                tracker.findByName(item4.getName())
        );
    }

    @Test
    public void findAllTest() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        Item item4 = new Item("item4");
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertEquals(item4, tracker.add(item4));
        assertEquals(
                List.of(item1, item2, item3, item4),
                tracker.findAll()
        );
    }

}