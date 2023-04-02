package com.bookingwebapppApi.ResourcePackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingwebapppApi.ExceptionPackage.EmailExistException;
import com.bookingwebapppApi.ExceptionPackage.PasswordNotMatchException;
import com.bookingwebapppApi.ExceptionPackage.UserNotFoundException;
import com.bookingwebapppApi.ExceptionPackage.UsernameExistException;
import com.bookingwebapppApi.ModelPackage.Role;
import com.bookingwebapppApi.ModelPackage.Userr;
import com.bookingwebapppApi.ServicePackage.UserService;
import com.bookingwebapppApi.UtilityPackage.SecurityUtility;

@RestController
public class UserProfileResource {
	@Autowired
	private UserService userService;

	@PostMapping("/updateUserInfoBySelf")
	public ResponseEntity<Userr> updateUserInfoSelf(@RequestParam("currentPassword") String password,
			@RequestParam("email") String email, @RequestParam("username") String username,
			@RequestParam("currentUsername") String currentUsername, @RequestParam("firstName") String firstname,
			@RequestParam("lastName") String lastname, @RequestParam("phone") String phone,
			@RequestParam("role") String role, @RequestParam("gender") String gender,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword
			/*@RequestParam("accountEnabled") String accountEnabled,
			@RequestParam("accountNonLocked") String accountNonLocked*/)
			throws UserNotFoundException, EmailExistException, UsernameExistException, PasswordNotMatchException {

		Userr currentUser = userService.findByUsername(currentUsername);

		if (currentUser == null) {
			throw new UserNotFoundException("User not found");

		}

		updateUser(currentUser, password, email, username, firstname, lastname, phone, role, gender, newPassword,
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

		updateUser(currentUser, "", email, username, firstname, lastname, phone, role, "", "", "", accountEnabled,
				accountNonLocked);

		return new ResponseEntity<>(currentUser, HttpStatus.OK);

	}

	private void updateUser(Userr currentUser, String password, String email, String username, String firstname,
			String lastname, String phone, String role, String gender, String newPassword, String confirmPassword,
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
