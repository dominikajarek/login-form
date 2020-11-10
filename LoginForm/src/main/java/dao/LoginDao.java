package dao;

import models.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDao {

    protected Connector connector;
    protected PreparedStatement preparedStatement;
    protected String selectStatement = "SELECT * FROM users WHERE email = ? and password = ?";
    protected String insertStatement = "INSERT INTO users (email, password) VALUES (?, ?)";

    public LoginDao() {
        connector = new Connector();
    }


    public void insertUser(User user) {

        try {
            connector.connect();
            preparedStatement = connector.connection.prepareStatement(insertStatement);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connector.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
