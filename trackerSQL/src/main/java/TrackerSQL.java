import actions.*;
import input.InputConsole;
import input.InputValidate;
import items.Item;
import store.Store;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;


public class TrackerSQL extends AbstractTracker implements AutoCloseable {
    private static final String APP_PROPERTIES = "app.tracker.properties";
    private final Connection connection;

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
            preparedStatement.setObject(1, item.getId());
            preparedStatement.setString(2, item.getName());
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public boolean replace(UUID id, Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE items SET id = ?, name = ? WHERE id = ?")
        ) {
            preparedStatement.setString(1, item.getId().toString());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, id.toString());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(UUID id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            preparedStatement.setString(1, id.toString());
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
                                UUID.fromString(resultSet.getString("id")),
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
                                UUID.fromString(resultSet.getString("id")),
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
    public Item findById(UUID id) {
        Item item = new Item();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            preparedStatement.setString(1, id.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item = new Item(
                        UUID.fromString(resultSet.getString("id")),
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
        Properties properties = new Properties();
        try {
            properties.load(TrackerSQL.class.getClassLoader().getResourceAsStream(APP_PROPERTIES));
            Store store = new Store(properties);
            if (store.init(true)) {
                TrackerSQL tracker = new TrackerSQL(store.getConnection());
                tracker.setOutput(System.out);
                tracker.setActionList(
                        List.of(
                                new AddItem(),
                                new ShowAll(),
                                new ReplaceItem(),
                                new DeleteItem(),
                                new FindItemById(),
                                new FindItemByName(),
                                new Exit()
                        )
                );
                new StartUI(
                        new InputValidate(new InputConsole()),
                        tracker
                ).init();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}