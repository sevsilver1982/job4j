package tracker;

import org.junit.jupiter.api.Test;
import tracker.actions.FindItemByName;
import tracker.actions.ShowAll;
import tracker.actions.StubAbstractAction;
import tracker.input.StubInput;
import tracker.items.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrackerTest {

    @Test
    public void initTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        new StartUI(
                new StubInput(
                        new String[] {"1"}
                ),
                new Tracker(
                        Arrays.asList(
                                new StubAbstractAction()
                        )
                ),
                System.out::println
        ).init();
        assertEquals(
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator() + System.lineSeparator())
                        .add("Menu:")
                        .add("1. Stub action")
                        .add("==== Stub action ====")
                        .toString(),
                new String(out.toByteArray())
        );
        System.setOut(def);
    }

    @Test
    public void exitTest() {
        StubAbstractAction action = new StubAbstractAction();
        new StartUI(
                new StubInput(
                        new String[] {"1"}
                ),
                new Tracker(
                        List.of(action)
                ),
                System.out::println).init();
        assertTrue(
                action.isCall()
        );
    }

    @Test
    public void findByIdTest() {
        Tracker tracker = new Tracker(Collections.emptyList());
        Item item = new Item("test1");
        tracker.add(item);
        Item test2 = new Item("test2");
        tracker.add(test2);
        assertEquals(
                item.getName(),
                tracker.findById(item.getId()).getName()
        );
    }

    @Test
    public void replaceTest() {
        Tracker tracker = new Tracker(Collections.emptyList());
        Item test1 = new Item("test1");
        tracker.add(test1);
        Item test2 = new Item("test2");
        tracker.add(test2);
        Item test3 = new Item("test3");
        tracker.add(test3);
        Item test4 = new Item("test4");
        tracker.replace(test2.getId(), test4);
        assertEquals(
                List.of(test1, test4, test3),
                tracker.findAll()
        );
    }

    @Test
    public void actionShowAllTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"item_1"}
        );
        Tracker tracker = new Tracker(Collections.emptyList());
        Item item = new Item(input.askString(""));
        tracker.add(item);
        new ShowAll().execute(input, tracker);
        assertEquals(
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("==== Show all items ====")
                        .add(item.toString())
                        .toString(),
                new String(out.toByteArray())
        );
        System.setOut(def);
    }

    @Test
    public void findByNameTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"item_1", "item_1"}
        );
        Tracker tracker = new Tracker(Collections.emptyList());
        Item item = new Item(input.askString(""));
        tracker.add(item);
        new FindItemByName().execute(input, tracker);
        assertEquals(
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("==== Find item by name ====")
                        .add(item.toString())
                        .toString(),
                new String(out.toByteArray())
        );
        System.setOut(def);
    }

}