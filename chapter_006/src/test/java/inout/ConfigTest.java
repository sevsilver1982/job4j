package inout;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigTest {
    private Config config;

    @Before
    public void load() {
        config = new Config("app.properties");
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