package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.10.108.127:1521/xe",
                    "c##madang", "madang");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
