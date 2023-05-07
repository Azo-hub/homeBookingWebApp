package com.bookingwebapppApi.ExceptionPackage;

public class AccountVerifiedException extends Exception {
    private static final long serialVersionUID = 1L;

    public AccountVerifiedException(String message) {

        super(message);
    }

}
