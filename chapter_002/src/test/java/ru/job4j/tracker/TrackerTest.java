package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenExit() {
        StubInput consoleInput = new StubInput(
                new String[] {"0"}
        );
        Tracker tracker = new Tracker();
        StubActionTracker action = new StubActionTracker();
        StubActionTracker[] actionsTracker = new StubActionTracker[] {
                action
        };
        new StartUI().init(consoleInput, tracker, actionsTracker);
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void testInit() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        new StartUI().init(input, new Tracker(), new ActionTracker[] {
                new StubActionTracker()
        });
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator() + System.lineSeparator())
                .add("Menu:")
                .add("0. Stub action")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }

    @Test
    public void testActionShowAll() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"item_1"}
        );
        Tracker tracker = new Tracker();
        Item item = new Item(input.askString(""));
        tracker.add(item);
        new ActionShowAll().execute(input, tracker);

        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("=== Show all items ====")
                .add("id: " + item.getId() + "; name: " + item.getName())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }

    @Test
    public void testFindByNameAction() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"item_1", "item_1"}
        );
        Tracker tracker = new Tracker();
        Item item = new Item(input.askString(""));
        tracker.add(item);
        new ActionFindItemByName().execute(input, tracker);

        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("=== Find item by name ====")
                .add("id: " + item.getId() + "; name: " + item.getName())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
