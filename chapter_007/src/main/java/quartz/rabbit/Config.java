package quartz.rabbit;

import java.io.IOException;
import java.util.Properties;

import static quartz.rabbit.Constants.RABBIT_PROPERTIES;

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

    public String getString(String key) {
        return config.getProperty(key, "");
    }

    public Integer getInteger(String key) {
        return Integer.parseInt(config.getProperty(key, "0"));
    }

}