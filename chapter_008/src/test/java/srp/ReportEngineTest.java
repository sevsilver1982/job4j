package srp;

import ocp.ReportGeneratorJSON;
import ocp.ReportGeneratorXML;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReportEngineTest {

    @Test
    public void reportGeneratorCSVTest() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employer worker1 = new Employer("worker1", now, now, 100);
        Employer worker2 = new Employer("worker2", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        assertThat(
                new ReportEngine<>(store, new ReportGeneratorCSV()).generate(employer -> employer.equals(worker1)),
                is(new StringBuilder()
                        .append("Name; Hired; Fired; Salary;").append(System.lineSeparator())
                        .append(worker1.getName()).append(";")
                        .append(worker1.getHired().getTime()).append(";")
                        .append(worker1.getFired().getTime()).append(";")
                        .append(worker1.getSalary()).append(";").append(System.lineSeparator())
                        .toString()
                )
        );
    }

    @Test
    public void reportGeneratorHTMLTest() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employer worker1 = new Employer("worker1", now, now, 100);
        Employer worker2 = new Employer("worker2", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        assertThat(
                new ReportEngine<>(store, new ReportGeneratorHTML()).generate(),
                is(new StringBuilder()
                        .append("<table>")
                        .append("<tr>")
                        .append("<th>Name</th>")
                        .append("<th>Hired</th>")
                        .append("<th>Fired</th>")
                        .append("<th>Salary</th>")
                        .append("</tr>")
                        .append("<tr>")
                        .append(String.format("<td>%s</td>", worker1.getName()))
                        .append(String.format("<td>%s</td>", worker1.getHired().getTime()))
                        .append(String.format("<td>%s</td>", worker1.getFired().getTime()))
                        .append(String.format("<td>%s</td>", worker1.getSalary()))
                        .append("</tr>")
                        .append("<tr>")
                        .append(String.format("<td>%s</td>", worker2.getName()))
                        .append(String.format("<td>%s</td>", worker2.getHired().getTime()))
                        .append(String.format("<td>%s</td>", worker2.getFired().getTime()))
                        .append(String.format("<td>%s</td>", worker2.getSalary()))
                        .append("</tr>")
                        .append("</table>")
                        .toString()
                )
        );
    }

    @Test
    public void reportGeneratorCSVSalaryEURTest() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employer worker1 = new Employer("worker1", now, now, 100);
        Employer worker2 = new Employer("worker2", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        String currency = "EUR";
        double rate = 85.37;
        assertThat(
                new ReportEngine<>(store, new ReportGeneratorCSVSalaryByRate(currency, rate)).generate(),
                is(new StringBuilder()
                        .append(String.format("Name; Hired; Fired; Salary %s;", currency)).append(System.lineSeparator())
                        .append(worker1.getName()).append(";")
                        .append(worker1.getHired().getTime()).append(";")
                        .append(worker1.getFired().getTime()).append(";")
                        .append(String.format("%.2f", worker1.getSalary() / rate)).append(";").append(System.lineSeparator())
                        .append(worker2.getName()).append(";")
                        .append(worker2.getHired().getTime()).append(";")
                        .append(worker2.getFired().getTime()).append(";")
                        .append(String.format("%.2f", worker2.getSalary() / rate)).append(";").append(System.lineSeparator())
                        .toString()
                )
        );
    }

    @Test
    public void reportGeneratorCSVSalaryDESCTest() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employer worker1 = new Employer("worker1", now, now, 100);
        Employer worker2 = new Employer("worker2", now, now, 200);
        Employer worker3 = new Employer("worker3", now, now, 300);
        Employer worker4 = new Employer("worker4", now, now, 400);
        Employer worker5 = new Employer("worker5", now, now, 500);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        store.add(worker4);
        store.add(worker5);
        assertThat(
                new ReportEngine<>(store, new ReportGeneratorCSVSalaryDESC()).generate(),
                is(new StringBuilder()
                        .append("Name; Salary;").append(System.lineSeparator())
                        .append(worker5.getName()).append(";")
                        .append(worker5.getSalary()).append(";").append(System.lineSeparator())
                        .append(worker4.getName()).append(";")
                        .append(worker4.getSalary()).append(";").append(System.lineSeparator())
                        .append(worker3.getName()).append(";")
                        .append(worker3.getSalary()).append(";").append(System.lineSeparator())
                        .append(worker2.getName()).append(";")
                        .append(worker2.getSalary()).append(";").append(System.lineSeparator())
                        .append(worker1.getName()).append(";")
                        .append(worker1.getSalary()).append(";").append(System.lineSeparator())
                        .toString()
                )
        );
    }

    @Test
    public void reportGeneratorXMLTest() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employer worker1 = new Employer("worker1", now, now, 100);
        Employer worker2 = new Employer("worker2", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        assertThat(
                new ReportEngine<>(store, new ReportGeneratorXML()).generate(),
                is(new StringBuilder()
                        .append("<Employers>")
                        .append("<Employer>")
                        .append(String.format("<Name>%s</Name>", worker1.getName()))
                        .append(String.format("<Hired>%s</Hired>", worker1.getHired().getTime()))
                        .append(String.format("<Fired>%s</Fired>", worker1.getFired().getTime()))
                        .append(String.format("<Salary>%s</Salary>", worker1.getSalary()))
                        .append("</Employer>")
                        .append("<Employer>")
                        .append(String.format("<Name>%s</Name>", worker2.getName()))
                        .append(String.format("<Hired>%s</Hired>", worker2.getHired().getTime()))
                        .append(String.format("<Fired>%s</Fired>", worker2.getFired().getTime()))
                        .append(String.format("<Salary>%s</Salary>", worker2.getSalary()))
                        .append("</Employer>")
                        .append("</Employers>")
                        .toString()
                )
        );
    }

    @Test
    public void reportGeneratorJSONTest() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employer worker1 = new Employer("worker1", now, now, 100);
        Employer worker2 = new Employer("worker2", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        assertThat(
                new ReportEngine<>(store, new ReportGeneratorJSON()).generate(),
                is(new StringBuilder()
                        .append("[")
                        .append("{")
                        .append(String.format("\"Name\": \"%s\"", worker1.getName()))
                        .append(String.format("\"Hired\": \"%s\"", worker1.getHired()))
                        .append(String.format("\"Fired\": \"%s\"", worker1.getFired()))
                        .append(String.format("\"Salary\": \"%s\"", worker1.getSalary()))
                        .append("},")
                        .append("{")
                        .append(String.format("\"Name\": \"%s\"", worker2.getName()))
                        .append(String.format("\"Hired\": \"%s\"", worker2.getHired()))
                        .append(String.format("\"Fired\": \"%s\"", worker2.getFired()))
                        .append(String.format("\"Salary\": \"%s\"", worker2.getSalary()))
                        .append("}")
                        .append("]")
                        .toString()
                )
        );
    }

}