package dao;

import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private String login = "SELECT * FROM users WHERE email = ? and password = ?";
    private String register = "INSERT INTO users (email, password) VALUES (?, ?)";
    private Connector connector;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    public LoginDao() {

    }

    public User getByEmailPassword(String email, String password) throws SQLException {

        try {
            connector.connect();
            preparedStatement = connector.connection.prepareStatement(login);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = getUser(resultSet);

            resultSet.close();
            preparedStatement.close();
            connector.connection.close();

            return user;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getUser(resultSet);
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString("email"), resultSet.getString("password"));
    }
}
