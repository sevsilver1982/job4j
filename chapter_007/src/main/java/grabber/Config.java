package grabber;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config instance = new Config();
    private static Properties properties;

    private Config() {
        properties = new Properties();
        try {
            properties.load(
                    Config.class.getClassLoader().getResourceAsStream("app.grabber.properties")
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Properties not found");
        }
    }

    public synchronized static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public synchronized String getProperty(String key) {
        return properties.getProperty(key);
    }

}