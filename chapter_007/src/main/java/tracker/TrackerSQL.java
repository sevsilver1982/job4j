package tracker;

import tracker.actions.*;
import tracker.input.InputConsole;
import tracker.input.InputValidate;
import tracker.items.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL extends AbstractTracker implements AutoCloseable {
    private Connection connection;

    public TrackerSQL(List<IAction> actions) {
        super(actions);
    }

    private boolean validateDBStruct() {
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS items (id character varying(36) NOT NULL, name character varying, CONSTRAINT items_pkey PRIMARY KEY (id));")) {
            preparedStatement.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Init connection to database.
     * @return result.
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.tracker.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            if (!validateDBStruct()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection != null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO items (id, name) VALUES (?, ?)")
        ) {
            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE items SET id = ?, name = ? WHERE id = ?")
        ) {
            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, id);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean delete(String id) {
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            preparedStatement.setString(1, id);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return item;
    }

    public static void main(String[] args) {
        TrackerSQL tracker = new TrackerSQL(
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
        if (tracker.init()) {
            new StartUI(
                    new InputValidate(new InputConsole()),
                    tracker,
                    System.out::println
            ).init();
        }
    }

}