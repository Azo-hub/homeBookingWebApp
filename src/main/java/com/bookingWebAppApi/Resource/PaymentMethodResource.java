package com.bookingWebAppApi.Resource;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingWebAppApi.Exception.UserNotFoundException;
import com.bookingWebAppApi.Model.PaymentMethod;
import com.bookingWebAppApi.Model.Userr;
import com.bookingWebAppApi.Service.PaymentMethodService;
import com.bookingWebAppApi.Service.UserService;
import com.bookingWebAppApi.Utility.HttpCustomResponse;
import com.bookingWebAppApi.Utility.SecurityUtility;

import jakarta.servlet.http.HttpServletRequest;

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
	
	
	
	@PreAuthorize("hasAnyAuthority('user:read')")
	@GetMapping("/getAllPaymentMethod")
	public ResponseEntity <List<PaymentMethod>> getAllPaymentMethod() {
		
			List<PaymentMethod> paymentMethodList = paymentMethodService.getAllPaymentMethod();
		
			return new ResponseEntity<>(paymentMethodList, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAnyAuthority('user:read')")
	@PostMapping("/setDefaultPaymentMethod")
	public ResponseEntity <List<PaymentMethod>> setDefaultPaymentMethod(@RequestParam("paymentMethodId") Long paymentMethodId,
			@RequestParam("value") String value) {
		
			List<PaymentMethod> paymentCardList = paymentMethodService.getAllPaymentMethod();
			
			for(PaymentMethod paymentCard : paymentCardList) {
				
				paymentCard.setDefaultPaymentMethod(false);
				paymentMethodService.save(paymentCard);
			}
		
			PaymentMethod paymentMethod = paymentMethodService.getById(paymentMethodId);
			
			if(Boolean.parseBoolean(value) == true) {
				paymentMethod.setDefaultPaymentMethod(false);
				paymentMethodService.save(paymentMethod);
			}
			
			if(Boolean.parseBoolean(value) == false) {
				paymentMethod.setDefaultPaymentMethod(true);
				paymentMethodService.save(paymentMethod);
			}
			
			List<PaymentMethod> paymentMethodList = paymentMethodService.getAllPaymentMethod();
			
		
			return new ResponseEntity<>(paymentMethodList, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasAnyAuthority('user:read')")
	@PostMapping("/removeCard")
	public ResponseEntity <HttpCustomResponse> removePaymentCard(@RequestParam("paymentMethodId") Long paymentMethodId) {
		
			paymentMethodService.removeCard(paymentMethodId);
		
			return response(HttpStatus.OK, "Card removed successfully!");
	}
	
	
	
	@PreAuthorize("hasAnyAuthority('user:read')")
	@PostMapping("/addNewBillingAddress")
	public ResponseEntity<PaymentMethod> addNewBillingAddress(@RequestParam String paymentMethodBillingAddressLine1, 
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
			
			List<PaymentMethod> paymentCardList = paymentMethodService.getAllPaymentMethod();
			
			for(PaymentMethod paymentCard : paymentCardList) {
				
				paymentCard.setDefaultPaymentMethod(false);
				paymentMethodService.save(paymentCard);
			}
			
			PaymentMethod paymentCard = paymentMethodService.getById(paymentMethodId);
			paymentCard.setDefaultPaymentMethod(true);
			paymentMethodService.save(paymentCard);
			
			
			return new ResponseEntity<>(paymentCard, HttpStatus.OK);
	}
	

	private ResponseEntity<HttpCustomResponse> response(HttpStatus httpStatus, String message) {

		return new ResponseEntity<>(new HttpCustomResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);

	}


	
}
