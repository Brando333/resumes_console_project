package org.brando.exceptions;

public class EmailAlreadyTakenException extends Exception {
    public EmailAlreadyTakenException(String s) {
        super(s);
    }
}
