package com.bookingwebapppApi;

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
@SpringBootApplication
@EnableScheduling
public class BookingwebapppApiApplication {
	
	@Bean
	public AuditorAware<String> auditorAware() {

		return new SpringSecurityAuditorAware();
	}


	public static void main(String[] args) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "azostore", // insert here you cloud name
				"api_key", "873821249613849", // insert here your api code
				"api_secret", "GPu-lx7TZYpi8VXxeo-8mvW0ri4")); // insert here your api secret
		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();
		
		SpringApplication.run(BookingwebapppApiApplication.class, args);
	}
	
	
	@Value("${angularFrontendLocalHostUrl}")
    private String angularFrontendLocalHostUrl;

    @Value("${angularFrontendRemoteUrl}")
    private String angularFrontendRemoteUrl;

    
    
    @Bean
	public CorsFilter corsFilter() {

		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		//corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		corsConfiguration.setAllowedOrigins(Arrays.asList(angularFrontendLocalHostUrl,angularFrontendRemoteUrl));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"MediaType", "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token","MediaType",
				"Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);

	}



}
