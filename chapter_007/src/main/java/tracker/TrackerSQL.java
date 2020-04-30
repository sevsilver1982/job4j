package tracker;

import tracker.actions.*;
import tracker.input.InputConsole;
import tracker.input.InputValidate;
import tracker.items.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackerSQL extends AbstractTracker implements AutoCloseable {
    private Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO items (id, name) VALUES (?, ?)")
        ) {
            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE items SET id = ?, name = ? WHERE id = ?")
        ) {
            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items ORDER BY name");
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                items.add(
                        new Item(
                                resultSet.getString("id"),
                                resultSet.getString("name")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items WHERE name = ? ORDER BY id")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                items.add(
                        new Item(
                                resultSet.getString("id"),
                                resultSet.getString("name")
                        )
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item item = new Item();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item = new Item(
                        resultSet.getString("id"),
                        resultSet.getString("name")
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    public static void main(String[] args) {
        Store store = new Store();
        if (store.init(true)) {
            TrackerSQL tracker = new TrackerSQL(store.getConnection());
            tracker.setActionList(
                    List.of(
                            new NewItem(),
                            new ShowAll(),
                            new ReplaceItem(),
                            new DeleteItem(),
                            new FindItemById(),
                            new FindItemByName(),
                            new ExitProgram()
                    )
            );
            new StartUI(
                    new InputValidate(new InputConsole()),
                    tracker,
                    System.out::println
            ).init();
        }
    }

}