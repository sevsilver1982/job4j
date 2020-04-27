package grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JobScheduler {

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

    public void start(IParser parser) {
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
                                    Config.getInstance().getProperty("cron.time")
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

    public static void main(String[] args) {
        new JobScheduler().start(
                new SQLRUParser(new Store())
        );
    }

}