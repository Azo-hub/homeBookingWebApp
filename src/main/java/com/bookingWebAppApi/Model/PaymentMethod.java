package com.bookingWebAppApi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PaymentMethod {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cardType;
	private String cardHolderName;
	private String expirationMonth;
	private String expirationYear;
	private Long cardNumber;
	private Long cvc;
	private String paymentMethodBillingAddressLine1;
	private String paymentMethodBillingAddressLine2;
	private String paymentMethodBillingCity;
	private String paymentMethodBillingState;
	private Long paymentMethodBillingZipCode;
	private String paymentMethodBillingCountry;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "card_owner_id")
	private Userr cardOwner;

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	public String getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Long getCvc() {
		return cvc;
	}
	public void setCvc(Long cvc) {
		this.cvc = cvc;
	}
	public String getPaymentMethodBillingAddressLine1() {
		return paymentMethodBillingAddressLine1;
	}
	public void setPaymentMethodBillingAddressLine1(String paymentMethodBillingAddressLine1) {
		this.paymentMethodBillingAddressLine1 = paymentMethodBillingAddressLine1;
	}
	public String getPaymentMethodBillingAddressLine2() {
		return paymentMethodBillingAddressLine2;
	}
	public void setPaymentMethodBillingAddressLine2(String paymentMethodBillingAddressLine2) {
		this.paymentMethodBillingAddressLine2 = paymentMethodBillingAddressLine2;
	}
	public String getPaymentMethodBillingCity() {
		return paymentMethodBillingCity;
	}
	public void setPaymentMethodBillingCity(String paymentMethodBillingCity) {
		this.paymentMethodBillingCity = paymentMethodBillingCity;
	}
	public String getPaymentMethodBillingState() {
		return paymentMethodBillingState;
	}
	public void setPaymentMethodBillingState(String paymentMethodBillingState) {
		this.paymentMethodBillingState = paymentMethodBillingState;
	}
	public Long getPaymentMethodBillingZipCode() {
		return paymentMethodBillingZipCode;
	}
	public void setPaymentMethodBillingZipCode(Long paymentMethodBillingZipCode) {
		this.paymentMethodBillingZipCode = paymentMethodBillingZipCode;
	}
	public String getPaymentMethodBillingCountry() {
		return paymentMethodBillingCountry;
	}
	public void setPaymentMethodBillingCountry(String paymentMethodBillingCountry) {
		this.paymentMethodBillingCountry = paymentMethodBillingCountry;
	}
	public Userr getCardOwner() {
		return cardOwner;
	}
	public void setCardOwner(Userr cardOwner) {
		this.cardOwner = cardOwner;
	}
	           



}
