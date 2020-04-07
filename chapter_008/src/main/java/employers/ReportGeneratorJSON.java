package employers;

import java.util.List;

public class ReportGeneratorJSON implements IReportGenerator {

    @Override
    public String generate(List<Employer> employers) {
        StringBuilder text = new StringBuilder()
                .append("[");
        employers.stream().forEach(employer -> text
                .append("{")
                .append(String.format("\"Name\": \"%s\"", employer.getName()))
                .append(String.format("\"Hired\": \"%s\"", employer.getHired()))
                .append(String.format("\"Fired\": \"%s\"", employer.getFired()))
                .append(String.format("\"Salary\": \"%s\"", employer.getSalary()))
                .append("},")
        );
        text.setLength(text.length() - 1);
        text.append("]");
        return text.toString();
    }

}