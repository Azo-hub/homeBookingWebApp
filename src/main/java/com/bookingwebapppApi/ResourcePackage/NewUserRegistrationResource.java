package com.bookingwebapppApi.ResourcePackage;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingwebapppApi.ExceptionPackage.EmailExistException;
import com.bookingwebapppApi.ExceptionPackage.InvalidTokenException;
import com.bookingwebapppApi.ExceptionPackage.UserNotFoundException;
import com.bookingwebapppApi.ExceptionPackage.UsernameExistException;
import com.bookingwebapppApi.ModelPackage.Userr;
import com.bookingwebapppApi.ServicePackage.UserSecurityService;
import com.bookingwebapppApi.ServicePackage.UserService;
import com.bookingwebapppApi.UtilityPackage.MailConstructor;
import com.bookingwebapppApi.UtilityPackage.PasswordResetToken;
import com.bookingwebapppApi.UtilityPackage.SecurityUtility;

@RestController
public class NewUserRegistrationResource {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private UserService userService;

	@Autowired
	private UserSecurityService userSecurityService;

	@GetMapping("/newUser")
	public ResponseEntity<Userr> newUser(Locale locale, @RequestParam("token") String token)
			throws InvalidTokenException {
		PasswordResetToken passToken = userService.getPasswordResetToken(token);

		if (passToken == null) {
			throw new InvalidTokenException("Invalid Request");
		}

		Userr user = passToken.getUser();
		String username = user.getUsername();
		UserDetails userDetails = userSecurityService.loadUserByUsername(username);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		Userr loginUser = userService.findByUsername(authentication.getName());

		return new ResponseEntity<>(loginUser, HttpStatus.OK);
	}

	@PostMapping("/newUser")
	public ResponseEntity<Userr> newUserPost(HttpServletRequest request,@RequestBody Userr user)
			throws UsernameExistException, UserNotFoundException, EmailExistException {

		Userr newUser = userService.createUser(user.getEmail(), user.getUsername());

		String password = SecurityUtility.randomPassword();
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		newUser.setPassword(encryptedPassword);
		newUser.setUserType(user.getUserType());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		userService.save(newUser);

		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(newUser, token);
		

		//String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		SimpleMailMessage Email = mailConstructor.constructNewUserEmail(request.getLocale(), token, newUser,
				password);
		
		mailSender.send(Email);
		

		return new ResponseEntity<>(newUser, HttpStatus.OK);

	}


}
