package com.bookingwebapppApi.ExceptionPackage;

public class PropertyBookingExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public PropertyBookingExistException(String message) {

        super(message);
    }

}
