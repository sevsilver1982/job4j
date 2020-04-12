package quartz;

import static quartz.Constants.RABBIT_INTERVAL;
import static quartz.Constants.RABBIT_PROPERTIES;

public class RabbitScheduler {

    public static void main(String[] args) {
        Config config = new Config();
        config.load(
                Config.class.getClassLoader().getResourceAsStream(RABBIT_PROPERTIES)
        );
        new SimpleScheduler().start(
                Rabbit.class,
                config.getInteger(RABBIT_INTERVAL)
        );
    }

}