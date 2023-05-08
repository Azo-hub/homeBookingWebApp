package com.homeBookingWebApp.Utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.homeBookingWebApp.Model.Booking;
import com.homeBookingWebApp.Model.Userr;
import com.homeBookingWebApp.Service.UserService;

@Component
public class MailConstructor {
    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    public SimpleMailMessage constructContactEmail(Locale locale, String Email, String Name, String Phone,
                                                   String Subject, String Content) {

        String message = "Email:" + " " + Email + "\n" + "Name:" + " " + Name + "\n" + "Phone Number:" + " " + Phone
                + "\n" + "Content:" + " " + Content;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("devreadone@gmail.com");
        email.setSubject(Subject);
        email.setText(message);
        email.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
        return email;

    }
    
    
    public SimpleMailMessage constructNotifyMessage(Locale locale, String userEmail, String Username, String Subject, String message) 
    	{

    	SimpleMailMessage email = new SimpleMailMessage();
    	email.setTo(userEmail);
    	email.setSubject(Subject);
    	email.setText(message);
    	email.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
    	
    	return email;

    }

    
    public SimpleMailMessage contactSupportEmail(Locale locale, String firstname, String lastname, String email, String subject,
    														String phonenumber, String problem) {
    	
    	String message = "Name:" + " " + firstname + " " + lastname + "\n\n" +
    			"Email:" + " " + email + "\n\n" + 
    			"Phone Number:" + " " + phonenumber + "\n\n" +
    			"Description of Problem:" + " " + problem ;

        SimpleMailMessage supportMail = new SimpleMailMessage();
        supportMail.setTo("devreadone@gmail.com");
        supportMail.setSubject(subject + " - ENQUIRIES FROM CUSTOMER");
        supportMail.setText(message);
        supportMail.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
        return supportMail;

    	
    	
    }

    public SimpleMailMessage constructResetTokenEmail(Locale locale, String token, Userr user,
                                                      String password) {

        

        String message = "\nHi "+user.getFirstname()+","+"\nYour new password is: \n"
                + password;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Valence Direct Booking Rental - User Password Reset Email");
        email.setText(message);
        email.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
        return email;

    }

    public SimpleMailMessage constructNewUserEmail(Locale locale, String token, Userr user,
                                                   String password) {


        String message = "\nThank you for signing up " + user.getUsername() +", Your password is: \n"
                + password;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Valence Direct Booking Rental - New User Email Verification");
        email.setText(message);
        email.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
        return email;

    }
    
    
    public SimpleMailMessage constructIdentityUploadEmail(Locale locale, Userr user) {


    	String message = "\nThank you "+ user.getUsername() +" for uploading your means of identification. We will get back to you \n"
    			+"after verification.";
    	SimpleMailMessage email = new SimpleMailMessage();
    	email.setTo(user.getEmail());
    	email.setSubject("Valence Direct Booking Rental - Identity Verification");
    	email.setText(message);
    	email.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
    	return email;

    }
    
    
    
    public SimpleMailMessage constructIdentityUploadEmailAdmin1(Locale locale, Userr user) {


    	String message = "\nA user "+ user.getUsername() +" has just uploaded his means of identification, please login \n"
    			+"to validate the uploaded document.";
    	SimpleMailMessage email = new SimpleMailMessage();
    	email.setTo("akintundeidris67@gmail.com");
    	email.setSubject("Valence Direct Booking Rental - Identity Verification");
    	email.setText(message);
    	email.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
    	return email;

    }

    
    
    
    public SimpleMailMessage constructIdentityUploadEmailAdmin2(Locale locale, Userr user) {


    	String message = "\nA user "+ user.getUsername() +" has just uploaded his means of identification, please login \n"
    			+"to validate the uploaded document.";
    	SimpleMailMessage email = new SimpleMailMessage();
    	email.setTo("readone.cybernet@gmail.com");
    	email.setSubject("Valence Direct Booking Rental - Identity Verification");
    	email.setText(message);
    	email.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
    	return email;

    }

    

    public SimpleMailMessage constructNewBookingEmailTravellerLoginUser(Locale locale, Booking booking) {
        // TODO Auto-generated method stub
        String message = "\nThank you " + booking.getLoginUser().getUsername() + " for booking a property with us \n"
                + "Your Booking details are as List below:\n\n" +
                "Booking ID: " + booking.getId() + "\n\n" +
                "Property Name: " + booking.getProperty().getName() + "\n\n" +
                "Booking Guest Name: " + booking.getBookingFirstName() + " " + booking.getBookingLastName() + "\n\n"
                + "Email Address: " + booking.getBookingEmailAddress() + "\n\n"+
                "Home Phone Number: " + booking.getBookingPhonehomeNumber() + "\n\n" +
                "Cell Phone Number: " + booking.getBookingPhoneNumber() + "\n\n" +
                "Check-in Date: " + booking.getBookingCheckInDate() + "\n\n" +
                "Check-out Date: " + booking.getBookingCheckOutDate() + "\n\n" +
                "Total: " + booking.getTotalPrice();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(booking.getLoginUser().getEmail());
        email.setSubject("Valence Direct Booking Rental - New Booking Notification");
        email.setText(message);
        email.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
        return email;
    }

    public SimpleMailMessage constructNewBookingEmailTravellerBookingUser(Locale locale, Booking booking) {
        // TODO Auto-generated method stub
        String message = "\nThank you " + booking.getLoginUser().getUsername() + " for booking a property with us \n"
                + "Your Booking details are as List below:\n\n" +
                "Booking ID: " + booking.getId() + "\n\n" +
                "Property Name: " + booking.getProperty().getName() + "\n\n" +
                "Booking Guest Name: " + booking.getBookingFirstName() + " " + booking.getBookingLastName() + "\n\n"
                + "Email Address: " + booking.getBookingEmailAddress() + "\n\n"+
                "Home Phone Number: " + booking.getBookingPhonehomeNumber() + "\n\n" +
                "Cell Phone Number: " + booking.getBookingPhoneNumber() + "\n\n" +
                "Check-in Date: " + booking.getBookingCheckInDate() + "\n\n" +
                "Check-out Date: " + booking.getBookingCheckOutDate() + "\n\n" +
                "Total: " + booking.getTotalPrice();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(booking.getBookingEmailAddress());
        email.setSubject("Valence Direct Booking Rental - New Booking Notification");
        email.setText(message);
        email.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
        return email;
    }


    public SimpleMailMessage constructNewBookingEmailOwner(Locale locale, Booking booking) {
        // TODO Auto-generated method stub
        String message = "\nA user with username: " + booking.getLoginUser().getUsername() + " just book a property with us \n"
                + "Your Booking details are as List below:\n\n" +
                "Booking ID: " + booking.getId() + "\n\n" +
                "Property Name: " + booking.getProperty().getName() + "\n\n" +
                "Booking Guest Name: " + booking.getBookingFirstName() + " " + booking.getBookingLastName() + "\n\n"
                + "Email Address: " + booking.getBookingEmailAddress() + "\n\n"+
                "Home Phone Number: " + booking.getBookingPhonehomeNumber() + "\n\n" +
                "Cell Phone Number: " + booking.getBookingPhoneNumber() + "\n\n" +
                "Check-in Date: " + booking.getBookingCheckInDate() + "\n\n" +
                "Check-out Date: " + booking.getBookingCheckOutDate() + "\n\n" +
                "Total: " + booking.getTotalPrice();


        Userr propertyOwner = userService.findByUsername(booking.getProperty().getCreatedBy());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(propertyOwner.getEmail());
        email.setSubject("Valence Direct Booking Rental - New Booking Notification");
        email.setText(message);
        email.setFrom(env.getProperty("azeezridwanaws4@gmail.com"));
        return email;

    }

}
