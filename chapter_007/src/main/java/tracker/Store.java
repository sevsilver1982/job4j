package tracker;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static grabber.Constants.*;

public class Store implements AutoCloseable {
    private static final String CHANGE_LOG_FILE = "db/scripts/tracker/tracker.xml";
    private Properties properties;
    private Connection connection;

    public Store(Properties properties) {
        this.properties = properties;
    }

    public boolean init(boolean validateStruct) {
        try {
            Class.forName(properties.getProperty(PROPERTY_JDBC_DRIVER));
            connection = DriverManager.getConnection(
                    properties.getProperty(PROPERTY_JDBC_URL),
                    properties.getProperty(PROPERTY_JDBC_USERNAME),
                    properties.getProperty(PROPERTY_JDBC_PASSWORD)
            );
            if (validateStruct) {
                validateStruct();
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

    public void validateStruct() {
        try {
            Liquibase liquibase = new Liquibase(
                    CHANGE_LOG_FILE,
                    new ClassLoaderResourceAccessor(),
                    DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection)));
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}