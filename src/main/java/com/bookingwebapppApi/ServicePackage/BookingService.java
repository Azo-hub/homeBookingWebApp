package com.bookingwebapppApi.ServicePackage;

import java.util.Date;
import java.util.List;

import com.bookingwebapppApi.ExceptionPackage.PropertyBookingExistException;
import com.bookingwebapppApi.ExceptionPackage.UserNotFoundException;
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

	List<Booking> findAll();

	List<Booking> findByLoginUser(Userr loginUser);

	

	List<Booking> findByBookingFirstNameContaining(String searchInput);

}
