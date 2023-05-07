package com.homeBookingWebApp.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate bookingCheckInDate;

    private LocalDate bookingCheckOutDate;

    private Double totalPrice;

    @ManyToOne
    @JoinColumn(nullable = false, name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private Userr loginUser;

    private String bookingFirstName;

    private String bookingLastName;

    private String bookingEmailAddress;

    private String bookingPhoneNumber;

    private String bookingPhonehomeNumber;

    private String bookingCountry;

    private String bookingStreet;

    private String bookingCity;

    private String bookingState;

    private String bookingZipCode;
    
    public Long getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Long noOfDays) {
		this.noOfDays = noOfDays;
	}

	private Long noOfDays;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getBookingCheckInDate() {
		return bookingCheckInDate;
	}

	public void setBookingCheckInDate(LocalDate bookingCheckInDate) {
		this.bookingCheckInDate = bookingCheckInDate;
	}

	public LocalDate getBookingCheckOutDate() {
		return bookingCheckOutDate;
	}

	public void setBookingCheckOutDate(LocalDate bookingCheckOutDate) {
		this.bookingCheckOutDate = bookingCheckOutDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Userr getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Userr loginUser) {
		this.loginUser = loginUser;
	}

	public String getBookingFirstName() {
		return bookingFirstName;
	}

	public void setBookingFirstName(String bookingFirstName) {
		this.bookingFirstName = bookingFirstName;
	}

	public String getBookingLastName() {
		return bookingLastName;
	}

	public void setBookingLastName(String bookingLastName) {
		this.bookingLastName = bookingLastName;
	}

	public String getBookingEmailAddress() {
		return bookingEmailAddress;
	}

	public void setBookingEmailAddress(String bookingEmailAddress) {
		this.bookingEmailAddress = bookingEmailAddress;
	}

	public String getBookingPhoneNumber() {
		return bookingPhoneNumber;
	}

	public void setBookingPhoneNumber(String bookingPhoneNumber) {
		this.bookingPhoneNumber = bookingPhoneNumber;
	}

	public String getBookingPhonehomeNumber() {
		return bookingPhonehomeNumber;
	}

	public void setBookingPhonehomeNumber(String bookingPhonehomeNumber) {
		this.bookingPhonehomeNumber = bookingPhonehomeNumber;
	}

	public String getBookingCountry() {
		return bookingCountry;
	}

	public void setBookingCountry(String bookingCountry) {
		this.bookingCountry = bookingCountry;
	}

	public String getBookingStreet() {
		return bookingStreet;
	}

	public void setBookingStreet(String bookingStreet) {
		this.bookingStreet = bookingStreet;
	}

	public String getBookingCity() {
		return bookingCity;
	}

	public void setBookingCity(String bookingCity) {
		this.bookingCity = bookingCity;
	}

	public String getBookingState() {
		return bookingState;
	}

	public void setBookingState(String bookingState) {
		this.bookingState = bookingState;
	}

	public String getBookingZipCode() {
		return bookingZipCode;
	}

	public void setBookingZipCode(String bookingZipCode) {
		this.bookingZipCode = bookingZipCode;
	}

}
