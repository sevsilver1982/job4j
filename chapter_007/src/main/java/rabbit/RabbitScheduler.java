package rabbit;

import static rabbit.Constants.RABBIT_INTERVAL;

public class RabbitScheduler {

    public static void main(String[] args) {
        new SimpleScheduler().start(
                Rabbit.class,
                Config.getInstance().getInteger(RABBIT_INTERVAL)
        );
    }

}