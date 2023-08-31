package com.bookingWebAppApi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableScheduling
@SpringBootApplication
public class BookingWebAppApiApplication /* implements CommandLineRunner */ {

	/*
	 * @Autowired private JavaMailSender mailSender;
	 * 
	 * @Autowired private MailConstructor mailConstructor;
	 * 
	 * @Autowired private UserService userService;
	 */

	@Bean
	AuditorAware<String> auditorAware() {

		return new SpringSecurityAuditorAware();
	}

	public static void main(String[] args) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "************", // insert here you cloud name
				"api_key", "*****************", // insert here your api code
				"api_secret", "****************************")); // insert here your api secret

		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();

		SpringApplication.run(BookingWebAppApiApplication.class, args);
	}

	@Value("${angularFrontendLocalHostUrl}")
	private String angularFrontendLocalHostUrl;

	@Value("${angularFrontendRemoteUrl}")
	private String angularFrontendRemoteUrl;

	@Bean
	CorsFilter corsFilter() {

		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		// corsConfiguration.setAllowedOrigins(Collections.singletonList(angularFrontendLocalHostUrl));
		corsConfiguration.setAllowedOrigins(Arrays.asList(angularFrontendLocalHostUrl, angularFrontendRemoteUrl));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"MediaType", "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "MediaType",
				"Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);

	}

	/*
	 * @Override public void run(String ...args) throws Exception {
	 * 
	 * Userr newUser = userService.createUser("readone.cybernet@gmail.com",
	 * "readone", "ROLE_ADMIN");
	 * 
	 * String password = SecurityUtility.randomPassword(); String encryptedPassword
	 * = SecurityUtility.passwordEncoder().encode(password);
	 * newUser.setPassword(encryptedPassword);
	 * 
	 * newUser.setFirstname("Readone");
	 * 
	 * newUser.setLastname("Azeez");
	 * 
	 * newUser.setIsIdcard(true);
	 * 
	 * newUser.setIsVerified(true);
	 * 
	 * userService.save(newUser);
	 * 
	 * String token = UUID.randomUUID().toString();
	 * userService.createPasswordResetTokenForUser(newUser, token);
	 * 
	 * 
	 * 
	 * SimpleMailMessage Email = mailConstructor.constructNewUserEmail(null, token,
	 * newUser, password);
	 * 
	 * mailSender.send(Email);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 */

}
