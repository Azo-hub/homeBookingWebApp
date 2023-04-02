package com.bookingwebapppApi.ResourcePackage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookingwebapppApi.ExceptionPackage.EmailExistException;
import com.bookingwebapppApi.ExceptionPackage.UserNotFoundException;
import com.bookingwebapppApi.ExceptionPackage.UsernameExistException;
import com.bookingwebapppApi.ModelPackage.Property;
import com.bookingwebapppApi.ServicePackage.PropertyService;
import com.bookingwebapppApi.UtilityPackage.HttpCustomResponse;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;

@RestController

public class AddNewPropertyResource {
	@Autowired
	private PropertyService propertyService;
	
	private final Cloudinary cloudinary = Singleton.getCloudinary();
	
	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/newProperty")
	public ResponseEntity<Property> newUserPost(HttpServletRequest request,@RequestBody Property property /*
			 MultipartFile profileImage */ )
			throws UsernameExistException, UserNotFoundException, EmailExistException {

		Property newProperty = propertyService.createProperty(property);
		newProperty.setPropertyType(property.getPropertyType().toLowerCase());
		newProperty.setPropertyOwner(property.getCreatedBy());
		propertyService.save(newProperty);
		

		return new ResponseEntity<>(newProperty, HttpStatus.OK);

	}
	
	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage1")
	public ResponseEntity<HttpCustomResponse> propertyImage1(
			@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {
			
			Property property = propertyService.findById(propertyId);
			if (!propertyImage.isEmpty()) {
			
			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_1"+property.getName()+property.getId();
				//File file = new File(name);
				//FileOutputStream fos = new FileOutputStream(file);
				//fos.write(bytes);
				//fos.close();
				
				/* uploading image file to cloudinary */
				  Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));
				 
				
				/*uploading video file to cloudinary */
				
				//Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("resource_type", "video", 
				//		"overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name, ObjectUtils.asMap("resource_type", "image", 
						"overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			

			
		}

		
		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}
	
	
	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage2")
	public ResponseEntity<HttpCustomResponse> propertyImage2(
			@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {
			
			Property property = propertyService.findById(propertyId);
			if (!propertyImage.isEmpty()) {
			
			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_2"+property.getName()+property.getId();
				//File file = new File(name);
				//FileOutputStream fos = new FileOutputStream(file);
				//fos.write(bytes);
				//fos.close();
				
				/* uploading image file to cloudinary */
				  Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));
				 
				
				/*uploading video file to cloudinary */
				
				//Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("resource_type", "video", 
				//		"overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name, ObjectUtils.asMap("resource_type", "image", 
						"overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			

			
		}

		
		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	
	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage3")
	public ResponseEntity<HttpCustomResponse> propertyImage3(
			@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {
			
			Property property = propertyService.findById(propertyId);
			if (!propertyImage.isEmpty()) {
			
			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_3"+property.getName()+property.getId();
				//File file = new File(name);
				//FileOutputStream fos = new FileOutputStream(file);
				//fos.write(bytes);
				//fos.close();
				
				/* uploading image file to cloudinary */
				  Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));
				 
				
				/*uploading video file to cloudinary */
				
				//Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("resource_type", "video", 
				//		"overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name, ObjectUtils.asMap("resource_type", "image", 
						"overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			

			
		}

		
		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	
	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage4")
	public ResponseEntity<HttpCustomResponse> propertyImage4(
			@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {
			
			Property property = propertyService.findById(propertyId);
			if (!propertyImage.isEmpty()) {
			
			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_4"+property.getName()+property.getId();
				//File file = new File(name);
				//FileOutputStream fos = new FileOutputStream(file);
				//fos.write(bytes);
				//fos.close();
				
				/* uploading image file to cloudinary */
				  Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));
				 
				
				/*uploading video file to cloudinary */
				
				//Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("resource_type", "video", 
				//		"overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name, ObjectUtils.asMap("resource_type", "image", 
						"overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			

			
		}

		
		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	
	
	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage5")
	public ResponseEntity<HttpCustomResponse> propertyImage5(
			@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {
			
			Property property = propertyService.findById(propertyId);
			if (!propertyImage.isEmpty()) {
				
				try {
					byte[] bytes = propertyImage.getBytes();
					String name = "bookingwebapp_5"+property.getName()+property.getId();
					//File file = new File(name);
					//FileOutputStream fos = new FileOutputStream(file);
					//fos.write(bytes);
					//fos.close();
					
					/* uploading image file to cloudinary */
					  Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));
					 
					
					/*uploading video file to cloudinary */
					
					//Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("resource_type", "video", 
					//		"overwrite", "true"));
					String publicId = uploadResult.get("public_id").toString();
					/*
					 * version += "/v"+ uploadResult.get("version").toString();
					 * model.addAttribute("version",version);
					 */
					cloudinary.uploader().rename(publicId, name, ObjectUtils.asMap("resource_type", "image", 
							"overwrite", "true"));

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				

				
			

						
		}

		
		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	

	
	@PostMapping("/allPropertyByCategory")
	public ResponseEntity<List<Property>> getAllProperty (
			@RequestParam("propertyType") String propertyType) {
			
			List<Property> propertyList = propertyService.findByPropertyType(propertyType);
				
			return new ResponseEntity<>(propertyList, HttpStatus.OK);

	}

	
	
	@PostMapping("/eachPropertyById")
	public ResponseEntity<Property> getEachPropertyById (
			@RequestParam("propertyId") Long propertyId) {
			
			Property property = propertyService.findById(propertyId);
				
			return new ResponseEntity<>(property, HttpStatus.OK);

	}
	

	
	
	private ResponseEntity<HttpCustomResponse> response(HttpStatus httpStatus, String message) {

		return new ResponseEntity<>(new HttpCustomResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);

	}


}
