package com.bookingwebapppApi.ServicePackage;

import java.util.Date;

import com.bookingwebapppApi.ExceptionPackage.PropertyBookingExistException;
import com.bookingwebapppApi.ModelPackage.Booking;
import com.bookingwebapppApi.ModelPackage.Userr;

public interface BookingService {
    Booking findBybookingEmailAddress(String email);

    Booking save(Booking booking);

    Booking findById(Long id);

    Booking createNewBooking(String bookingFirstName, String bookingLastName, String bookingEmailAddress,
                             String bookingPhoneNumber, String bookingHomePhoneNumber, String bookingCountry, String bookingStreet,
                             String bookingCity, String bookingState, String bookingZipCode, Date checkInDate, Date checkOutDate,
                             Long bookingNoOfDays, Long bookingPropertyId, Userr user) throws PropertyBookingExistException;

    void deleteBookingById(Long Id);

}
