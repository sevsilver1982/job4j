package tracker;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrackerSQLTest {

    @Test
    public void init() {
            TrackerSQL sql = new TrackerSQL(Collections.emptyList());
            assertTrue(sql.init());
    }

}