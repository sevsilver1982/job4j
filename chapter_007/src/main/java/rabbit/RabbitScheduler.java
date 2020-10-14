package rabbit;

public class RabbitScheduler {
    public static final String RABBIT_INTERVAL = "rabbit.interval";

    public static void main(String[] args) {
        new SimpleScheduler().start(
                Rabbit.class,
                Config.getInstance().getInteger(RABBIT_INTERVAL)
        );
    }

}