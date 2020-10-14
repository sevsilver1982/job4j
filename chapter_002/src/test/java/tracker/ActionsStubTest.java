package tracker;

import org.junit.jupiter.api.Test;
import tracker.actions.*;
import tracker.input.StubInput;
import tracker.items.Item;

import java.io.ByteArrayOutputStream;
import java.util.List;

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
        StubInput stubInput = new StubInput(
                new String[] {
                        "1", "item1",
                        "2", "item2",
                        "3", "item3",
                        "4",
                        "2", "replaced"
                }
        );
        Item item1 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item2 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item3 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
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
        assertEquals("replaced", tracker.findById("2").getName());
    }

    @Test
    public void deleteItemTest() {
        StubInput stubInput = new StubInput(
                new String[] {
                        "1", "item1",
                        "2", "item2",
                        "3", "item3",
                        "4",
                        "2"
                }
        );
        Item item1 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item2 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item3 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
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
        StubInput stubInput = new StubInput(
                new String[] {
                        "1", "item1",
                        "2", "item2",
                        "3", "item3",
                        "4",
                        "2"
                }
        );
        Item item1 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item2 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item3 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
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
        StubInput stubInput = new StubInput(
                new String[] {
                        "11", "item1",
                        "12", "item1",
                        "2", "item2",
                        "3", "item3",
                        "item4",
                        "item1"
                }
        );
        Item item11 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item12 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item2 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item3 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
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
                new String[] {
                        "1", "item1",
                        "2", "item2",
                        "3", "item3"
                }
        );
        Item item1 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item2 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
        Item item3 = new Item(
                stubInput.askString(""),
                stubInput.askString("")
        );
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