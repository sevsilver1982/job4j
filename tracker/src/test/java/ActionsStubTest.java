import actions.*;
import input.StubInput;
import items.Item;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ActionsStubTest {

    @Test
    public void classStubAbstractActionTest() {
        StubAbstractAction action = new StubAbstractAction();
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        tracker.setActionList(
                List.of(action)
        );
        new StartUI(
                new StubInput(new String[] {"1"}),
                tracker
        ).init();
        assertTrue(
                action.isCall()
        );
    }

    @Test
    public void exitTest() {
        StubInput stubInput = new StubInput(
                new String[] {
                        "1"
                }
        );
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        tracker.setActionList(
                List.of(new Exit())
        );
        new StartUI(
                stubInput,
                tracker
        ).init();
        assertEquals(
                "Menu:\n"
                        + "1. Exit program\n"
                        + "==== Exit program ====\n",
                tracker.getOutput().toString()
        );
        assertFalse(new Exit().execute(stubInput, tracker));
    }

    @Test
    public void addItemTest() {
        StubInput stubInput = new StubInput(
                new String[] {
                        "item1",
                        "item2",
                        "item3",
                        "4"
                }
        );
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new AddItem().execute(stubInput, tracker));
        assertTrue(new AddItem().execute(stubInput, tracker));
        assertTrue(new AddItem().execute(stubInput, tracker));
        assertEquals(
                "==== Add new item ====\n"
                        + tracker.findByName("item1").get(0).toString() + "\n"
                        + "==== Add new item ====\n"
                        + tracker.findByName("item2").get(0).toString() + "\n"
                        + "==== Add new item ====\n"
                        + tracker.findByName("item3").get(0).toString() + "\n",
                tracker.getOutput().toString()
        );
    }

    @Test
    public void replaceItemTest() {
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        StubInput stubInput = new StubInput(
                new String[] {
                        UUID.randomUUID().toString(),
                        item2.getId().toString(), "replaced"
                }
        );
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new ReplaceItem().execute(stubInput, tracker));
        assertEquals(
                "==== Replace item ====\n"
                        + "Item not found\n",
                tracker.getOutput().toString()
        );
        tracker.setOutput(new ByteArrayOutputStream());
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertTrue(new ReplaceItem().execute(stubInput, tracker));
        assertEquals(
                "==== Replace item ====\n"
                        + item2.toString() + "\n",
                tracker.getOutput().toString()
        );
        assertEquals(
                "replaced",
                tracker.findById(item2.getId()).getName()
        );
    }

    @Test
    public void deleteItemTest() {
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        StubInput stubInput = new StubInput(
                new String[] {
                        UUID.randomUUID().toString(),
                        item2.getId().toString()
                }
        );
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new DeleteItem().execute(stubInput, tracker));
        assertEquals(
                "==== Delete item ====\n"
                        + "Item not found\n",
                tracker.getOutput().toString()
        );
        tracker.setOutput(new ByteArrayOutputStream());
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertTrue(new DeleteItem().execute(stubInput, tracker));
        assertEquals(
                "==== Delete item ====\n"
                        + item2.toString() + "\n",
                tracker.getOutput().toString()
        );
    }

    @Test
    public void findItemByIdTest() {
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        StubInput stubInput = new StubInput(
                new String[] {
                        UUID.randomUUID().toString(),
                        item2.getId().toString()
                }
        );
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new FindItemById().execute(stubInput, tracker));
        assertEquals(
                "==== Find item by id ====\n"
                        + "Item not found\n",
                tracker.getOutput().toString()
        );
        tracker.setOutput(new ByteArrayOutputStream());
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertTrue(new FindItemById().execute(stubInput, tracker));
        assertEquals(
                "==== Find item by id ====\n"
                        + item2.toString() + "\n",
                tracker.getOutput().toString()
        );
    }

    @Test
    public void findItemByNameTest() {
        Item item11 = new Item("item1");
        Item item12 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        StubInput stubInput = new StubInput(
                new String[] {
                        "item4",
                        "item1"
                }
        );
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new FindItemByName().execute(stubInput, tracker));
        assertEquals(
                "==== Find item by name ====\n"
                        + "Item not found\n",
                tracker.getOutput().toString()
        );
        tracker.setOutput(new ByteArrayOutputStream());
        assertEquals(item11, tracker.add(item11));
        assertEquals(item12, tracker.add(item12));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertTrue(new FindItemByName().execute(stubInput, tracker));
        assertEquals(
                "==== Find item by name ====\n"
                        + item11.toString() + "\n"
                        + item12.toString() + "\n",
                tracker.getOutput().toString()
        );
    }

    @Test
    public void showAllActionTest() {
        StubInput stubInput = new StubInput(
                new String[] {}
        );
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new ShowAll().execute(stubInput, tracker));
        assertEquals(
                "==== Show all items ====\n"
                        + "Items not found\n",
                tracker.getOutput().toString()
        );
        tracker.setOutput(new ByteArrayOutputStream());
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertTrue(new ShowAll().execute(stubInput, tracker));
        assertEquals(
                "==== Show all items ====\n"
                        + item1.toString() + "\n"
                        + item2.toString() + "\n"
                        + item3.toString() + "\n",
                tracker.getOutput().toString()
        );
    }

}