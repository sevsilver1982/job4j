package grabber;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.*;

public class JobOfferCollector implements Job {
    private static final Logger LOG = LogManager.getLogger(JobScheduler.class.getName());
    private static final String URL = "https://www.sql.ru/forum/job-offers/";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LOG.info("init");
            SchedulerContext schedulerContext = jobExecutionContext.getScheduler().getContext();
            Parser parser = (Parser) schedulerContext.get("parser");
            Store store = parser.getStore();
            LOG.info("begin grabber proc");
            parser.list(URL).forEach(post -> {
                LOG.info(post);
                store.save(post);
            });
            LOG.info("end grabber proc");
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

}