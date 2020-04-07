package employers;

import java.util.Comparator;
import java.util.List;

public class ReportGeneratorCSVSalaryDESC implements IReportGenerator {

    @Override
    public String generate(List<Employer> employers) {

        class SalaryDESC implements Comparator<Employer> {
            @Override
            public int compare(Employer o1, Employer o2) {
                return Double.compare(o2.getSalary(), o1.getSalary());
            }
        }

        employers.sort(new SalaryDESC());
        StringBuilder text = new StringBuilder()
                .append("Name; Salary;").append(System.lineSeparator());
        employers.stream()
                .forEach(employer -> text
                        .append(employer.getName()).append(";")
                        .append(employer.getSalary()).append(";").append(System.lineSeparator())
        );
        return text.toString();
    }

}