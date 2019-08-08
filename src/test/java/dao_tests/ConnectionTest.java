package dao_tests;

import org.junit.jupiter.api.Test;
import ua.mkorniie.controller.dao.JDBCVars;

public class ConnectionTest {
    @Test
    void JDBCConnects() {
        assert (JDBCVars.testConnection());
    }
}
