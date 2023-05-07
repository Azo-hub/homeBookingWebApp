package com.homeBookingWebApp.Model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Property extends Auditable <String>  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String name;
    private String propertyType;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private double propertyPrice;
    private String propertyCountry;
    private String propertyCity;
    private String propertyState;
    private String propertyAddress;
    private String propertyZipCode;

    @Column(columnDefinition = "text")
    private String description;

    private String propertyOwner;

    private double propertyTax;

    private double propertyServiceFee;

    private double propertyCleaningFee;
    
    private String[] reviews;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public double getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(double propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public String getPropertyCountry() {
		return propertyCountry;
	}

	public void setPropertyCountry(String propertyCountry) {
		this.propertyCountry = propertyCountry;
	}

	public String getPropertyState() {
		return propertyState;
	}

	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}

	public String getPropertyCity() {
		return propertyCity;
	}

	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getPropertyZipCode() {
		return propertyZipCode;
	}

	public void setPropertyZipCode(String propertyZipCode) {
		this.propertyZipCode = propertyZipCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPropertyOwner() {
		return propertyOwner;
	}

	public void setPropertyOwner(String propertyOwner) {
		this.propertyOwner = propertyOwner;
	}

	public double getPropertyTax() {
		return propertyTax;
	}

	public void setPropertyTax(double propertyTax) {
		this.propertyTax = propertyTax;
	}

	public double getPropertyServiceFee() {
		return propertyServiceFee;
	}

	public void setPropertyServiceFee(double propertyServiceFee) {
		this.propertyServiceFee = propertyServiceFee;
	}

	public double getPropertyCleaningFee() {
		return propertyCleaningFee;
	}

	public void setPropertyCleaningFee(double propertyCleaningFee) {
		this.propertyCleaningFee = propertyCleaningFee;
	}
	
	public String[] getReviews() {
		return reviews;
	}



	public void setReviews(String[] reviews) {
		this.reviews = reviews;
	}




}
