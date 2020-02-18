package inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();
    private int paramsCount = 0;

    public Config(final String path) {
        this.path = path;
    }

    public int getParamsCount() {
        return paramsCount;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(line -> {
                if (!line.trim().isEmpty()) {
                    int indexComment = line.indexOf("##");
                    String[] tmp = line
                            .substring(0, indexComment > 0 ? indexComment : line.length())
                            .trim()
                            .split("=");
                    if (tmp.length == 2) {
                        values.put(tmp[0].trim(), tmp[1].trim());
                        paramsCount++;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

}