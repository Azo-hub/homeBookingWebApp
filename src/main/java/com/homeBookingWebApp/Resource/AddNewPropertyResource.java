package com.homeBookingWebApp.Resource;

import java.security.Principal;
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

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.homeBookingWebApp.Exception.AccountVerifiedException;
import com.homeBookingWebApp.Exception.EmailExistException;
import com.homeBookingWebApp.Exception.UserNotFoundException;
import com.homeBookingWebApp.Exception.UsernameExistException;
import com.homeBookingWebApp.Model.Property;
import com.homeBookingWebApp.Model.Userr;
import com.homeBookingWebApp.Service.PropertyService;
import com.homeBookingWebApp.Service.UserService;
import com.homeBookingWebApp.Utility.HttpCustomResponse;

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
		newProperty.setPropertyOwner(property.getCreatedBy());
		propertyService.save(newProperty);

		return new ResponseEntity<>(newProperty, HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage1")
	public ResponseEntity<HttpCustomResponse> propertyImage1(@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_1" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage2")
	public ResponseEntity<HttpCustomResponse> propertyImage2(@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_2" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage3")
	public ResponseEntity<HttpCustomResponse> propertyImage3(@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_3" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage4")
	public ResponseEntity<HttpCustomResponse> propertyImage4(@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_4" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage5")
	public ResponseEntity<HttpCustomResponse> propertyImage5(@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_5" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage6")
	public ResponseEntity<HttpCustomResponse> propertyImage6(@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_6" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage7")
	public ResponseEntity<HttpCustomResponse> propertyImage7(@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_7" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage8")
	public ResponseEntity<HttpCustomResponse> propertyImage8(@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_8" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage9")
	public ResponseEntity<HttpCustomResponse> propertyImage9(@RequestParam("propertyImage") MultipartFile propertyImage,
			@RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_9" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage10")
	public ResponseEntity<HttpCustomResponse> propertyImage10(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_10" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage11")
	public ResponseEntity<HttpCustomResponse> propertyImage11(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_11" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage12")
	public ResponseEntity<HttpCustomResponse> propertyImage12(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_12" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage13")
	public ResponseEntity<HttpCustomResponse> propertyImage13(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_13" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage14")
	public ResponseEntity<HttpCustomResponse> propertyImage14(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_14" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage15")
	public ResponseEntity<HttpCustomResponse> propertyImage15(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_15" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage16")
	public ResponseEntity<HttpCustomResponse> propertyImage16(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_16" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage17")
	public ResponseEntity<HttpCustomResponse> propertyImage17(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_17" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage18")
	public ResponseEntity<HttpCustomResponse> propertyImage18(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_18" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage19")
	public ResponseEntity<HttpCustomResponse> propertyImage19(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_19" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage20")
	public ResponseEntity<HttpCustomResponse> propertyImage20(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_20" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage21")
	public ResponseEntity<HttpCustomResponse> propertyImage21(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_21" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage22")
	public ResponseEntity<HttpCustomResponse> propertyImage22(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_22" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage23")
	public ResponseEntity<HttpCustomResponse> propertyImage23(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_23" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage24")
	public ResponseEntity<HttpCustomResponse> propertyImage24(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_24" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage25")
	public ResponseEntity<HttpCustomResponse> propertyImage25(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_25" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage26")
	public ResponseEntity<HttpCustomResponse> propertyImage26(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_26" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage27")
	public ResponseEntity<HttpCustomResponse> propertyImage27(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_27" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage28")
	public ResponseEntity<HttpCustomResponse> propertyImage28(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_28" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage29")
	public ResponseEntity<HttpCustomResponse> propertyImage29(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_29" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadPropertyImage30")
	public ResponseEntity<HttpCustomResponse> propertyImage30(
			@RequestParam("propertyImage") MultipartFile propertyImage, @RequestParam("propertyId") Long propertyId) {

		Property property = propertyService.findById(propertyId);
		if (!propertyImage.isEmpty()) {

			try {
				byte[] bytes = propertyImage.getBytes();
				String name = "bookingwebapp_30" + property.getName() + property.getId();
				// File file = new File(name);
				// FileOutputStream fos = new FileOutputStream(file);
				// fos.write(bytes);
				// fos.close();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				/* uploading video file to cloudinary */

				// Map uploadResult = cloudinary.uploader().upload(bytes,
				// ObjectUtils.asMap("resource_type", "video",
				// "overwrite", "true"));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}

	private ResponseEntity<HttpCustomResponse> response(HttpStatus httpStatus, String message) {

		return new ResponseEntity<>(new HttpCustomResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);

	}

}
