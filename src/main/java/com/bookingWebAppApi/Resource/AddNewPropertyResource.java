package com.bookingWebAppApi.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookingWebAppApi.Exception.AccountVerifiedException;
import com.bookingWebAppApi.Exception.EmailExistException;
import com.bookingWebAppApi.Exception.UserNotFoundException;
import com.bookingWebAppApi.Exception.UsernameExistException;
import com.bookingWebAppApi.Model.Property;
import com.bookingWebAppApi.Model.Userr;
import com.bookingWebAppApi.Service.PropertyService;
import com.bookingWebAppApi.Service.UserService;
import com.bookingWebAppApi.Utility.HttpCustomResponse;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;

@RestController
public class AddNewPropertyResource {

	@Autowired
	private UserService userService;

	@Autowired
	private PropertyService propertyService;

	private final Cloudinary cloudinary = Singleton.getCloudinary();

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/newProperty")
	public ResponseEntity<Property> newUserPost(HttpServletRequest request, @RequestBody Property property,
			Principal principal)
			throws UsernameExistException, UserNotFoundException, EmailExistException, AccountVerifiedException {

		Userr loginUser = userService.findByUsername(principal.getName());

		if (loginUser.getIsImage() == null || loginUser.getIsImage() == false) {

			throw new AccountVerifiedException(
					"You can only list a property after adding your profile picture! Contact support for further updates.");

		}

		if (loginUser.getDateOfBirth() == null) {

			throw new AccountVerifiedException(
					"You can only list a property after adding your date of birth! Contact support for further updates.");

		}

		if (loginUser.getIsVerified() == false || loginUser.getIsVerified() == null) {

			throw new AccountVerifiedException(
					"You can only list a property after your account is verified! Contact support for further updates.");

		}

		Property newProperty = propertyService.createProperty(property);
		newProperty.setPropertyType(property.getPropertyType().toLowerCase());
		newProperty.setPropertyOwner(userService.findByUsername(property.getCreatedBy()));
		propertyService.save(newProperty);

		return new ResponseEntity<>(newProperty, HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImages")
	public ResponseEntity <HttpCustomResponse> uploadPropertyImages (
			@RequestParam("propertyImages") List<MultipartFile> multipartFiles,
			@RequestParam("propertyId") Long propertyId) throws IOException {

		Property property = propertyService.findById(propertyId);

		
		List<String> imageUrls = new ArrayList<>();
		List<Boolean> imagePresent = new ArrayList<>();
		List<String> imageFilenames = new ArrayList<>();

		if (multipartFiles.size() != 0) {
			
			for (MultipartFile file : multipartFiles) {
				String filename = StringUtils.cleanPath(file.getOriginalFilename());
				byte[] bytes = file.getBytes();
				String newPorpertyImageName = "bookingwebapp" + property.getName() + filename + property.getId();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				String publicId = uploadResult.get("public_id").toString();

				/* renaming uploaded file to cloudinary */
				Map uploadResultRename = cloudinary.uploader().rename(publicId, newPorpertyImageName,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));
				
				
				//System.out.println(uploadResultRename.get("public_id"));
				String imageUrl = uploadResultRename.get("secure_url").toString();

				
				imageUrls.add(imageUrl);
				imagePresent.add(true);
				imageFilenames.add(newPorpertyImageName);
				
				System.out.println(imageUrl);
				
				

			}
		}

		property.setPropertyImageUrl(imageUrls);
		property.setIsImage(imagePresent);
		property.setPropertyImageFileName(imageFilenames);
		propertyService.save(property);
		
		return response(HttpStatus.OK, "Image Uploaded Successfully");
		

	}

	/*
	 * //Define a method to download files
	 * 
	 * @PostMapping("/downloadPropertyImageTest/{imageUrl}") public
	 * ResponseEntity<Resource> downloadPropertyImageTest(
	 * 
	 * @PathVariable("imageUrl") String imageUrl) throws IOException {
	 * 
	 * public static final String DIRECTORY = System.getProperty("user.home") +
	 * "/Downloads/uploads/";
	 * 
	 * Path filePath =
	 * Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
	 * 
	 * if (!Files.exists(filePath)) { throw new FileNotFoundException(filename +
	 * "was not found on the server"); }
	 * 
	 * Resource resource = new UrlResource(filePath.toUri());
	 * 
	 * HttpHeaders httpHeaders = new HttpHeaders(); httpHeaders.add("File-Name",
	 * filename); httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION,
	 * "attachment;File-Name=" + resource.getFilename());
	 * 
	 * return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.
	 * probeContentType(filePath))) .headers(httpHeaders).body(resource);
	 * 
	 * }
	 * 
	 */

	private ResponseEntity<HttpCustomResponse> response(HttpStatus httpStatus, String message) {

		return new ResponseEntity<>(new HttpCustomResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);

	}

}
