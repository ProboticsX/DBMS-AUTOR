package main.helpers;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHelper {
    static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream("application.properties"));
                conn = DriverManager.getConnection(jdbcURL, properties.getProperty("username"),
                        properties.getProperty("password"));

            } catch (Exception e) {
            }
        } catch (Throwable oops) {
            oops.printStackTrace();
        }

        return conn;
    }

    public ResultSet runQuery(String query) {
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }

        return rs;

    }

    public void runUpdateQuery(String query){
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            e.printStackTrace();
        }

    }

    public void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (Throwable whatever) {
        }
    }

}