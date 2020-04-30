package tracker;

import grabber.Config;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;

import static tracker.Constants.*;

public class Store implements AutoCloseable {
    private Connection connection;
    private String changeLogFile = "db/scripts/tracker/tracker.xml";

    public boolean init(boolean validateStruct) {
        try {
            Class.forName(Config.getInstance().getProperty(PROPERTY_JDBC_DRIVER));
            connection = DriverManager.getConnection(
                    Config.getInstance().getProperty(PROPERTY_JDBC_URL),
                    Config.getInstance().getProperty(PROPERTY_JDBC_USERNAME),
                    Config.getInstance().getProperty(PROPERTY_JDBC_PASSWORD)
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
                    changeLogFile,
                    new ClassLoaderResourceAccessor(),
                    DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
                            new JdbcConnection(
                                    DriverManager.getConnection(
                                            Config.getInstance().getProperty(PROPERTY_JDBC_URL),
                                            Config.getInstance().getProperty(PROPERTY_JDBC_USERNAME),
                                            Config.getInstance().getProperty(PROPERTY_JDBC_PASSWORD)
                                    )
                            )
                    )
            );
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}