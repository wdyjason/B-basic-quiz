package com.wdyjason.basicquiz.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String s) {
        super(s);
    }

    public UserNotFoundException() {
        super("User not found!");
    }
}
