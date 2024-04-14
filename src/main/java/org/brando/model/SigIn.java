package org.brando.model;

public class SigIn extends User {
    private User user;
    private String passwordReconfirmation;


    public SigIn(String fullName, String emailAddress, String rawPassword, String passwordReconfirmation) {
        super(fullName, emailAddress, rawPassword);
        this.user = new User(fullName, emailAddress, rawPassword);
        this.passwordReconfirmation = passwordReconfirmation;
    }


    public User getUser() {
        return user;
    }

    public String getRawPasswordReconfirmation() {
        return passwordReconfirmation;
    }

    public void setPasswordReconfirmation(String passwordReconfirmation) {
        this.passwordReconfirmation = passwordReconfirmation;
    }
}
