package utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:src/main/resources/db/dict.db";
            Connection conn = DriverManager.getConnection(dbURL);
            return conn;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
