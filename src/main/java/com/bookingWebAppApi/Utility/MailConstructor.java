package com.bookingWebAppApi.Utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bookingWebAppApi.Model.Booking;
import com.bookingWebAppApi.Model.Userr;
import com.bookingWebAppApi.Service.UserService;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailConstructor {
	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@Autowired
	private TemplateEngine templateEngine;

	public SimpleMailMessage constructContactEmail(Locale locale, String Email, String Name, String Phone,
			String Subject, String Content) {

		String message = "Email:" + " " + Email + "\n" + "Name:" + " " + Name + "\n" + "Phone Number:" + " " + Phone
				+ "\n" + "Content:" + " " + Content;

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo("azeezazeezdev@gmail.com");
		email.setSubject(Subject);
		email.setText(message);
		email.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return email;

	}

	public SimpleMailMessage constructNotifyMessage(Locale locale, String userEmail, String Username, String Subject,
			String message) {

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(userEmail);
		email.setSubject(Subject);
		email.setText(message);
		email.setFrom(env.getProperty("azeezazeezdev@gmail.com"));

		return email;

	}

	public SimpleMailMessage contactSupportEmail(Locale locale, String firstname, String lastname, String email,
			String subject, String phonenumber, String problem) {

		String message = "Name:" + " " + firstname + " " + lastname + "\n\n" + "Email:" + " " + email + "\n\n"
				+ "Phone Number:" + " " + phonenumber + "\n\n" + "Description of Problem:" + " " + problem;

		SimpleMailMessage supportMail = new SimpleMailMessage();
		supportMail.setTo("azeezazeezdev@gmail.com");
		supportMail.setSubject(subject + " - ENQUIRIES FROM CUSTOMER");
		supportMail.setText(message);
		supportMail.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return supportMail;

	}

	public SimpleMailMessage contactPropertyOwnerEmail(Locale locale, String firstname, String lastname, String email,
			String subject, String phonenumber, String problem, String ownerUsername, String propertyName) {

		String message = "Name:" + " " + firstname + " " + lastname + "\n\n" + "Email:" + " " + email + "\n\n"
				+ "Phone Number:" + " " + phonenumber + "\n\n" + "Property Name: " + propertyName + "\n\n"
				+ "Description of Problem:" + " " + problem;

		SimpleMailMessage propertyOwnerMail = new SimpleMailMessage();
		propertyOwnerMail.setTo(userService.findByUsername(ownerUsername).getEmail());
		propertyOwnerMail.setSubject(subject + " - ENQUIRIES FROM CUSTOMER");
		propertyOwnerMail.setText(message);
		propertyOwnerMail.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return propertyOwnerMail;

	}

	public SimpleMailMessage constructResetTokenEmail(Locale locale, String token, Userr user, String password) {

		String message = "\nHi " + user.getFirstname() + "," + "\nYour new password is: \n" + password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Home Booking Rental - User Password Reset Email");
		email.setText(message);
		email.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return email;

	}

	public SimpleMailMessage constructNewUserEmail(Locale locale, String token, Userr user, String password) {

		String appUrl = "https://www.valencedirectbookingrentals.com/";
		String message = "\nThank you for signing up " + user.getUsername() + ", You can log in to \n\n" + appUrl
				+ "\n\n with your password: \n" + password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Home Booking Rental - New User Email Verification");
		email.setText(message);
		email.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return email;

	}

	public SimpleMailMessage constructIdentityUploadEmail(Locale locale, Userr user) {

		String message = "\nThank you " + user.getUsername()
				+ " for uploading your means of identification. We will get back to you \n" + "after verification.";
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Home Booking Rental - Identity Verification");
		email.setText(message);
		email.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return email;

	}

	public SimpleMailMessage constructIdentityUploadEmailAdmin1(Locale locale, Userr user) {

		String message = "\nA user " + user.getUsername()
				+ " has just uploaded his means of identification, please login \n"
				+ "to validate the uploaded document.";
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo("azeezazeezdev@gmail.com");
		email.setSubject("Home Booking Rental - Identity Verification");
		email.setText(message);
		email.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return email;

	}

	public SimpleMailMessage constructIdentityUploadEmailAdmin2(Locale locale, Userr user) {

		String message = "\nA user " + user.getUsername()
				+ " has just uploaded his means of identification, please login \n"
				+ "to validate the uploaded document.";
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo("readone.cybernet@gmail.com");
		email.setSubject("Home Booking Rental - Identity Verification");
		email.setText(message);
		email.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return email;

	}

	public SimpleMailMessage constructNewBookingEmailTravellerLoginUser(Locale locale, Booking booking) {
		// TODO Auto-generated method stub
		String message = "\nThank you " + booking.getLoginUser().getUsername() + " for booking a property with us \n"
				+ "Your Booking details are as List below:\n\n" + "Booking ID: " + booking.getId() + "\n\n"
				+ "Property Name: " + booking.getProperty().getName() + "\n\n" + "Booking Guest Name: "
				+ booking.getBookingFirstName() + " " + booking.getBookingLastName() + "\n\n" + "Email Address: "
				+ booking.getBookingEmailAddress() + "\n\n" + "Home Phone Number: "
				+ booking.getBookingPhonehomeNumber() + "\n\n" + "Cell Phone Number: " + booking.getBookingPhoneNumber()
				+ "\n\n" + "Check-in Date: " + booking.getBookingCheckInDate() + "\n\n" + "Check-out Date: "
				+ booking.getBookingCheckOutDate() + "\n\n" + "Number of Guest: " + booking.getNoOfGuest() + "\n\n"
				+ "Number of Children: " + booking.getNoOfChildren() + "\n\n" + "Pets: " + booking.getPets() + "\n\n"
				+ "Total: " + booking.getTotalPrice();

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(booking.getLoginUser().getEmail());
		email.setSubject("Home Booking Rental - New Booking Notification");
		email.setText(message);
		email.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return email;
	}

	public SimpleMailMessage constructNewBookingEmailTravellerBookingUser(Locale locale, Booking booking) {
		// TODO Auto-generated method stub
		String message = "\nThank you " + booking.getLoginUser().getUsername() + " for booking a property with us \n"
				+ "Your Booking details are as List below:\n\n" + "Booking ID: " + booking.getId() + "\n\n"
				+ "Property Name: " + booking.getProperty().getName() + "\n\n" + "Booking Guest Name: "
				+ booking.getBookingFirstName() + " " + booking.getBookingLastName() + "\n\n" + "Email Address: "
				+ booking.getBookingEmailAddress() + "\n\n" + "Home Phone Number: "
				+ booking.getBookingPhonehomeNumber() + "\n\n" + "Cell Phone Number: " + booking.getBookingPhoneNumber()
				+ "\n\n" + "Check-in Date: " + booking.getBookingCheckInDate() + "\n\n" + "Check-out Date: "
				+ booking.getBookingCheckOutDate() + "\n\n" + "Number of Guest: " + booking.getNoOfGuest() + "\n\n"
				+ "Number of Children: " + booking.getNoOfChildren() + "\n\n" + "Pets: " + booking.getPets() + "\n\n"
				+ "Total: " + booking.getTotalPrice();

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(booking.getBookingEmailAddress());
		email.setSubject("Home Booking Rental - New Booking Notification");
		email.setText(message);
		email.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return email;
	}

	public SimpleMailMessage constructNewBookingEmailOwner(Locale locale, Booking booking) {
		// TODO Auto-generated method stub
		String message = "\nA user with username: " + booking.getLoginUser().getUsername()
				+ " just book a property with us \n" + "Your Booking details are as List below:\n\n" + "Booking ID: "
				+ booking.getId() + "\n\n" + "Property Name: " + booking.getProperty().getName() + "\n\n"
				+ "Booking Guest Name: " + booking.getBookingFirstName() + " " + booking.getBookingLastName() + "\n\n"
				+ "Email Address: " + booking.getBookingEmailAddress() + "\n\n" + "Home Phone Number: "
				+ booking.getBookingPhonehomeNumber() + "\n\n" + "Cell Phone Number: " + booking.getBookingPhoneNumber()
				+ "\n\n" + "Check-in Date: " + booking.getBookingCheckInDate() + "\n\n" + "Check-out Date: "
				+ booking.getBookingCheckOutDate() + "\n\n" + "Number of Guest: " + booking.getNoOfGuest() + "\n\n"
				+ "Number of Children: " + booking.getNoOfChildren() + "\n\n" + "Pets: " + booking.getPets() + "\n\n"
				+ "Total: " + booking.getTotalPrice();

		Userr propertyOwner = userService.findByUsername(booking.getProperty().getCreatedBy());

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(propertyOwner.getEmail());
		email.setSubject("Home Booking Rental - New Booking Notification");
		email.setText(message);
		email.setFrom(env.getProperty("azeezazeezdev@gmail.com"));
		return email;

	}

}
