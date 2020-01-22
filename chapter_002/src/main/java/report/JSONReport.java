package report;

public class JSONReport extends TextReport {

    @Override
    public String generate(String name, String body) {
        return String.format("{name : %s, body : %s}", name, body);
    }

}