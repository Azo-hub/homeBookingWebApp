package com.homeBookingWebApp.Exception;

public class EmailExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public EmailExistException(String message) {

        super(message);
    }

}
