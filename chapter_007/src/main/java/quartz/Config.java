package quartz;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    Properties config;

    public void load(InputStream propertiesStream) {
        config = new Properties();
        try {
            config.load(propertiesStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        return config.getProperty(key, "");
    }

    public Integer getInteger(String key) {
        return Integer.parseInt(config.getProperty(key, "0"));
    }

}