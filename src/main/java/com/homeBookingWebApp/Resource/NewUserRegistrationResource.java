package com.homeBookingWebApp.Resource;

import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homeBookingWebApp.Exception.EmailExistException;
import com.homeBookingWebApp.Exception.UserNotFoundException;
import com.homeBookingWebApp.Exception.UsernameExistException;
import com.homeBookingWebApp.Model.Userr;
import com.homeBookingWebApp.Service.UserService;
import com.homeBookingWebApp.Utility.MailConstructor;
import com.homeBookingWebApp.Utility.SecurityUtility;

@RestController
public class NewUserRegistrationResource {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private UserService userService;

	@PostMapping("/newUser")
	public ResponseEntity<Userr> newUserPost(HttpServletRequest request, @RequestBody Userr user)
			throws UsernameExistException, UserNotFoundException, EmailExistException {

		Userr newUser = userService.createUser(user.getEmail(), user.getUsername(), user.getRole());

		String password = SecurityUtility.randomPassword();
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		newUser.setPassword(encryptedPassword);

		/* Capitalizing first letter of the first name */

		if (user.getFirstname() != null && user.getFirstname().isBlank()) {

			char FirstName_FirstChar = user.getFirstname().trim().charAt(0);

			String UpperCase_FirstName_FirstChar = Character.toString(FirstName_FirstChar).toUpperCase();

			String FirstName_RemainingChar = user.getFirstname().trim().substring(1).toLowerCase();

			String FirstName = UpperCase_FirstName_FirstChar.concat(FirstName_RemainingChar);

			newUser.setFirstname(FirstName);

		}

		/* Capitalizing first letter of the last name */

		if (user.getLastname() != null && !user.getLastname().isBlank()) {

			char LastName_FirstChar = user.getLastname().trim().charAt(0);

			String UpperCase_LastName_FirstChar = Character.toString(LastName_FirstChar).toUpperCase();

			String LastName_RemainingChar = user.getLastname().trim().substring(1).toLowerCase();

			String LastName = UpperCase_LastName_FirstChar.concat(LastName_RemainingChar);

			newUser.setLastname(LastName);

		}

		userService.save(newUser);

		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(newUser, token);

		SimpleMailMessage Email = mailConstructor.constructNewUserEmail(request.getLocale(), token, newUser, password);

		mailSender.send(Email);

		return new ResponseEntity<>(newUser, HttpStatus.OK);

	}

}
