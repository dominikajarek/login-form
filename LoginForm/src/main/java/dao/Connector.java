package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connector {

    protected Connection connection;
    protected Statement statement;

    public void connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "pagaj"
            );

            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
