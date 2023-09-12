package org.brando.controller;

import org.brando.data.DBConnection;
import org.brando.model.Login;
import org.brando.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private Login login;
    private Connection connection = DBConnection.getConnection();

    public LoginController(Login login) {
        this.login = login;
    }

    private final String SELECT_USER_QUERY = "SELECT * FROM user WHERE emailAddress = ?";

    /**
     * Returns the user with the id if exists else throw exception
     **/

    public User login() {
        try {
            PreparedStatement pst = connection.prepareStatement(SELECT_USER_QUERY);
            pst.setString(1, login.getEmailAddress());
            ResultSet rs = pst.executeQuery();
            boolean existsUser = rs.first();
            if (!existsUser) {
                System.err.println("El usuario no existe");
                throw new RuntimeException("User doesnt exist");
            } else {
                String rawPassword = login.getPassword();
                boolean passwordMatch = BCrypt.checkpw(rawPassword, rs.getString("password"));
                if (passwordMatch) {
                    return new User(rs.getInt("id"));
                } else {
                    return null;
                }
            }


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }
}
