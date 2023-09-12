package org.brando.controller;

import org.brando.data.DBConnection;
import org.brando.model.SigIn;
import org.brando.model.UserSigIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInController {

    private SigIn sigIn;

    public SignInController(SigIn sigIn) {
        this.sigIn = sigIn;
    }

    private Connection connection = DBConnection.getConnection();
    private final String SELECT_USER_QUERY = "SELECT password FROM user WHERE emailAddress = ?";

    public void signIn() {
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement(SELECT_USER_QUERY);
            pst.setString(1, sigIn.getUserSigIn().getEmailAddress());
            ResultSet rs = pst.executeQuery();
            boolean existsUserWithEmail = rs.first();
            if (!existsUserWithEmail) {
                UserController userController = new UserController(sigIn.getUserSigIn());
                userController.create();
                System.err.println("Se ha registrado al usuario");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean validEmail(String email) {
        return email.matches("^\\w+@[a-zA-Z]+\\..*$");
    }
}
