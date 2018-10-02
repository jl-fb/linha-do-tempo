package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {

    public static Connection getConnection() {

        Connection conn = null;
        String url = "jdbc:sqlite::resource:db/";
        String db = "timeline.db";

        try {
            conn = DriverManager.getConnection(url+db);
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
        return conn;
    }
}