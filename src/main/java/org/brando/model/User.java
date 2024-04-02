package org.brando.model;

public class User {

    private int id;
    private String fullName;
    private String emailAddress;
    private String rawPassword;
    private String hashedPassword;

    private String keyHash;


    public User(int id) {
        this.id = id;
    }

    /**
     * this constructor is used for retrieving users
     **/
    public User(int id, String address, String rawPassword, String fullName, String passwordKey) {
        this.id = id;
        this.emailAddress = address;
        this.rawPassword = rawPassword;
        this.fullName = fullName;
        this.keyHash = passwordKey;
    }

    /**
     * this constructor is also used for retrieving users
     **/
    public User(String address, String rawPassword, String fullName, String passwordKey) {
        this.emailAddress = address;
        this.rawPassword = rawPassword;
        this.fullName = fullName;
        this.keyHash = passwordKey;

    }


    /**
     * this constructor is used for creating new users
     **/
    public User(String fullName, String emailAddress, String rawPassword) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.rawPassword = rawPassword;
    }


    public String getKeyHash() {
        return keyHash;
    }

    public void setKeyHash(String keyHash) {
        this.keyHash = keyHash;
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

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
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
                ", password='" + rawPassword + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
