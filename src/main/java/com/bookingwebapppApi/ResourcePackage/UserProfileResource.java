package com.bookingwebapppApi.ResourcePackage;

import java.security.Principal;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookingwebapppApi.ExceptionPackage.EmailExistException;
import com.bookingwebapppApi.ExceptionPackage.PasswordNotMatchException;
import com.bookingwebapppApi.ExceptionPackage.PropertyBookingExistException;
import com.bookingwebapppApi.ExceptionPackage.UserNotFoundException;
import com.bookingwebapppApi.ExceptionPackage.UsernameExistException;
import com.bookingwebapppApi.ModelPackage.CheckInAndOutDate;
import com.bookingwebapppApi.ModelPackage.Property;
import com.bookingwebapppApi.ModelPackage.Role;
import com.bookingwebapppApi.ModelPackage.Userr;
import com.bookingwebapppApi.ServicePackage.UserService;
import com.bookingwebapppApi.UtilityPackage.HttpCustomResponse;
import com.bookingwebapppApi.UtilityPackage.MailConstructor;
import com.bookingwebapppApi.UtilityPackage.SecurityUtility;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;

@RestController
public class UserProfileResource {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private UserService userService;

	private final Cloudinary cloudinary = Singleton.getCloudinary();

	@PreAuthorize("hasAnyAuthority('user:read')")
	@PostMapping("/updateUserInfoBySelf")
	public ResponseEntity<Userr> updateUserInfoSelf(@RequestParam("currentPassword") String password,
			@RequestParam("email") String email, @RequestParam("username") String username,
			@RequestParam("currentUsername") String currentUsername, @RequestParam("firstName") String firstname,
			@RequestParam("lastName") String lastname, @RequestParam("phone") String phone,
			@RequestParam("role") String role, @RequestParam("gender") String gender,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
			@RequestParam("dateOfBirth") String dOB ) 
					throws UserNotFoundException, EmailExistException, UsernameExistException, PasswordNotMatchException {

		Userr currentUser = userService.findByUsername(currentUsername);

		if (currentUser == null) {
			throw new UserNotFoundException("User not found");

		}
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate dateOfBirth = LocalDate.parse(dOB, format);
		
		
		
		
		//LocalDate dateOfBirthLocalDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(dateOfBirth);
		updateUser(currentUser, password, email, username, firstname, lastname, phone, role, gender, dateOfBirth, newPassword,
				confirmPassword, "true", "true");

		return new ResponseEntity<>(currentUser, HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('user:update')")
	@PostMapping("/updateUserInfo")
	public ResponseEntity<Userr> updateUserInfo(@RequestParam("email") String email,
			@RequestParam("username") String username, @RequestParam("firstName") String firstname,
			@RequestParam("lastName") String lastname, @RequestParam("phone") String phone,
			@RequestParam("role") String role, @RequestParam("accountEnabled") String accountEnabled,
			@RequestParam("accountNonLocked") String accountNonLocked)
			throws UserNotFoundException, EmailExistException, UsernameExistException, PasswordNotMatchException {

		Userr currentUser = userService.findByUsername(username);

		if (currentUser == null) {
			throw new UserNotFoundException("User not found");

		}

		updateUser(currentUser, "", email, username, firstname, lastname, phone, role, "", null,"", "", accountEnabled,
				accountNonLocked);

		return new ResponseEntity<>(currentUser, HttpStatus.OK);

	}

	@GetMapping("/allUser")
	public ResponseEntity<List<Userr>> getAllUsers() {

		List<Userr> allUser = userService.getAllUsers();
		List<Userr> filteredUser = new ArrayList<>();

		for (Userr eachUser : allUser) {
			if (!eachUser.getRole().equals("ROLE_ADMIN")) {

				filteredUser.add(eachUser);

			}
		}

		return new ResponseEntity<>(filteredUser, HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/uploadIdentityImage")
	public ResponseEntity<HttpCustomResponse> identityImage(HttpServletRequest request,
			@RequestParam("identityImage") MultipartFile identityImage,
			@RequestParam("identityType") String identityType, Principal principal) {

		Userr currentUser = userService.findByUsername(principal.getName());

		if (!identityImage.isEmpty()) {

			try {
				byte[] bytes = identityImage.getBytes();

				String name = "bookingwebapp" + currentUser.getFirstname() + currentUser.getLastname()
						+ currentUser.getUserId();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				String publicId = uploadResult.get("public_id").toString();

				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

				currentUser.setIdentityType(identityType);
				currentUser.setIsIdcard(true);
				userService.save(currentUser);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		if (currentUser.getIsIdcard() == true) {

			SimpleMailMessage email1 = mailConstructor.constructIdentityUploadEmail(request.getLocale(), currentUser);

			mailSender.send(email1);

			SimpleMailMessage email2 = mailConstructor.constructIdentityUploadEmailAdmin1(request.getLocale(),
					currentUser);

			mailSender.send(email2);

			SimpleMailMessage email3 = mailConstructor.constructIdentityUploadEmailAdmin2(request.getLocale(),
					currentUser);

			mailSender.send(email3);
		}

		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}
	
	
	
	
	@PreAuthorize("hasAnyAuthority('user:read')")
	@PostMapping("/uploadProfileImage")
	public ResponseEntity<HttpCustomResponse> profileImage(HttpServletRequest request,
			@RequestParam("profileImage") MultipartFile profileImage,
			Principal principal) {

		Userr currentUser = userService.findByUsername(principal.getName());

		if (!profileImage.isEmpty()) {

			try {
				byte[] bytes = profileImage.getBytes();

				String name = "bookingwebapp-profileImage-" + currentUser.getFirstname() + currentUser.getLastname()
						+ currentUser.getUserId();

				/* uploading image file to cloudinary */
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));

				String publicId = uploadResult.get("public_id").toString();

				cloudinary.uploader().rename(publicId, name,
						ObjectUtils.asMap("resource_type", "image", "overwrite", "true"));

				
				currentUser.setIsImage(true);
				userService.save(currentUser);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		
		return response(HttpStatus.OK, "Image Uploaded Successfully");

	}
	
	

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/getUserByUsername")
	public ResponseEntity<Userr> getUserById(@RequestParam("username") String username) {

		Userr user = userService.findByUsername(username);

		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@PreAuthorize("hasAnyAuthority('user:create')")
	@PostMapping("/verifyAcct")
	public ResponseEntity<HttpCustomResponse> verifyAccount(@RequestParam("verify") boolean verify,
			@RequestParam("username") String username) {

		Userr user = userService.findByUsername(username);

		user.setIsVerified(verify);

		userService.save(user);

		return response(HttpStatus.OK, "User Verification Status Changed Successfully");

	}
	
	
	@PreAuthorize("hasAnyAuthority('user:read')")
	@PostMapping("/support")
	public ResponseEntity<HttpCustomResponse> contactSuport(HttpServletRequest request, @RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("email") String email, @RequestParam("subject") String subject,
			@RequestParam("phonenumber") String phonenumber, @RequestParam("problem") String problem) {
		
			SimpleMailMessage supportMail = mailConstructor.contactSupportEmail(request.getLocale(), firstname, lastname, email, 
																					subject, phonenumber, problem);

			mailSender.send(supportMail);

		
		return response(HttpStatus.OK, "Message Sent Successfully! We will contact you as soon as possible.");

	}

	
	
	

	private ResponseEntity<HttpCustomResponse> response(HttpStatus httpStatus, String message) {

		return new ResponseEntity<>(new HttpCustomResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);

	}

	private void updateUser(Userr currentUser, String password, String email, String username, String firstname,
			String lastname, String phone, String role, String gender, LocalDate dateOfBirth, String newPassword, String confirmPassword,
			String accountEnabled, String accountNonLocked)
			throws UserNotFoundException, EmailExistException, UsernameExistException, PasswordNotMatchException {

		/* check email already exists */

		if (userService.findByEmail(email) != null) {
			if (userService.findByEmail(email).getId() != currentUser.getId()) {

				throw new EmailExistException("Email Already Exist");
			}

		}

		/* check username already exists */

		if (userService.findByUsername(username) != null) {
			if (userService.findByUsername(username).getId() != currentUser.getId()) {

				throw new UsernameExistException("Username Already Exist");
			}

		}

		/* update password */

		if (password != null && !password.isEmpty() && !password.equals("")) {

			if (!newPassword.matches(confirmPassword)) {

				throw new PasswordNotMatchException("Password Not Match");
			}

			if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
				BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
				String dbPassword = currentUser.getPassword();
				if (passwordEncoder.matches(password, dbPassword)) {
					currentUser.setPassword(passwordEncoder.encode(newPassword));

				} else {

					throw new UserNotFoundException("Invalid User");

				}

			}

		}

		/* Capitalizing first letter of the first name */

		if (!firstname.trim().isEmpty()) {

			char FirstName_FirstChar = firstname.trim().charAt(0);

			String UpperCase_FirstName_FirstChar = Character.toString(FirstName_FirstChar).toUpperCase();

			String FirstName_RemainingChar = firstname.trim().substring(1).toLowerCase();

			String FirstName = UpperCase_FirstName_FirstChar.concat(FirstName_RemainingChar);

			currentUser.setFirstname(FirstName);

		}

		/* Capitalizing first letter of the last name */

		if (!lastname.trim().isEmpty()) {

			char LastName_FirstChar = lastname.trim().charAt(0);

			String UpperCase_LastName_FirstChar = Character.toString(LastName_FirstChar).toUpperCase();

			String LastName_RemainingChar = lastname.trim().substring(1).toLowerCase();

			String LastName = UpperCase_LastName_FirstChar.concat(LastName_RemainingChar);

			currentUser.setLastname(LastName);

		}

		if (!phone.trim().isEmpty()) {

			currentUser.setPhone(phone);
		}

		if (!gender.trim().isEmpty()) {

			currentUser.setGender(gender);
		}
		
		if (dateOfBirth != null) {

			currentUser.setDateOfBirth(dateOfBirth);
		}


		if (!role.trim().isEmpty() && role.equals("ROLE_TRAVELLER_USER")) {

			currentUser.setRole(Role.ROLE_TRAVELLER_USER.name());
			currentUser.setAuthorities(Role.ROLE_TRAVELLER_USER.getAuthorities());

		}

		if (!role.trim().isEmpty() && role.equals("ROLE_OWNER_USER")) {
			currentUser.setRole(Role.ROLE_OWNER_USER.name());
			currentUser.setAuthorities(Role.ROLE_OWNER_USER.getAuthorities());

		}

		if (!role.trim().isEmpty() && role.equals("ROLE_ADMIN")) {
			currentUser.setRole(Role.ROLE_ADMIN.name());
			currentUser.setAuthorities(Role.ROLE_ADMIN.getAuthorities());

		}

		currentUser.setAccountEnabled(Boolean.parseBoolean(accountEnabled));

		currentUser.setAccountNonLocked(Boolean.parseBoolean(accountNonLocked));

		userService.save(currentUser);

	}

}
