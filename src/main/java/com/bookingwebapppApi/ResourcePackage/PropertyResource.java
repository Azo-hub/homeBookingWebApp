package com.bookingwebapppApi.ResourcePackage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingwebapppApi.ModelPackage.Property;
import com.bookingwebapppApi.ServicePackage.PropertyService;

@RestController
public class PropertyResource {
	
	@Autowired
	private PropertyService propertyService;
	
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
	


	
	
	
}
