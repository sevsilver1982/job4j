package rabbit;

import java.io.IOException;
import java.util.Properties;

public class Config {
    public static final String RABBIT_PROPERTIES = "app.rabbit.properties";
    private static Config instance = new Config();
    private final Properties config;

    private Config() {
        this.config = new Properties();
        try {
            this.config.load(
                    this.getClass().getClassLoader().getResourceAsStream(RABBIT_PROPERTIES)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getString(String key) {
        return config.getProperty(key, "");
    }

    public Integer getInteger(String key) {
        return Integer.parseInt(config.getProperty(key, "0"));
    }

}