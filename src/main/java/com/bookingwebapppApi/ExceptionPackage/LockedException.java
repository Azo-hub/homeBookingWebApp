package com.bookingwebapppApi.ExceptionPackage;

public class LockedException extends Exception {
    private static final long serialVersionUID = 1L;

    public LockedException(String message) {
        super(message);
    }

}
