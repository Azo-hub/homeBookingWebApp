package com.homeBookingWebApp.Resource;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homeBookingWebApp.Model.UserPrincipal;
import com.homeBookingWebApp.Model.Userr;
import com.homeBookingWebApp.Service.UserService;
import com.homeBookingWebApp.Utility.JWTTokenProvider;
import com.homeBookingWebApp.Utility.SecurityConstant;

@RestController
public class LoginResource {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenProvider jWTTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<Userr> login(@RequestBody Userr user) {
		authenticate(user.getUsername(), user.getPassword());
		Userr loginUser = userService.findByUsername(user.getUsername());
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
		loginUser.setLastLoginDate(new Date());
		userService.save(loginUser);
		loginUser.setLastLoginDateDisplay(loginUser.getLastLoginDate());
		userService.save(loginUser);
		return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

	private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {

		HttpHeaders headers = new HttpHeaders();
		headers.add(SecurityConstant.JWT_TOKEN_HEADER, jWTTokenProvider.generateJwtToken(userPrincipal));
		return headers;
	}


}
