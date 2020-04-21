package quartz.rabbit;

import java.io.IOException;
import java.util.Properties;

import static quartz.rabbit.Constants.RABBIT_PROPERTIES;

public class Config {
    private final Properties config;

    public Config() {
        this.config = new Properties();
        try {
            this.config.load(Config.class.getClassLoader().getResourceAsStream(RABBIT_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Properties not found");
        }
    }

    public String getString(String key) {
        return config.getProperty(key, "");
    }

    public Integer getInteger(String key) {
        return Integer.parseInt(config.getProperty(key, "0"));
    }

}