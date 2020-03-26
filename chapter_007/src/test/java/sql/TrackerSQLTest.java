package sql;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TrackerSQLTest {

    @Test
    public void init() {
            TrackerSQL sql = new TrackerSQL(Collections.EMPTY_LIST);
            assertThat(sql.init(), is(true));
    }

}