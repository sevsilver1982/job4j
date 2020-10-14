package tracker;

import org.junit.jupiter.api.Test;
import tracker.actions.*;
import tracker.input.IInput;
import tracker.items.Item;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActionsMockTest {

    @Test
    public void exitTest() {
        IInput mockInput = mock(IInput.class);
        when(mockInput.askInt(eq("Select: "), anyInt()))
                .thenReturn(1);
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        tracker.setActionList(
                List.of(new Exit())
        );
        new StartUI(
                mockInput,
                tracker
        ).init();
        assertEquals(
                "Menu:\n"
                        + "1. Exit program\n"
                        + "==== Exit program ====\n",
                tracker.getOutput().toString()
        );
        assertFalse(new Exit().execute(mockInput, tracker));
    }

    @Test
    public void addItemTest() {
        IInput mockInput = mock(IInput.class);
        when(mockInput.askString(eq("Enter name: ")))
                .thenReturn("item1")
                .thenReturn("item2")
                .thenReturn("item3");
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new AddItem().execute(mockInput, tracker));
        assertTrue(new AddItem().execute(mockInput, tracker));
        assertTrue(new AddItem().execute(mockInput, tracker));
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
        IInput mockInput = mock(IInput.class);
        when(mockInput.askString(eq("Enter item id: ")))
                .thenReturn("2");
        when(mockInput.askString(eq("Enter new item name: ")))
                .thenReturn("replaced");
        Item item1 = new Item("1", "item1");
        Item item2 = new Item("2", "item2");
        Item item3 = new Item("3", "item3");
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new ReplaceItem().execute(mockInput, tracker));
        assertEquals(
                "==== Replace item ====\n"
                        + "Item not found\n",
                tracker.getOutput().toString()
        );
        tracker.setOutput(new ByteArrayOutputStream());
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertTrue(new ReplaceItem().execute(mockInput, tracker));
        assertEquals(
                "==== Replace item ====\n"
                        + item2.toString() + "\n",
                tracker.getOutput().toString()
        );
        assertEquals("replaced", tracker.findById("2").getName());
    }

    @Test
    public void deleteItemTest() {
        IInput mockInput = mock(IInput.class);
        when(mockInput.askString(eq("Enter item id: ")))
                .thenReturn("2");
        Item item1 = new Item("1", "item1");
        Item item2 = new Item("2", "item2");
        Item item3 = new Item("3", "item3");
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new DeleteItem().execute(mockInput, tracker));
        assertEquals(
                "==== Delete item ====\n"
                        + "Item not found\n",
                tracker.getOutput().toString()
        );
        tracker.setOutput(new ByteArrayOutputStream());
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertTrue(new DeleteItem().execute(mockInput, tracker));
        assertEquals(
                "==== Delete item ====\n"
                        + item2.toString() + "\n",
                tracker.getOutput().toString()
        );
    }

    @Test
    public void findItemByIdTest() {
        IInput mockInput = mock(IInput.class);
        when(mockInput.askString(eq("Enter item id: ")))
                .thenReturn("2");
        Item item1 = new Item("1", "item1");
        Item item2 = new Item("2", "item2");
        Item item3 = new Item("3", "item3");
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new FindItemById().execute(mockInput, tracker));
        assertEquals(
                "==== Find item by id ====\n"
                        + "Item not found\n",
                tracker.getOutput().toString()
        );
        tracker.setOutput(new ByteArrayOutputStream());
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertTrue(new FindItemById().execute(mockInput, tracker));
        assertEquals(
                "==== Find item by id ====\n"
                        + item2.toString() + "\n",
                tracker.getOutput().toString()
        );
    }

    @Test
    public void findItemByNameTest() {
        IInput mockInput = mock(IInput.class);
        when(mockInput.askString(eq("Enter item name: ")))
                .thenReturn("item1");
        Item item11 = new Item("11", "item1");
        Item item12 = new Item("12", "item1");
        Item item2 = new Item("2", "item2");
        Item item3 = new Item("3", "item3");

        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new FindItemByName().execute(mockInput, tracker));
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
        assertTrue(new FindItemByName().execute(mockInput, tracker));
        assertEquals(
                "==== Find item by name ====\n"
                        + item11.toString() + "\n"
                        + item12.toString() + "\n",
                tracker.getOutput().toString()
        );
    }

    @Test
    public void showAllActionTest() {
        IInput mockInput = mock(IInput.class);
        Item item1 = new Item("1", "item1");
        Item item2 = new Item("2", "item2");
        Item item3 = new Item("3", "item3");
        Tracker tracker = new Tracker();
        tracker.setOutput(new ByteArrayOutputStream());
        assertTrue(new ShowAll().execute(mockInput, tracker));
        assertEquals(
                "==== Show all items ====\n"
                        + "Items not found\n",
                tracker.getOutput().toString()
        );
        tracker.setOutput(new ByteArrayOutputStream());
        assertEquals(item1, tracker.add(item1));
        assertEquals(item2, tracker.add(item2));
        assertEquals(item3, tracker.add(item3));
        assertTrue(new ShowAll().execute(mockInput, tracker));
        assertEquals(
                "==== Show all items ====\n"
                        + item1.toString() + "\n"
                        + item2.toString() + "\n"
                        + item3.toString() + "\n",
                tracker.getOutput().toString()
        );
    }

}