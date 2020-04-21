package quartz.rabbit;

import static quartz.rabbit.Constants.RABBIT_INTERVAL;

public class RabbitScheduler {

    public static void main(String[] args) {
        Config config = new Config();
        new SimpleScheduler().start(
                Rabbit.class,
                config.getInteger(RABBIT_INTERVAL)
        );
    }

}