package org.brando.exceptions;

public class NotValidEmailException extends Exception {
    public NotValidEmailException(String message) {
        super(message);
    }
}
