package grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static grabber.Constants.PROPERTY_CRON_TIME;


public class JobScheduler {
    private static final String APP_PROPERTIES = "app.grabber.properties";

    private JobDetail buildJobDetail(Class<? extends org.quartz.Job> jobClass) {
        return JobBuilder.newJob(jobClass)
                .build();
    }

    private CronTrigger buildCronTrigger(String name, String group, String cronExpression) {
        return TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(cronExpression)
                )
                .build();
    }

    public void start(Parser parser, String cronExpression) {
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.getContext().put("parser", parser);
            scheduler.scheduleJob(
                    buildJobDetail(
                            JobOfferCollector.class
                    ),
                    Stream.of(
                            TriggerBuilder
                                    .newTrigger()
                                    .withIdentity("now", "grabber")
                                    .startNow()
                                    .build(),
                            buildCronTrigger(
                                    "SQLRUParser",
                                    "grabber",
                                    cronExpression
                            )
                    ).collect(Collectors.toSet()),
                    false
            );
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(JobScheduler.class.getClassLoader().getResourceAsStream(APP_PROPERTIES));
        SQLStore sqlStore = new SQLStore(properties);
        sqlStore.init(true);
        new JobScheduler().start(
                new SQLRUParser(sqlStore),
                properties.getProperty(PROPERTY_CRON_TIME)
        );
    }

}