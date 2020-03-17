package tracker;

import org.junit.Test;
import tracker.actions.FindItemByName;
import tracker.actions.ShowAll;
import tracker.actions.StubActionTracker;
import tracker.input.StubInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
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
    public void replaceTest() {
        Tracker tracker = new Tracker();
        Item test1 = new Item("test1");
        tracker.add(test1);
        Item test2 = new Item("test2");
        tracker.add(test2);
        Item test3 = new Item("test3");
        tracker.add(test3);
        Item test4 = new Item("test4");
        tracker.replace(test2.getId(), test4);
        assertThat(tracker.findAll(), is(List.of(test1, test4, test3)));
    }

    @Test
    public void whenExit() {
        Tracker tracker = new Tracker();
        StubActionTracker action = new StubActionTracker();
        tracker.addAction(action);
        StubInput input = new StubInput(
                new String[] {"1"}
        );
        new StartUI(input, tracker, System.out::println).init();
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
        new StartUI(input, tracker, System.out::println).init();
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
    public void findByNameTest() {
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