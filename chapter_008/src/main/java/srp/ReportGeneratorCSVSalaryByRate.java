package srp;


import java.util.List;

public class ReportGeneratorCSVSalaryByRate implements IReportGenerator {
    private String currency;
    private double rate;

    public ReportGeneratorCSVSalaryByRate(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    @Override
    public String generate(List<Employer> employers) {
        StringBuilder text = new StringBuilder()
                .append(String.format("Name; Hired; Fired; Salary %s;", currency)).append(System.lineSeparator());
        employers.forEach(employer ->
                text.append(employer.getName()).append(";")
                        .append(employer.getHired().getTime()).append(";")
                        .append(employer.getFired().getTime()).append(";")
                        .append(String.format("%.2f", employer.getSalary() / rate)).append(";").append(System.lineSeparator())
        );
        return text.toString();
    }

}