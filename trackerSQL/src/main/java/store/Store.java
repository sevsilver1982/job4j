package store;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Store implements AutoCloseable {
    private static final String CHANGE_LOG_FILE = "db/scripts/tracker/tracker.xml";
    private final Properties properties;
    private Connection connection;

    public Store(Properties properties) {
        this.properties = properties;
    }

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("jdbc.driver"));
        return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password")
        );
    }

    public boolean init(boolean validateStruct) {
        try {
            connection = connect();
            if (validateStruct) {
                Liquibase liquibase = new Liquibase(
                        CHANGE_LOG_FILE,
                        new ClassLoaderResourceAccessor(),
                        DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connect())));
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

}