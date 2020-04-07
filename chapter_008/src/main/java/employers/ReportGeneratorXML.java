package employers;

import java.util.List;

public class ReportGeneratorXML implements IReportGenerator {

    @Override
    public String generate(List<Employer> employers) {
        StringBuilder text = new StringBuilder()
                .append("<Employers>");
        employers.stream().forEach(employer -> text
                .append("<Employer>")
                .append(String.format("<Name>%s</Name>", employer.getName()))
                .append(String.format("<Hired>%s</Hired>", employer.getHired().getTime()))
                .append(String.format("<Fired>%s</Fired>", employer.getFired().getTime()))
                .append(String.format("<Salary>%s</Salary>", employer.getSalary()))
                .append("</Employer>")
        );
        text.append("</Employers>");
        return text.toString();
    }

}