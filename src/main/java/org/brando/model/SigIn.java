package org.brando.model;

public class SigIn {
    UserSigIn userSigIn;

    public SigIn(UserSigIn userSigIn) {
        this.userSigIn = userSigIn;
    }

    public UserSigIn getUserSigIn() {
        return userSigIn;
    }

    public void setUserSigIn(UserSigIn userSigIn) {
        this.userSigIn = userSigIn;
    }
}
