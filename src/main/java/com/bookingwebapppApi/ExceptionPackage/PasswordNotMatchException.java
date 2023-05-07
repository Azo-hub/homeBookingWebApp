package com.bookingwebapppApi.ExceptionPackage;

public class PasswordNotMatchException extends Exception {
    private static final long serialVersionUID = 1L;

    public PasswordNotMatchException(String message) {
        super(message);
    }

}
