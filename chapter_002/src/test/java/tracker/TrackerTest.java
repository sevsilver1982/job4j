package tracker;

import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.actions.FindItemByName;
import ru.job4j.tracker.actions.ShowAll;
import ru.job4j.tracker.actions.StubActionTracker;
import ru.job4j.tracker.input.StubInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TrackerTest {

    @Test
    public void findByIdTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item test2 = new Item("test2");
        tracker.add(test2);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void renameTest() {
        Tracker tracker = new Tracker();
        Item test1 = new Item("test1");
        tracker.add(test1);
        Item test2 = new Item("test1");
        tracker.add(test2);
        tracker.rename(test2.getId(), "test2");
        assertThat(tracker.findById(test2.getId()).getName(), is("test2"));
    }

    @Test
    public void whenExit() {
        Tracker tracker = new Tracker();
        StubActionTracker action = new StubActionTracker();
        tracker.addAction(action);
        StubInput consoleInput = new StubInput(
                new String[] {"1"}
        );
        new StartUI().init(consoleInput, tracker);
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void testInit() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"1"}
        );
        Tracker tracker = new Tracker();
        tracker.addAction(new StubActionTracker());
        new StartUI().init(input, tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator() + System.lineSeparator())
                .add("Menu:")
                .add("1. Stub action")
                .add("==== Stub action ====")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }

    @Test
    public void actionShowAllTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"item_1"}
        );
        Tracker tracker = new Tracker();
        Item item = new Item(input.askString(""));
        tracker.add(item);
        new ShowAll().execute(input, tracker);

        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("==== Show all items ====")
                .add(item.toString())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }

    @Test
    public void actionFindByNameTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"item_1", "item_1"}
        );
        Tracker tracker = new Tracker();
        Item item = new Item(input.askString(""));
        tracker.add(item);
        new FindItemByName().execute(input, tracker);

        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("==== Find item by name ====")
                .add(String.format("%s found by name", item.toString()))
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }

}