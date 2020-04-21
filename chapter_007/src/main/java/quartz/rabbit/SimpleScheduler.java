package quartz.rabbit;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class SimpleScheduler {

    public void start(Class<? extends Job> jobClass, int interval) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(
                    newJob(jobClass).build(),
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