package org.brando.controller;

import org.brando.data.DBConnection;
import org.brando.exceptions.EmailAlreadyTakenException;
import org.brando.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserController {


    private static Connection connection = DBConnection.getConnection();
    private final String CREATE_QUERY = "INSERT INTO user (fullName, emailAddress, password, keyHash) VALUES (?, ?, ?, ?)";
    private final String UPDATE_FULL_NAME_QUERY = "UPDATE user SET fullName = ? WHERE id = ?";
    private final String UPDATE_EMAIL_ADDRESS_QUERY = "UPDATE user SET emailAddress = ? WHERE id = ?";
    private final String UPDATE_PASSWORD_QUERY = "UPDATE user SET password = ? WHERE id = ?";
    private final String DELETE_USER_QUERY = "DELETE  FROM user WHERE  id = ?";
    private User user;

    public UserController(User user, Connection connection) {
        this.user = user;
        this.connection = connection;
    }

    public UserController(User user) {
        this.user = user;
    }

    public UserController() {
    }

    /**
     * Returns an existing user with all the fields
     */
    public static User getUser(int id) {
        try {
            String SELECT_USER_QUERY = "SELECT * FROM user WHERE  id = ?";
            PreparedStatement pst = connection.prepareStatement(SELECT_USER_QUERY);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();

            String emailAddress = resultSet.getString("emailAddress");
            String fullName = resultSet.getString("fullName");
            String password = resultSet.getString("password");
            String keyHash = resultSet.getString("keyHash");

            return new User(id, emailAddress, password, fullName, keyHash);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns an existing user with all the fields
     */
    public static int getUserId(String email) {
        try {
            String SELECT_USER_QUERY = "SELECT id FROM user WHERE  emailAddress = ?";
            PreparedStatement pst = connection.prepareStatement(SELECT_USER_QUERY);
            pst.setString(1, email);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int create() throws EmailAlreadyTakenException {
        try {
            PreparedStatement pst = connection.prepareStatement(CREATE_QUERY);
            pst.setString(1, user.getFullName());
            pst.setString(2, user.getEmailAddress());

            String salt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(user.getRawPassword(), salt);

            pst.setString(3, hashedPassword);
            pst.setString(4, salt);
            return pst.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {

            throw new EmailAlreadyTakenException(STR."the email \{user.getEmailAddress()} has already been taken");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePassword(String newPassword) {
        try {

            PreparedStatement pst = connection.prepareStatement(UPDATE_PASSWORD_QUERY);
            pst.setString(1, newPassword);
            pst.setInt(2, user.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEmailAddress(String newEmailAddress) {
        try {

            PreparedStatement pst = connection.prepareStatement(UPDATE_EMAIL_ADDRESS_QUERY);
            pst.setString(1, newEmailAddress);
            pst.setInt(2, user.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFullName(String newName) {
        try {

            PreparedStatement pst = connection.prepareStatement(UPDATE_FULL_NAME_QUERY);
            pst.setString(1, newName);
            pst.setInt(2, user.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser() {
        try {
            PreparedStatement pst = connection.prepareStatement(DELETE_USER_QUERY);
            pst.setInt(1, user.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
