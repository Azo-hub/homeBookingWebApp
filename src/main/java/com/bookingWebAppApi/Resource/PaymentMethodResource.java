package com.bookingWebAppApi.Resource;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingWebAppApi.Exception.UserNotFoundException;
import com.bookingWebAppApi.Model.PaymentMethod;
import com.bookingWebAppApi.Service.PaymentMethodService;
import com.bookingWebAppApi.Service.UserService;

@RestController
public class PaymentMethodResource {
	
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAnyAuthority('user:read')")
	@PostMapping("/newPaymentCard")
	public ResponseEntity<PaymentMethod> addPaymentCard(@RequestParam String cardType, @RequestParam String cardHolderName,
			@RequestParam String cardExpiryMonth, @RequestParam String cardExpiryYear, @RequestParam String cardNumber, 
			@RequestParam String cardCVC, Principal principal) throws UserNotFoundException {
		
			PaymentMethod paymentMethod = new PaymentMethod();
		
			if (userService.findByUsername(principal.getName()) != null) {
			
				paymentMethod.setCardType(cardType);
				paymentMethod.setCardHolderName(cardHolderName);
				paymentMethod.setExpirationMonth(cardExpiryMonth);
				paymentMethod.setExpirationYear(cardExpiryYear);
				paymentMethod.setCardNumber(Long.valueOf(cardNumber).longValue());
				paymentMethod.setCvc(Long.valueOf(cardCVC).longValue());
				paymentMethod.setCardOwner(userService.findByUsername(principal.getName()));
				paymentMethodService.save(paymentMethod);
				
			} else {
				throw new UserNotFoundException("User not Found! Try authenticating!");
			}
			
			
			return new ResponseEntity<>(paymentMethod, HttpStatus.OK);
	}
	
	
	
	
	@PreAuthorize("hasAnyAuthority('user:read')")
	@PostMapping("/newBillingAddress")
	public ResponseEntity<PaymentMethod> addBillingAddress(@RequestParam String paymentMethodBillingAddressLine1, 
			@RequestParam String paymentMethodBillingAddressLine2, @RequestParam String paymentMethodBillingCity, 
			@RequestParam String paymentMethodBillingState, @RequestParam Long paymentMethodBillingZipCode,
			@RequestParam String paymentMethodBillingCountry,
			@RequestParam Long paymentMethodId, Principal principal) throws UserNotFoundException {
		
			PaymentMethod paymentMethod = paymentMethodService.getById(paymentMethodId);
		
			if (userService.findByUsername(principal.getName()) != null) {
			
				paymentMethod.setPaymentMethodBillingAddressLine1(paymentMethodBillingAddressLine1);
				paymentMethod.setPaymentMethodBillingAddressLine2(paymentMethodBillingAddressLine2);
				paymentMethod.setPaymentMethodBillingCity(paymentMethodBillingCity);
				paymentMethod.setPaymentMethodBillingState(paymentMethodBillingState);
				paymentMethod.setPaymentMethodBillingZipCode(paymentMethodBillingZipCode);
				paymentMethod.setPaymentMethodBillingCountry(paymentMethodBillingCountry);
				
				paymentMethodService.save(paymentMethod);
				
			} else {
				throw new UserNotFoundException("User not Found! Try authenticating!");
			}
			
			
			return new ResponseEntity<>(paymentMethod, HttpStatus.OK);
	}
	
	
}
