package com.bookingWebAppApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingWebAppApi.Model.Property;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PropertyRepository extends JpaRepository<Property, Long> {
    
	List<Property> findByPropertyType(String PropertyType);

	List<Property> findByCreatedBy(String propertyOwner);
}
