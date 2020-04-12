package quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class Rabbit implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("Rabbit runs here ...");
    }

}