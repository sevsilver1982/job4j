package spammer;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Spammer implements AutoCloseable {
    private final Properties properties;
    private static final String APP_PROPERTIES = "app.spammer.properties";
    public static final String PROPERTY_JDBC_DRIVER = "jdbc.driver";
    public static final String PROPERTY_JDBC_URL = "jdbc.url";
    public static final String PROPERTY_JDBC_USERNAME = "jdbc.username";
    public static final String PROPERTY_JDBC_PASSWORD = "jdbc.password";

    private static final String CHANGE_LOG_FILE = "db/scripts/spammer/spammer.xml";
    private Connection connection;
    private final InputStream stream;

    public Spammer(Properties properties, InputStream stream) {
        this.properties = properties;
        this.stream = stream;
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
                Liquibase liquibase = new Liquibase(
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

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
            rd.lines().forEach(s -> {
                String[] str = s.split(";");
                users.add(new User(str[0], str[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws SQLException {
        for (User user : users) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                int i = 1;
                ps.setString(i++, user.getName());
                ps.setString(i, user.getEmail());
                ps.execute();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(Spammer.class.getClassLoader().getResourceAsStream(APP_PROPERTIES));
            Spammer spammer = new Spammer(
                    properties,
                    Spammer.class.getClassLoader().getResourceAsStream("files/dump.txt")
            );
            spammer.init(true);
            spammer.save(spammer.load());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}