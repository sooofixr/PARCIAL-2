package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://ep-round-snow-abrscb5y-pooler.eu-west-2.aws.neon.tech/neondb?sslmode=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_YudOJC1lqtE2";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
