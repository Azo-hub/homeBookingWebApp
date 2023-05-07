package com.bookingwebapppApi.ResourcePackage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingwebapppApi.ModelPackage.Property;
import com.bookingwebapppApi.ModelPackage.Review;
import com.bookingwebapppApi.ServicePackage.CheckInAndOutDateService;
import com.bookingwebapppApi.ServicePackage.PropertyService;
import com.bookingwebapppApi.ServicePackage.ReviewService;
import com.bookingwebapppApi.UtilityPackage.HttpCustomResponse;

@RestController
public class PropertyResource {

	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private CheckInAndOutDateService checkInAndOutDateService;

	@PreAuthorize("hasAnyAuthority('user:update')")
	@PostMapping("/editProperty")
	public ResponseEntity<Property> editProperty(@RequestParam("id") Long id, @RequestParam("name") String propertyName,
			@RequestParam("propertyType") String propertyType, @RequestParam("propertyPrice") double propertyPrice,
			@RequestParam("propertyCountry") String propertyCountry,
			@RequestParam("propertyState") String propertyState, @RequestParam("propertyCity") String propertyCity,
			@RequestParam("propertyAddress") String propertyAddress,
			@RequestParam("propertyZipCode") String propertyZipCode, @RequestParam("description") String description,
			@RequestParam("propertyTax") double propertyTax,
			@RequestParam("propertyServiceFee") double propertyServiceFee,
			@RequestParam("propertyCleaningFee") double propertyCleaningFee) {

		Property editedProperty = propertyService.findById(id);
		editedProperty.setName(propertyName);
		editedProperty.setPropertyType(propertyType);
		editedProperty.setPropertyPrice(propertyPrice);
		editedProperty.setPropertyCountry(propertyCountry);
		editedProperty.setPropertyState(propertyState);
		editedProperty.setPropertyCity(propertyCity);
		editedProperty.setPropertyAddress(propertyAddress);
		editedProperty.setPropertyZipCode(propertyZipCode);
		editedProperty.setDescription(description);
		editedProperty.setPropertyTax(propertyTax);
		editedProperty.setPropertyServiceFee(propertyServiceFee);
		editedProperty.setPropertyCleaningFee(propertyCleaningFee);

		propertyService.save(editedProperty);

		return new ResponseEntity<>(editedProperty, HttpStatus.OK);

	}

	@PostMapping("/allPropertyByCategory")
	public ResponseEntity<List<Property>> getAllProperty(@RequestParam("propertyType") String propertyType) {

		List<Property> propertyList = propertyService.findByPropertyType(propertyType);

		return new ResponseEntity<>(propertyList, HttpStatus.OK);

	}

	@PostMapping("/eachPropertyById")
	public ResponseEntity<Property> getEachPropertyById(@RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);

		return new ResponseEntity<>(property, HttpStatus.OK);

	}

	@PostMapping("/allPropertyByOwner")
	public ResponseEntity<List<Property>> getAllPropertyByOwner(@RequestParam("propertyOwner") String propertyOwner) {

		List<Property> propertyList = propertyService.findByPropertyOwner(propertyOwner);

		return new ResponseEntity<>(propertyList, HttpStatus.OK);

	}
	
	@GetMapping("/allProperty")
	public ResponseEntity<List<Property>> getAllProperty() {

		List<Property> propertyList = propertyService.findAll();

		return new ResponseEntity<>(propertyList, HttpStatus.OK);

	}

	

	@PreAuthorize("hasAnyAuthority('user:update')")
	@PostMapping("/deleteProperty")
	public ResponseEntity<HttpCustomResponse> onDelete(@RequestParam("deletePropertyId") Long id) {
		
		checkInAndOutDateService.deleteByProperty(propertyService.findById(id));
		reviewService.deleteByProperty(propertyService.findById(id));
		propertyService.deletePropertyById(id);

		return response(HttpStatus.OK, "Property deleted Successfully!");
	}
	
	
	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/addReview")
	public ResponseEntity<HttpCustomResponse> onAddReview(@RequestParam("propertyId") Long propertyId,
			@RequestParam("reviewContent") String reviewContent, @RequestParam("reviewAuthor") String reviewAuthor, 
			@RequestParam("reviewLocation") String reviewLocation) {
		
		
		
			Property property = propertyService.findById(propertyId);
			reviewService.createReview(reviewContent, reviewAuthor, reviewLocation, property);
			
		
		
		return response(HttpStatus.OK, "Reviews added Successfully!");
	}
	
	
	@PostMapping("/reviewByProperty")
	public ResponseEntity<List<Review>> getReviewByProperty(@RequestParam("propertyId") Long propertyId) {
		
		
		
			Property property = propertyService.findById(propertyId);
			List<Review> propertyReview = reviewService.getReviewByProperty(property);
 		
		return new ResponseEntity<>(propertyReview, HttpStatus.OK);
	}


	private ResponseEntity<HttpCustomResponse> response(HttpStatus httpStatus, String message) {

		return new ResponseEntity<>(new HttpCustomResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);

	}

}