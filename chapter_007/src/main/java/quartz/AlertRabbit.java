package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class AlertRabbit {

    public static class Rabbit implements Job {

        @Override
        public void execute(JobExecutionContext context)  {
            System.out.println("Rabbit runs here ...");
        }

    }

    public static void main(String[] args) {
        int interval = 0;
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            Properties config = new Properties();
            config.load(in);
            interval = parseInt(
                    config.getProperty("rabbit.interval")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(
                    newJob(
                            Rabbit.class).build(),
                    newTrigger()
                            .startNow()
                            .withSchedule(
                                    simpleSchedule()
                                            .withIntervalInSeconds(interval)
                                            .repeatForever()
                            )
                            .build()
            );
            scheduler.start();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

}