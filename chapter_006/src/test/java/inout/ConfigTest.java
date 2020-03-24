package inout;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigTest {
    private static Config config;

    @TempDir
    public static File file;

    @BeforeAll
    public static void load() throws IOException {
        File source = new File(file, "app.properties");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println(" = ##comment=");
            out.println("");
            out.println("нет знака равно");
            out.println("hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect");
            out.println("hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio");
            out.println("hibernate.connection.driver_class=org.postgresql.Driver");
            out.println("hibernate.connection.username=postgres");
            out.println("hibernate.connection.password=password");
            out.println("name= Petr Arsentev #comment");
        }
        config = new Config(source.getPath());
        config.load();
    }

    @Test
    public void value() {
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test
    public void getParamsCount() {
        assertThat(config.getParamsCount(), is(6));
    }

}