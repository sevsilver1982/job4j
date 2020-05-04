package rabbit;

import java.io.IOException;
import java.util.Properties;

import static rabbit.Constants.RABBIT_PROPERTIES;

public class Config {
    private static Config instance = new Config();
    private final Properties config;

    private Config() {
        this.config = new Properties();
        try {
            this.config.load(
                    Config.class.getClassLoader().getResourceAsStream(RABBIT_PROPERTIES)
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