package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) throws SQLException {

        String connection_key= "jdbc:postgresql://ep-round-snow-abrscb5y-pooler.eu-west-2.aws.neon.tech/neondb?user=neondb_owner&password=npg_YudOJC1lqtE2&sslmode=require";

        Connection conn = DriverManager.getConnection(connection_key);
        System.out.println("Exitos "+ conn.getMetaData().getDatabaseProductVersion());

    }

}