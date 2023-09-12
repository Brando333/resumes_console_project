package org.brando.model;

public class UserSigIn extends User {
    private String passwordReconfirmation;

    public UserSigIn(int id, String address, String rawPassword, String fullName, String passwordKey, String passwordReconfirmation) {
        super(id, address, rawPassword, fullName, passwordKey);
        this.passwordReconfirmation = passwordReconfirmation;
    }

    public UserSigIn(String address, String rawPassword, String fullName, String passwordKey, String passwordReconfirmation) {
        super(address, rawPassword, fullName, passwordKey);
        this.passwordReconfirmation = passwordReconfirmation;
    }

    public String getPasswordReconfirmation() {
        return passwordReconfirmation;
    }

    public void setPasswordReconfirmation(String passwordReconfirmation) {
        this.passwordReconfirmation = passwordReconfirmation;
    }

    @Override
    public String toString() {
        return "UserSigIn{" +
                "passwordReconfirmation='" + passwordReconfirmation + '\'' +
                '}';
    }
}
