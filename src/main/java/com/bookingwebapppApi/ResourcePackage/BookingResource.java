package com.bookingwebapppApi.ResourcePackage;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingwebapppApi.ExceptionPackage.PropertyBookingExistException;
import com.bookingwebapppApi.ModelPackage.Booking;
import com.bookingwebapppApi.ModelPackage.CheckInAndOutDate;
import com.bookingwebapppApi.ModelPackage.Userr;
import com.bookingwebapppApi.ServicePackage.BookingService;
import com.bookingwebapppApi.ServicePackage.CheckInAndOutDateService;
import com.bookingwebapppApi.ServicePackage.PropertyService;
import com.bookingwebapppApi.ServicePackage.UserService;
import com.bookingwebapppApi.UtilityPackage.HttpCustomResponse;
import com.bookingwebapppApi.UtilityPackage.MailConstructor;

@RestController
public class BookingResource {
	@Autowired
	private PropertyService propertyService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private CheckInAndOutDateService checkInAndOutDateService;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	private JavaMailSender mailSender;


	
	@PostMapping("/checkDateAvailability")
	public ResponseEntity<HttpCustomResponse> checkDateAvailability(@RequestParam("checkInDate") Date checkInDate,
			@RequestParam("checkOutDate") Date checkOutDate)
			throws PropertyBookingExistException {

		LocalDate checkInDateLocalDate = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate checkOutDateLocalDate = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		

		List<CheckInAndOutDate> dbCheckInAndOutDateList = checkInAndOutDateService.findAll();

		for (CheckInAndOutDate dbCheckInAndOutDate : dbCheckInAndOutDateList) {
			if (dbCheckInAndOutDate.getCheckInDate() == checkInDateLocalDate
					&& dbCheckInAndOutDate.getCheckOutDate() == checkOutDateLocalDate) {

				throw new PropertyBookingExistException("");

			}
		}

		return response(HttpStatus.OK, "Your dates are available! Check the property to rent");
	}

	
	
	
	@PostMapping("/checkPropertyAvailability")
	public ResponseEntity<HttpCustomResponse> checkPropertyAvailability(@RequestParam("checkInDate") Date checkInDate,
			@RequestParam("checkOutDate") Date checkOutDate, @RequestParam("propertyId") Long PropertyId)
			throws PropertyBookingExistException {

		LocalDate checkInDateLocalDate = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate checkOutDateLocalDate = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(checkInDateLocalDate);
		System.out.println(checkOutDateLocalDate);
		System.out.println(PropertyId);
		// Property property = propertyService.findById(PropertyId);

		List<CheckInAndOutDate> dbCheckInAndOutDateList = checkInAndOutDateService.findAll();

		for (CheckInAndOutDate dbCheckInAndOutDate : dbCheckInAndOutDateList) {
			if (dbCheckInAndOutDate.getCheckInDate() == checkInDateLocalDate
					&& dbCheckInAndOutDate.getCheckOutDate() == checkOutDateLocalDate
					&& dbCheckInAndOutDate.getProperty().getId() == PropertyId) {

				throw new PropertyBookingExistException("Booking Already Exist!");

			}
		}

		return response(HttpStatus.OK, "The dates are available!");
	}

	@PostMapping("/newBooking")
	public ResponseEntity<Booking> getEachPropertyById(HttpServletRequest request,
			@RequestParam("bookingFirstName") String bookingFirstName,
			@RequestParam("bookingLastName") String bookingLastName,
			@RequestParam("bookingEmailAddress") String bookingEmailAddress,
			@RequestParam("bookingPhoneNumber") String bookingPhoneNumber,
			@RequestParam("bookingHomePhoneNumber") String bookingHomePhoneNumber,
			@RequestParam("bookingCountry") String bookingCountry, @RequestParam("bookingStreet") String bookingStreet,
			@RequestParam("bookingCity") String bookingCity, @RequestParam("bookingState") String bookingState,
			@RequestParam("bookingZipCode") String bookingZipCode, @RequestParam("checkInDate") Date checkInDate,
			@RequestParam("checkOutDate") Date checkOutDate, @RequestParam("bookingNoOfDays") Long bookingNoOfDays,
			@RequestParam("bookingPropertyId") Long bookingPropertyId, Principal principal)
			throws PropertyBookingExistException {

		Userr loginUser = userService.findByUsername(principal.getName());

		Booking booking = bookingService.createNewBooking(bookingFirstName, bookingLastName, bookingEmailAddress,
				bookingPhoneNumber, bookingHomePhoneNumber, bookingCountry, bookingStreet, bookingCity, bookingState,
				bookingZipCode, checkInDate, checkOutDate, bookingNoOfDays, bookingPropertyId, loginUser);

		
		SimpleMailMessage loginUserEmail = mailConstructor.constructNewBookingEmailTravellerLoginUser(request.getLocale(), booking);
		mailSender.send(loginUserEmail);
		
		SimpleMailMessage BookingUserEmail = mailConstructor.constructNewBookingEmailTravellerBookingUser(request.getLocale(), booking);
		mailSender.send(BookingUserEmail);
		
		SimpleMailMessage OwnerEmail = mailConstructor.constructNewBookingEmailOwner(request.getLocale(), booking);
		mailSender.send(OwnerEmail);
		
		return new ResponseEntity<>(booking, HttpStatus.OK);

	}

	private ResponseEntity<HttpCustomResponse> response(HttpStatus httpStatus, String message) {

		return new ResponseEntity<>(new HttpCustomResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);

	}


}
