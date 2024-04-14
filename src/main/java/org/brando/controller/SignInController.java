package org.brando.controller;

import org.brando.data.DBConnection;
import org.brando.exceptions.EmailAlreadyTakenException;
import org.brando.exceptions.NotValidEmailException;
import org.brando.model.SigIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignInController {

    private final SigIn sigIn;
    private final Connection connection = DBConnection.getConnection();
    private final String SELECT_USER_QUERY = "SELECT id FROM user WHERE emailAddress = ?";
    private final String GET_USER_QUERY = "SELECT password FROM user WHERE emailAddress = ?";

    public SignInController(SigIn sigIn) {
        this.sigIn = sigIn;
    }


    //return the user id of the just created user
    public int signIn() throws EmailAlreadyTakenException, NotValidEmailException {
        UserController userController = new UserController(sigIn.getUser());
        userController.create();
        String email = sigIn.getUser().getEmailAddress();
        if (!isValidEmail(email)) {
            throw new NotValidEmailException("The email " + email + " doesn´t match with a valid email");
        }
        return UserController.getUserId(email);
    }


    /**
     * returns false if the user already exists with the associated email.
     * return true if the email is not registered yet.
     */

    private boolean checkNonExistingUserWithEmail() {
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement(SELECT_USER_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, sigIn.getEmailAddress());
            ResultSet rs = pst.executeQuery();
            return rs.first();

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error in the checkNonExistingUserWithEmail() method");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^\\w+@[a-zA-Z]+\\..*$");
    }
}
