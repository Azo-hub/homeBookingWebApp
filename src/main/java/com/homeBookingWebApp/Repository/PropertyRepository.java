package com.homeBookingWebApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeBookingWebApp.Model.Property;

import jakarta.transaction.Transactional;


@Transactional
public interface PropertyRepository extends JpaRepository<Property, Long> {
    
	List<Property> findByPropertyType(String PropertyType);

	List<Property> findByCreatedBy(String propertyOwner);
}
