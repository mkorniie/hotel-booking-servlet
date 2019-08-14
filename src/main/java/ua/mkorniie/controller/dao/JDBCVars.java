package ua.mkorniie.controller.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCVars {
    private static final Logger logger = Logger.getLogger(JDBCVars.class);

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/hotel?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String jdbcUsername = "newuser";
    private static final String jdbcPassword = "12345";

    static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Connecting to DB: success");
        return connection;
    }

    public static boolean testConnection() {
        return (getConnection() != null);
    }

    private JDBCVars() {}
}
