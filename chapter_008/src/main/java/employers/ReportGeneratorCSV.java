package employers;

import java.util.List;

public class ReportGeneratorCSV implements IReportGenerator {

    @Override
    public String generate(List<Employer> employers) {
        StringBuilder text = new StringBuilder()
                .append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        employers.stream().forEach(employer -> text
                .append(employer.getName()).append(";")
                .append(employer.getHired().getTime()).append(";")
                .append(employer.getFired().getTime()).append(";")
                .append(employer.getSalary()).append(";").append(System.lineSeparator())
        );
        return text.toString();
    }

}