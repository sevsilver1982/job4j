package SRP;

import java.util.function.Predicate;

public class ReportEngine<T extends IReportGenerator> {
    private T reportGenerator;
    private IStore store;

    public ReportEngine(MemoryStore store, T reportGenerator) {
        this.store = store;
        this.reportGenerator = reportGenerator;
    }

    public String generate(Predicate<Employer> filter) {
        return reportGenerator.generate(store.getEmployers(filter));
    }

    public String generate() {
        return reportGenerator.generate(store.getEmployers());
    }

}