package parking;


public class ReportEngine<T extends IReport> {
    private T report;
    private IParking parking;

    public ReportEngine(IParking parking, T report) {
        this.parking = parking;
        this.report = report;
    }

    public String generate() {
        return report.generate(parking.getParkList());
    }

}