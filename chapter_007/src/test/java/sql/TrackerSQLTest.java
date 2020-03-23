package sql;

import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TrackerSQLTest {

    @Test
    public void init() {
            TrackerSQL sql = new TrackerSQL(Collections.EMPTY_LIST);
            assertThat(sql.init(), is(true));
    }

}