package com.bookingWebAppApi.Resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingWebAppApi.Model.Property;
import com.bookingWebAppApi.Model.Review;
import com.bookingWebAppApi.Service.CheckInAndOutDateService;
import com.bookingWebAppApi.Service.PropertyService;
import com.bookingWebAppApi.Service.ReviewService;
import com.bookingWebAppApi.Utility.HttpCustomResponse;

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
			@RequestParam("propertyCountry") String propertyCountry,@RequestParam("propertyCleaningFee") double propertyCleaningFee,
			@RequestParam("propertyState") String propertyState, @RequestParam("propertyCity") String propertyCity,
			@RequestParam("propertyAddress1") String propertyAddress1, @RequestParam("propertyAddress2") String propertyAddress2,
			@RequestParam("propertyZipCode") String propertyZipCode, @RequestParam("description") String description,
			@RequestParam("propertyTax") double propertyTax, @RequestParam("propertyServiceFee") double propertyServiceFee,
			@RequestParam("noOfAccommodation") String noOfAccommodation, @RequestParam("noOfBedrooms") String noOfBedrooms,
			@RequestParam("noOfKing") String noOfKing, @RequestParam("noOfQueen") String noOfQueen,
			@RequestParam("noOfSingle") String noOfSingle, @RequestParam("noOfMasterBathroom") String noOfMasterBathroom,
			@RequestParam("noOfPrivateBathroom") String noOfPrivateBathroom, @RequestParam("noOfHalfBath") String noOfHalfBath,
			@RequestParam("Kitchen") String Kitchen, @RequestParam("laudryRoom") String laudryRoom,
			@RequestParam("outDoorParking") String outDoorParking, @RequestParam("garage") String garage,
			@RequestParam("balcony") String balcony, @RequestParam("backyard") String backyard,
			@RequestParam("wifi") String wifi, @RequestParam("TowelsBedsheetsSoapAndToiletpaper") String TowelsBedsheetsSoapAndToiletpaper,
			@RequestParam("shampoo") String shampoo, @RequestParam("closetDrawers") String closetDrawers,
			@RequestParam("hairDryer") String hairDryer, @RequestParam("LEDTV") String LEDTV, @RequestParam("grill") String grill,
			@RequestParam("parking") String parking, @RequestParam("outdoorSwimmingPool") String outdoorSwimmingPool, 
			@RequestParam("iron&Board") String ironBoard, @RequestParam("satelliteOrCable") String satelliteOrCable, 
			@RequestParam("microwave") String microwave, @RequestParam("boardGames") String boardGames,
			@RequestParam("toaster") String toaster, @RequestParam("coffeeMaker") String coffeeMaker, 
			@RequestParam("stove") String stove) {
		

		Property editedProperty = propertyService.findById(id);
		editedProperty.setName(propertyName);
		editedProperty.setPropertyType(propertyType);
		editedProperty.setPropertyPrice(propertyPrice);
		editedProperty.setPropertyCountry(propertyCountry);
		editedProperty.setPropertyState(propertyState);
		editedProperty.setPropertyCity(propertyCity);
		editedProperty.setPropertyZipCode(propertyZipCode);
		editedProperty.setPropertyCleaningFee(propertyCleaningFee);
		editedProperty.setTheSpace_noOfAccommodation(noOfAccommodation);
		editedProperty.setTheSpace_noOfBedrooms(noOfBedrooms);
		editedProperty.setBeds_noOfKing(noOfKing);
		editedProperty.setBeds_noOfQueen(noOfQueen);
		editedProperty.setBeds_noOfSingle(noOfSingle);
		editedProperty.setBathrooms_noOfMasterBathroom(noOfMasterBathroom);
		editedProperty.setBathrooms_noOfPrivateBathroom(noOfPrivateBathroom);
		editedProperty.setBathrooms_noOfHalfBath(noOfHalfBath);
		editedProperty.setSharedSpaces_kitchen(Kitchen);
		editedProperty.setSharedSpaces_laudryRoom(laudryRoom);
		editedProperty.setSharedSpaces_outDoorParking(outDoorParking);
		editedProperty.setSharedSpaces_garage(garage);
		editedProperty.setSharedSpaces_balcony(balcony);
		editedProperty.setSharedSpaces_backyard(backyard);
		editedProperty.setPropertyAddress1(propertyAddress1);
		editedProperty.setPropertyAddress2(propertyAddress2);
		editedProperty.setAmenities_wifi(wifi);
		editedProperty.setAmenities_towelsBedsheetsSoapAndToiletpaper(TowelsBedsheetsSoapAndToiletpaper);
		editedProperty.setAmenities_shampoo(shampoo);
		editedProperty.setAmenities_closetDrawers(closetDrawers);
		editedProperty.setAmenities_hairDryer(hairDryer);
		editedProperty.setAmenities_LEDTV(LEDTV);
		editedProperty.setAmenities_grill(grill);
		editedProperty.setAmenities_parking(parking);
		editedProperty.setAmenities_outdoorSwimmingPool(outdoorSwimmingPool);
		editedProperty.setAmenities_ironBoard(ironBoard);
		editedProperty.setAmenities_satelliteOrCable(satelliteOrCable);
		editedProperty.setAmenities_microwave(microwave);
		editedProperty.setAmenities_boardGames(boardGames);
		editedProperty.setAmenities_toaster(toaster);
		editedProperty.setAmenities_coffeeMaker(coffeeMaker);
		editedProperty.setAmenities_stove(stove);
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
