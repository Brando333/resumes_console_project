package org.brando.model;

public class Login {
    private String emailAddress;
    private String password;

    public Login(String address, String password) {
        this.emailAddress = address;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" + "address='" + emailAddress + '\'' + ", password='" + password + '\'' + '}';
    }
}
