package org.brando.model;

public class User {

    private int id;
    private String fullName;
    private String emailAddress;
    private String password;

    //this constructor is used for retrieving users
    public User(int id, String address, String password, String fullName) {
        this.id = id;
        this.emailAddress = address;
        this.password = password;
        this.fullName = fullName;
    }

    public User(String address, String password, String fullName) {
        this.emailAddress = address;
        this.password = password;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", address='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
