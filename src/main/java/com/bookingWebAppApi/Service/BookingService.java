package com.bookingWebAppApi.Service;

import java.util.Date;
import java.util.List;

import com.bookingWebAppApi.Exception.PropertyBookingExistException;
import com.bookingWebAppApi.Model.Booking;
import com.bookingWebAppApi.Model.PaymentMethod;
import com.bookingWebAppApi.Model.Userr;

public interface BookingService {
    Booking findBybookingEmailAddress(String email);

    Booking save(Booking booking);

    Booking findById(Long id);

    Booking createNewBooking(String bookingFirstName, String bookingLastName, String bookingEmailAddress,
			String bookingPhoneNumber, String bookingHomePhoneNumber, String bookingCountry, String bookingStreet,
			String bookingCity, String bookingState, String bookingZipCode, Date checkInDate, Date checkOutDate,
			Long bookingNoOfDays, Long bookingPropertyId, Userr user, String noOfGuest, String noOfChildren,
			String pets, PaymentMethod paymentCard) throws PropertyBookingExistException;

    void deleteBookingById(Long Id);

	List<Booking> findAll();

	List<Booking> findByLoginUser(Userr loginUser);

	

	List<Booking> findByBookingFirstNameContaining(String searchInput);

	
	
}
