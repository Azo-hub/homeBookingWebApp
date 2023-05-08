package com.homeBookingWebApp.Resource;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homeBookingWebApp.Exception.PropertyBookingExistException;
import com.homeBookingWebApp.Model.Booking;
import com.homeBookingWebApp.Model.CheckInAndOutDate;
import com.homeBookingWebApp.Model.Property;
import com.homeBookingWebApp.Model.Userr;
import com.homeBookingWebApp.Service.BookingService;
import com.homeBookingWebApp.Service.CheckInAndOutDateService;
import com.homeBookingWebApp.Service.PropertyService;
import com.homeBookingWebApp.Service.UserService;
import com.homeBookingWebApp.Utility.HttpCustomResponse;
import com.homeBookingWebApp.Utility.MailConstructor;

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
			@RequestParam("checkOutDate") Date checkOutDate) throws PropertyBookingExistException {

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
					&& dbCheckInAndOutDate.getProperty().getId() == PropertyId) {

				throw new PropertyBookingExistException("Booking Already Exist!");

			}

			if (dbCheckInAndOutDate.getCheckInDate() == checkInDateLocalDate
					&& dbCheckInAndOutDate.getCheckOutDate() == checkOutDateLocalDate
					&& dbCheckInAndOutDate.getProperty().getId() == PropertyId) {

				throw new PropertyBookingExistException("Booking Already Exist!");

			}
		}

		return response(HttpStatus.OK, "The dates are available!");
	}

	@PostMapping("/newBooking")
	public ResponseEntity<Booking> newBooking(HttpServletRequest request,
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

		SimpleMailMessage loginUserEmail = mailConstructor
				.constructNewBookingEmailTravellerLoginUser(request.getLocale(), booking);
		mailSender.send(loginUserEmail);

		SimpleMailMessage BookingUserEmail = mailConstructor
				.constructNewBookingEmailTravellerBookingUser(request.getLocale(), booking);
		mailSender.send(BookingUserEmail);

		SimpleMailMessage OwnerEmail = mailConstructor.constructNewBookingEmailOwner(request.getLocale(), booking);
		mailSender.send(OwnerEmail);

		return new ResponseEntity<>(booking, HttpStatus.OK);

	}

	@PostMapping("/addDates")
	public ResponseEntity<HttpCustomResponse> addCheckInAndOutDates(
			@RequestParam("checkinDateAdmin") String checkInDate,
			@RequestParam("checkoutDateAdmin") String checkOutDate, @RequestParam("propertyId") Long PropertyId) {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate checkInDateLocalDate = LocalDate.parse(checkInDate, format);
		LocalDate checkOutDateLocalDate = LocalDate.parse(checkOutDate, format);

		Property property = propertyService.findById(PropertyId);

		CheckInAndOutDate checkInAndOutDate = new CheckInAndOutDate();
		checkInAndOutDate.setCheckInDate(checkInDateLocalDate);
		checkInAndOutDate.setCheckOutDate(checkOutDateLocalDate);
		checkInAndOutDate.setProperty(property);
		checkInAndOutDateService.save(checkInAndOutDate);

		return response(HttpStatus.OK, "Dates added successsfully!");
	}

	@PostMapping("/getBookingById")
	public ResponseEntity<Booking> getBookingById(@RequestParam("bookingId") Long bookingId) {

		Booking booking = bookingService.findById(bookingId);

		return new ResponseEntity<>(booking, HttpStatus.OK);

	}

	@GetMapping("/allBookingByUser")
	public ResponseEntity<List<Booking>> getAllBookingByUser(Principal principal) {

		Userr loginUser = userService.findByUsername(principal.getName());

		List<Booking> bookingList = bookingService.findByLoginUser(loginUser);

		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);

	}

	@GetMapping("/allBooking")
	public ResponseEntity<List<Booking>> getAllBooking() {

		List<Booking> bookingList = bookingService.findAll();

		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('user:delete')")
	@PostMapping("/searchAllBooking")
	public ResponseEntity<List<Booking>> searchBooking(@RequestParam("searchInput") String searchInput) {

		List<Booking> searchBooking = new ArrayList<>();
		List<Booking> bookingById = bookingService.findByBookingFirstNameContaining(searchInput);

		searchBooking.addAll(bookingById);

		return new ResponseEntity<>(searchBooking, HttpStatus.OK);

	}

	private ResponseEntity<HttpCustomResponse> response(HttpStatus httpStatus, String message) {

		return new ResponseEntity<>(new HttpCustomResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);

	}

}
