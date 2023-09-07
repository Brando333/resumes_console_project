package org.brando.model;

class UserSigIn extends User {
    private String passwordReconfirmation;


    public UserSigIn(int id, String address, String password, String fullName, String passwordReconfirmation) {
        super(id, address, password, fullName);
        this.passwordReconfirmation = passwordReconfirmation;
    }

    public UserSigIn(String address, String password, String fullName, String passwordReconfirmation) {
        super(address, password, fullName);
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
