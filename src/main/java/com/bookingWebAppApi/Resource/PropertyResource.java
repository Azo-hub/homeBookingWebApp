package com.bookingWebAppApi.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookingWebAppApi.Model.Property;
import com.bookingWebAppApi.Model.Review;
import com.bookingWebAppApi.Service.BookingService;
import com.bookingWebAppApi.Service.CheckInAndOutDateService;
import com.bookingWebAppApi.Service.PropertyService;
import com.bookingWebAppApi.Service.ReviewService;
import com.bookingWebAppApi.Utility.HttpCustomResponse;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;

@RestController
public class PropertyResource {

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CheckInAndOutDateService checkInAndOutDateService;
	
	@Autowired
	private BookingService bookingService;

	private final Cloudinary cloudinary = Singleton.getCloudinary();

	@PreAuthorize("hasAnyAuthority('user:update')")
	@PostMapping("/editProperty")
	public ResponseEntity<Property> editProperty(@RequestBody Property property) {

		Property editedProperty = propertyService.findById(property.getId());

		editedProperty.setName(property.getName());
		editedProperty.setPropertyType(property.getPropertyType().toLowerCase());
		editedProperty.setPropertyPrice(property.getPropertyPrice());
		editedProperty.setPropertyCountry(property.getPropertyCountry());
		editedProperty.setPropertyState(property.getPropertyState());
		editedProperty.setPropertyCity(property.getPropertyCity());
		editedProperty.setPropertyZipCode(property.getPropertyZipCode());
		editedProperty.setPropertyCleaningFee(property.getPropertyCleaningFee());
		editedProperty.setTheSpace_noOfAccommodation(property.getTheSpace_noOfAccommodation());
		editedProperty.setTheSpace_noOfBedrooms(property.getTheSpace_noOfBedrooms());
		editedProperty.setBeds_noOfKing(property.getBeds_noOfKing());
		editedProperty.setBeds_noOfQueen(property.getBeds_noOfQueen());
		editedProperty.setBeds_noOfSingle(property.getBeds_noOfSingle());
		editedProperty.setBathrooms_noOfMasterBathroom(property.getBathrooms_noOfMasterBathroom());
		editedProperty.setBathrooms_noOfPrivateBathroom(property.getBathrooms_noOfPrivateBathroom());
		editedProperty.setBathrooms_noOfHalfBath(property.getBathrooms_noOfHalfBath());
		editedProperty.setSharedSpaces_kitchen(property.getSharedSpaces_kitchen());
		editedProperty.setSharedSpaces_laudryRoom(property.getSharedSpaces_laudryRoom());
		editedProperty.setSharedSpaces_outDoorParking(property.getSharedSpaces_outDoorParking());
		editedProperty.setSharedSpaces_garage(property.getSharedSpaces_garage());
		editedProperty.setSharedSpaces_balcony(property.getSharedSpaces_balcony());
		editedProperty.setSharedSpaces_backyard(property.getSharedSpaces_backyard());
		editedProperty.setPropertyAddress1(property.getPropertyAddress1());
		editedProperty.setPropertyAddress2(property.getPropertyAddress2());
		editedProperty.setAmenities_wifi(property.getAmenities_wifi());
		editedProperty.setAmenities_towelsBedsheetsSoapAndToiletpaper(
				property.getAmenities_towelsBedsheetsSoapAndToiletpaper());
		editedProperty.setAmenities_shampoo(property.getAmenities_shampoo());
		editedProperty.setAmenities_closetDrawers(property.getAmenities_closetDrawers());
		editedProperty.setAmenities_hairDryer(property.getAmenities_hairDryer());
		editedProperty.setAmenities_LEDTV(property.getAmenities_LEDTV());
		editedProperty.setAmenities_grill(property.getAmenities_grill());
		editedProperty.setAmenities_parking(property.getAmenities_parking());
		editedProperty.setAmenities_outdoorSwimmingPool(property.getAmenities_outdoorSwimmingPool());
		editedProperty.setAmenities_ironBoard(property.getAmenities_ironBoard());
		editedProperty.setAmenities_satelliteOrCable(property.getAmenities_satelliteOrCable());
		editedProperty.setAmenities_boardGames(property.getAmenities_boardGames());

		propertyService.save(editedProperty);

		return new ResponseEntity<>(editedProperty, HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('user:update')")
	@PostMapping("/editPropertyImage")
	public ResponseEntity<HttpCustomResponse> editPropertyImage(
			@RequestParam("propertyImage") MultipartFile multipartFile, @RequestParam("propertyId") Long propertyId,
			@RequestParam("fileIndex") int fileIndex) throws IOException {

		Property property = propertyService.findById(propertyId);

		List<String> dbImageUrls = property.getPropertyImageUrl();
		List<Boolean> dbImagePresent = property.getIsImage();
		List<String> dbImageFilenames = property.getPropertyImageFileName();

		if (multipartFile != null) {

			String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			byte[] bytes = multipartFile.getBytes();
			String newPorpertyImageName = "bookingwebapp" + property.getName() + filename + property.getId();

			/* deleting image file from cloudinary */
			Map deleteResult = cloudinary.uploader().destroy(property.getPropertyImageFileName().get(fileIndex),
					ObjectUtils.asMap("resource_type", "image"));

			property.getPropertyImageUrl().remove(fileIndex);

			property.getIsImage().remove(fileIndex);

			property.getPropertyImageFileName().remove(fileIndex);

			/* uploading image file to cloudinary */
			Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

			String publicId = uploadResult.get("public_id").toString();

			/* renaming uploaded file to cloudinary */
			Map uploadResultRename = cloudinary.uploader().rename(publicId, newPorpertyImageName,
					ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			// System.out.println(uploadResultRename.get("public_id"));
			String imageUrl = uploadResultRename.get("secure_url").toString();

			dbImageUrls.add(fileIndex, imageUrl);
			dbImagePresent.add(fileIndex, true);
			dbImageFilenames.add(fileIndex, newPorpertyImageName);

		}

		property.setPropertyImageUrl(dbImageUrls);
		property.setIsImage(dbImagePresent);
		property.setPropertyImageFileName(dbImageFilenames);
		propertyService.save(property);

		return response(HttpStatus.OK, "Image Uploaded Successfully");

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

		bookingService.deleteBookingByProperty(propertyService.findById(id));
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
