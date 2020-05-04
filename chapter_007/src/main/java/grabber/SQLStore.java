package grabber;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static grabber.Constants.*;

public class SQLStore implements Store, AutoCloseable {
    private static final String CHANGE_LOG_FILE = "db/scripts/grabber/grabber.xml";
    private Properties properties;
    private Connection connection;

    public SQLStore(Properties properties) {
        this.properties = properties;
    }

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty(PROPERTY_JDBC_DRIVER));
        return DriverManager.getConnection(
                properties.getProperty(PROPERTY_JDBC_URL),
                properties.getProperty(PROPERTY_JDBC_USERNAME),
                properties.getProperty(PROPERTY_JDBC_PASSWORD)
        );
    }

    public boolean init(boolean validateStruct) {
        try {
            connection = connect();
            if (validateStruct) {
                Liquibase liquibase = new liquibase.Liquibase(
                        CHANGE_LOG_FILE,
                        new ClassLoaderResourceAccessor(),
                        DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connect()))
                );
                liquibase.update(new Contexts(), new LabelExpression());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    /**
     * Сохраняет объявление в БД.
     * Вакансии с одинаковым именем считаются дубликатами и не записываются в БД.
     * @param post
     */
    @Override
    public boolean save(Post post) {
        try {
            try (PreparedStatement ps = connection.prepareStatement("SELECT count(*) FROM joboffers WHERE name = ?")) {
                ps.setString(1, post.getName());
                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                if (resultSet.getInt(1) > 0) {
                    return false;
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO joboffers (date, name, author, link, text) VALUES (?, ?, ?, ?, ?);")) {
                int i = 1;
                ps.setTimestamp(i++, new Timestamp(post.getDate().getTime()));
                ps.setString(i++, post.getName());
                ps.setString(i++, post.getAuthor());
                ps.setString(i++, post.getTextLink());
                ps.setString(i++, post.getText());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получить последнюю дату.
     * @return
     */
    @Override
    public Timestamp getLastOfferDate() {
        try {
            try (PreparedStatement ps = connection.prepareStatement("SELECT max(date) AS date FROM joboffers")) {
                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                return resultSet.getTimestamp("date");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Извлекает объявления.
     * @param filter
     * @return
     */
    @Override
    public List<Post> get(Predicate<Post> filter) {
        return get().stream().filter(filter).collect(Collectors.toList());
    }

    public List<Post> get() {
        List<Post> joboffers = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM joboffers ORDER BY date DESC");
             ResultSet resultSet = ps.executeQuery()
        ) {
            while (resultSet.next()) {
                joboffers.add(
                        new Post(
                                resultSet.getTimestamp("date"),
                                resultSet.getString("name"),
                                resultSet.getString("author"),
                                resultSet.getString("link"),
                                resultSet.getString("text")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return joboffers;
    }

}