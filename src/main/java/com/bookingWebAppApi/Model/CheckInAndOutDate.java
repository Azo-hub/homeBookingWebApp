package com.bookingWebAppApi.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class CheckInAndOutDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String noOfGuest;

    @OneToOne(targetEntity = Property.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "property_id")
    private Property property;

    private Long propertyIId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getNoOfGuest() {
		return noOfGuest;
	}

	public void setNoOfGuest(String noOfGuest) {
		this.noOfGuest = noOfGuest;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Long getPropertyIId() {
		return propertyIId;
	}

	public void setPropertyIId(Long propertyIId) {
		this.propertyIId = propertyIId;
	}



}
