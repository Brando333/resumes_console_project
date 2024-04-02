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
    private final Login login;
    private final Connection connection = DBConnection.getConnection();


    public LoginController(Login login) {
        this.login = login;
    }

    /**
     * Returns the user with the id if exists else throw exception
     **/

    public User login() {
        try {
            String SELECT_USER_QUERY = "SELECT id, password FROM user WHERE emailAddress = ?";
            PreparedStatement pst = connection.prepareStatement(SELECT_USER_QUERY);
            pst.setString(1, login.getEmailAddress());
            ResultSet rs = pst.executeQuery();
            boolean existsUser = rs.next();

            if (!existsUser) {
                return null;
            } else {
                System.err.println("Usuario existe");
                String rawPassword = login.getPassword();
                boolean passwordMatch = BCrypt.checkpw(rawPassword, rs.getString("password"));
                if (passwordMatch) {
                    UserController userController = new UserController();
                    int id = rs.getInt("id");
                    return userController.getUser(id);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("error en el login");
        }

    }
}
