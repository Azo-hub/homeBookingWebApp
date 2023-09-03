package com.bookingWebAppApi.Service.Impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingWebAppApi.Exception.PropertyBookingExistException;
import com.bookingWebAppApi.Model.Booking;
import com.bookingWebAppApi.Model.CheckInAndOutDate;
import com.bookingWebAppApi.Model.PaymentMethod;
import com.bookingWebAppApi.Model.Property;
import com.bookingWebAppApi.Model.Userr;
import com.bookingWebAppApi.Repository.BookingRepository;
import com.bookingWebAppApi.Service.BookingService;
import com.bookingWebAppApi.Service.CheckInAndOutDateService;
import com.bookingWebAppApi.Service.PropertyService;


@Service
public class BookingServiceImpl implements BookingService  {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CheckInAndOutDateService checkInAndOutDateService;

    @Autowired
    private PropertyService propertyService;


    @Override
    public Booking findBybookingEmailAddress(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Booking save(Booking booking) {
        // TODO Auto-generated method stub
        return bookingRepository.save(booking);
    }

    
	@Override
    public Booking findById(Long id) {
        // TODO Auto-generated method stub
        return bookingRepository.getReferenceById(id);
    }

    @Override
    public Booking createNewBooking(String bookingFirstName, String bookingLastName, String bookingEmailAddress,
                                    String bookingPhoneNumber, String bookingHomePhoneNumber, String bookingCountry, String bookingStreet,
                                    String bookingCity, String bookingState, String bookingZipCode, Date checkInDate, Date checkOutDate,
                                    Long bookingNoOfDays, Long bookingPropertyId, Userr user, String noOfGuest, 
                    				String noOfChildren, String pets, PaymentMethod paymentMethod) throws PropertyBookingExistException {
        // TODO Auto-generated method stub

        LocalDate checkInDateLocalDate = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate checkOutDateLocalDate = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        List<CheckInAndOutDate> dbCheckInAndOutDateList = checkInAndOutDateService.findAll();

        for (CheckInAndOutDate dbCheckInAndOutDate : dbCheckInAndOutDateList) {
        	
        	if (dbCheckInAndOutDate.getCheckInDate() == checkInDateLocalDate && 
					dbCheckInAndOutDate.getProperty().getId() == bookingPropertyId) {

				throw new PropertyBookingExistException("Booking Already Exist!");

			}

        	
            if (dbCheckInAndOutDate.getCheckInDate() == checkInDateLocalDate
                    && dbCheckInAndOutDate.getCheckOutDate() == checkOutDateLocalDate
                    && dbCheckInAndOutDate.getProperty().getId() == bookingPropertyId) {

                throw new PropertyBookingExistException("Booking Already Exist!");

            }
        }

        Property property = propertyService.findById(bookingPropertyId);

        Booking booking = new Booking();

        booking.setBookingCheckInDate(checkInDateLocalDate);
        booking.setBookingCheckOutDate(checkOutDateLocalDate);
        booking.setProperty(property);
        booking.setLoginUser(user);
        booking.setBookingFirstName(bookingFirstName);
        booking.setBookingLastName(bookingLastName);
        booking.setBookingEmailAddress(bookingEmailAddress);
        booking.setBookingPhonehomeNumber(bookingHomePhoneNumber);
        booking.setBookingPhoneNumber(bookingPhoneNumber);
        booking.setNoOfDays(bookingNoOfDays);
        booking.setBookingCity(bookingCity);
        booking.setBookingState(bookingState);
        booking.setBookingCountry(bookingCountry);
        booking.setBookingStreet(bookingStreet);
        booking.setBookingZipCode(bookingZipCode);
        booking.setNoOfGuest(noOfGuest);
        booking.setNoOfChildren(noOfChildren);
        booking.setPets(pets);
        booking.setPaymentMethod(paymentMethod);

        double subPrice = bookingNoOfDays * property.getPropertyPrice();

        booking.setTotalPrice(subPrice + property.getPropertyCleaningFee());

        bookingRepository.save(booking);

        /*CheckInAndOutDate checkInAndOutDate = new CheckInAndOutDate();
        checkInAndOutDate.setCheckInDate(checkInDateLocalDate);
        checkInAndOutDate.setCheckOutDate(checkOutDateLocalDate);
        checkInAndOutDate.setProperty(property);
        checkInAndOutDateService.save(checkInAndOutDate);*/

        return booking;
    }

    @Override
    public void deleteBookingByProperty(Property property) {
        // TODO Auto-generated method stub
    	bookingRepository.deleteByProperty(property);

    }

	@Override
	public List<Booking> findAll() {
		// TODO Auto-generated method stub
		return bookingRepository.findAll();
	}

	
	@Override
	public List<Booking> findByLoginUser(Userr loginUserId){
		
		
		return bookingRepository.findByLoginUser(loginUserId);
	}
	
	
	@Override
    public List<Booking> findByBookingFirstNameContaining(String searchInput) {

		List<Booking> bookingList = bookingRepository.findByBookingFirstNameContaining(searchInput);

				
		return bookingList;

		
    }
	
	
	 

	
}
