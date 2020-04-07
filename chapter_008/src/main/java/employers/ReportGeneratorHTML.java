package employers;

import java.util.List;

public class ReportGeneratorHTML implements IReportGenerator {

    @Override
    public String generate(List<Employer> employers) {
        StringBuilder text = new StringBuilder()
                .append("<table>")
                .append("<tr>")
                .append("<th>Name</th>")
                .append("<th>Hired</th>")
                .append("<th>Fired</th>")
                .append("<th>Salary</th>")
                .append("</tr>");
        employers.stream().forEach(employer -> text
                .append("<tr>")
                .append(String.format("<td>%s</td>", employer.getName()))
                .append(String.format("<td>%s</td>", employer.getHired().getTime()))
                .append(String.format("<td>%s</td>", employer.getFired().getTime()))
                .append(String.format("<td>%s</td>", employer.getSalary()))
                .append("</tr>")
        );
        text.append("</table>");
        return text.toString();
    }

}