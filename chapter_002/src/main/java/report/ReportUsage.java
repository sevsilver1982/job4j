package report;

public class ReportUsage {

    public static void main(String[] args) {
        System.out.println(
                new JSONReport().generate("Report's name", "Report's body")
        );
    }

}